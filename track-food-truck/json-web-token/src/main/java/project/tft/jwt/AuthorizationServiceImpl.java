package project.tft.jwt;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paweł Szopa on 22/01/2019
 */
@Component
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService
{
	@Autowired
	private StoredKeyPair keyPair;

	@Override
	public String generateToken(final String login)
	{
		Date date = new Date();
		Algorithm algorithm = Algorithm.RSA512(keyPair.getPublicKey(), keyPair.getPrivateKey());
		return JWT.create()
				.withClaim("login", login)
				.withIssuer("tft")
				.withAudience("customer")
				.withExpiresAt(DateUtils.addMinutes(date, 30))
				.withNotBefore(date)
				.sign(algorithm);
	}

	@Override
	public boolean validateToken(final String token)
	{
		try
		{
			Algorithm algorithm = Algorithm.RSA512(keyPair.getPublicKey(), keyPair.getPrivateKey());
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
			return true;
		}
		catch (JWTVerificationException e)
		{
			log.error("Exception while validating token {}", e);
		}
		return false;
	}
}
