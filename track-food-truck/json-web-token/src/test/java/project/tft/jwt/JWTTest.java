package project.tft.jwt;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Paweł Szopa on 21/01/2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = StoredKeyPair.class)
public class JWTTest
{
	private static RSAPublicKey rsaPublicKey2048;
	private static RSAPublicKey rsaPublicKey4096;
	private static RSAPrivateKey rsaPrivateKey2048;
	private static RSAPrivateKey rsaPrivateKey4096;

	@Autowired
	private StoredKeyPair keyPair;

	@BeforeClass
	public static void setUp() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		GenerateKeys generateKeys = new GenerateKeys();
		generateKeys.generateRSAKeyPairWithSize2048();
		generateKeys.generateRSAKeyPairWithSize4096();
		rsaPublicKey2048 = (RSAPublicKey) generateKeys.loadRsaPublicKey2048();
		rsaPrivateKey2048 = (RSAPrivateKey) generateKeys.loadRsaPrivateKey2048();
		rsaPublicKey4096 = (RSAPublicKey) generateKeys.loadRsaPublicKey4096();
		rsaPrivateKey4096 = (RSAPrivateKey) generateKeys.loadRsaPrivateKey4096();
	}

	@Test
	public void createAndVerifyTokenWithRSA256AndKeySize2048()
	{
		Algorithm algorithm = Algorithm.RSA256(rsaPublicKey2048, rsaPrivateKey2048);
		String token = JWT.create().sign(algorithm);
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT jwt = verifier.verify(token);
		assertThat(jwt).isNotNull();
	}

	@Test
	public void createAndVerifyTokenWithRSA256AndKeySize4096()
	{
		Algorithm algorithm = Algorithm.RSA256(rsaPublicKey4096, rsaPrivateKey4096);
		String token = JWT.create().sign(algorithm);
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT jwt = verifier.verify(token);
		assertThat(jwt).isNotNull();
	}

	@Test
	public void createAndVerifyTokenWithRSA512AndKeySize2048()
	{
		Algorithm algorithm = Algorithm.RSA512(rsaPublicKey2048, rsaPrivateKey2048);
		String token = JWT.create().sign(algorithm);
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT jwt = verifier.verify(token);
		assertThat(jwt).isNotNull();
	}

	@Test
	public void createAndVerifyTokenWithRSA512AndKeySize4096()
	{
		Algorithm algorithm = Algorithm.RSA512(rsaPublicKey4096, rsaPrivateKey4096);
		String token = JWT.create().sign(algorithm);
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT jwt = verifier.verify(token);
		assertThat(jwt).isNotNull();
	}

	@Test
	public void creatingTokenForIntegrationTests()
	{
		//given
		keyPair.setPrivateKey((RSAPrivateKey) keyPair.loadRSAPrivateKey());
		keyPair.setPublicKey((RSAPublicKey) keyPair.loadRSAPublicKey());
		Algorithm algorithm = Algorithm.RSA512(keyPair.getPublicKey(), keyPair.getPrivateKey());
		//when
		String token = JWT.create().sign(algorithm);
		//then
		System.out.println(token);
	}

}
