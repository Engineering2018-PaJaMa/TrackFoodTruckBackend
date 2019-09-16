package project.tft.jwt;

import static com.auth0.jwt.algorithms.Algorithm.RSA256;
import static com.auth0.jwt.algorithms.Algorithm.RSA512;
import static org.assertj.core.api.Java6Assertions.assertThat;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Paweł Szopa on 21/01/2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StoredKeyPair.class, GenerateKeys.class})
public class JWTTest {

    @Autowired
    private StoredKeyPair keyPair;

    @Autowired
    private GenerateKeys generateKeys;

    private KeyPair keyPair1024;

    private KeyPair keyPair2048;

    private KeyPair keyPair4096;

    @Before
    public void setUp() throws IOException, NoSuchAlgorithmException {

        keyPair1024 = generateKeys.generateRSAKeyPairWithSize(1024);
        keyPair2048 = generateKeys.generateRSAKeyPairWithSize(2048);
        keyPair4096 = generateKeys.generateRSAKeyPairWithSize(4096);
    }

    @Test
    public void createAndVerifyTokenWithRSA256AndKeySize1024() {
        Algorithm algorithm = RSA256((RSAPublicKey) keyPair1024.getPublic(), (RSAPrivateKey) keyPair1024.getPrivate());
        String token = JWT.create().sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        assertThat(jwt).isNotNull();
    }

    @Test
    public void createAndVerifyTokenWithRSA256AndKeySize2048() {
        Algorithm algorithm = RSA256((RSAPublicKey) keyPair2048.getPublic(), (RSAPrivateKey) keyPair2048.getPrivate());
        String token = JWT.create().sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        assertThat(jwt).isNotNull();
    }

    @Test
    public void createAndVerifyTokenWithRSA256AndKeySize4096() {
        Algorithm algorithm = RSA256((RSAPublicKey) keyPair4096.getPublic(), (RSAPrivateKey) keyPair4096.getPrivate());
        String token = JWT.create().sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        assertThat(jwt).isNotNull();
    }

    @Test
    public void createAndVerifyTokenWithRSA512AndKeySize1024() {
        Algorithm algorithm = RSA512((RSAPublicKey) keyPair1024.getPublic(), (RSAPrivateKey) keyPair1024.getPrivate());
        String token = JWT.create().sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        assertThat(jwt).isNotNull();
    }

    @Test
    public void createAndVerifyTokenWithRSA512AndKeySize2048() {
        Algorithm algorithm = RSA512((RSAPublicKey) keyPair2048.getPublic(), (RSAPrivateKey) keyPair2048.getPrivate());
        String token = JWT.create().sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        assertThat(jwt).isNotNull();
    }

    @Test
    public void createAndVerifyTokenWithRSA512AndKeySize4096() {
        Algorithm algorithm = RSA512((RSAPublicKey) keyPair4096.getPublic(), (RSAPrivateKey) keyPair4096.getPrivate());
        String token = JWT.create().sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        assertThat(jwt).isNotNull();
    }

    @Test
    public void creatingTokenForIntegrationTests() {
        //given
        keyPair.setPrivateKey(keyPair.loadRSAPrivateKey());
        keyPair.setPublicKey(keyPair.loadRSAPublicKey());
        Algorithm algorithm = RSA512(keyPair.getPublicKey(), keyPair.getPrivateKey());
        //when
        String token = JWT.create().sign(algorithm);
        //then
        System.out.println(token);
    }

}
