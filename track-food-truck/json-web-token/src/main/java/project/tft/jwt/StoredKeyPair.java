package project.tft.jwt;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paweł Szopa on 21/01/2019
 */
@Component
@Data
@Slf4j
public class StoredKeyPair
{
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;

	public StoredKeyPair()
	{
		this.publicKey = (RSAPublicKey) loadRSAPublicKey();
		this.privateKey = (RSAPrivateKey) loadRSAPrivateKey();
	}

	private PublicKey loadRSAPublicKey()
	{
		try
		{
			Path path = Paths.get("/Users/Pawel/IdeaProjects/TrackFoodTruckBackend/track-food-truck/json-web-token/src/main/resources/.tft4096.pub");
			byte[] bytes = Files.readAllBytes(path);

			X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(bytes);
			return KeyFactory.getInstance("RSA").generatePublic(encodedKeySpec);
		}
		catch (Exception e)
		{
			log.error("Error while loading RSA public key {}", e);
			return null;
		}
	}

	private PrivateKey loadRSAPrivateKey()
	{
		try
		{
			Path path = Paths.get("/Users/Pawel/IdeaProjects/TrackFoodTruckBackend/track-food-truck/json-web-token/src/main/resources/.tft4096");
			byte[] bytes = Files.readAllBytes(path);

			PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(bytes);
			return KeyFactory.getInstance("RSA").generatePrivate(encodedKeySpec);
		}
		catch (Exception e)
		{
			log.error("Error while loading RSA private key {}", e);
			return null;
		}
	}
}
