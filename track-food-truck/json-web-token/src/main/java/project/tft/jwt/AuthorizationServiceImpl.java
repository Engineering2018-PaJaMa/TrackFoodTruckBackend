package project.tft.jwt;

import static com.auth0.jwt.algorithms.Algorithm.RSA512;
import static org.apache.commons.lang3.time.DateUtils.addMinutes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Paweł Szopa on 22/01/2019
 */
@Component
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private StoredKeyPair keyPair;

    @Override
    public String generateToken(String login, String role) {
        Date date = new Date();
        Algorithm algorithm = RSA512(keyPair.loadRSAPublicKey(), keyPair.loadRSAPrivateKey());
        return JWT.create()
            .withIssuer("TrackFoodTruck")
            .withAudience(role)
            .withExpiresAt(addMinutes(date, 1))
            .withNotBefore(date)
            .sign(algorithm);
    }

    @Override
    public TokenWithStatus validateToken(String token) {
        DecodedJWT jwt = null;
        keyPair.setPrivateKey(keyPair.loadRSAPrivateKey());
        keyPair.setPublicKey(keyPair.loadRSAPublicKey());
        try {
            Algorithm algorithm = RSA512(keyPair.getPublicKey(), keyPair.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt = verifier.verify(token);

            return new TokenWithStatus(jwt, true);
        } catch (JWTVerificationException e) {
            log.info("{}", e.getMessage());
        }
        return new TokenWithStatus(jwt, false);
    }

    @Data
    @AllArgsConstructor
    public class TokenWithStatus {

        private DecodedJWT jwt;
        private boolean valid;
    }
}
