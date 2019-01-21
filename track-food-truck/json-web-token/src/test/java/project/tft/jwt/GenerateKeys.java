package project.tft.jwt;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bson.internal.Base64;

/**
 * Created by Paweł Szopa on 21/01/2019
 */
public class GenerateKeys
{
	private RSAPublicKey rsaPublicKey2048;
	private RSAPublicKey rsaPublicKey4096;
	private RSAPrivateKey rsaPrivateKey2048;
	private RSAPrivateKey rsaPrivateKey4096;

	public void generateRSAKeyPairWithSize2048() throws NoSuchAlgorithmException, IOException
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		this.rsaPublicKey2048 = (RSAPublicKey) keyPair.getPublic();
		this.rsaPrivateKey2048 = (RSAPrivateKey) keyPair.getPrivate();

		saveToFileRSAKeyPairWithSize2048();
		saveToFileRSAKeyPairBase64WithSize2048();
	}

	public void generateRSAKeyPairWithSize4096() throws NoSuchAlgorithmException, IOException
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(4096);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		this.rsaPublicKey4096 = (RSAPublicKey) keyPair.getPublic();
		this.rsaPrivateKey4096 = (RSAPrivateKey) keyPair.getPrivate();

		saveToFileRSAKeyPairWIthSize4096();
		saveToFileRSAKeyPairBase64WithSize4096();
	}

	private void saveToFileRSAKeyPairWithSize2048() throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(".tft2048.pub");
		fileOutputStream.write(rsaPublicKey2048.getEncoded());
		fileOutputStream.close();

		fileOutputStream = new FileOutputStream(".tft2048");
		fileOutputStream.write(rsaPrivateKey2048.getEncoded());
		fileOutputStream.close();
	}

	private void saveToFileRSAKeyPairWIthSize4096() throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(".tft4096.pub");
		fileOutputStream.write(rsaPublicKey4096.getEncoded());
		fileOutputStream.close();

		fileOutputStream = new FileOutputStream(".tft4096");
		fileOutputStream.write(rsaPrivateKey4096.getEncoded());
		fileOutputStream.close();
	}

	private void saveToFileRSAKeyPairBase64WithSize2048() throws IOException
	{
		Writer writer = new FileWriter(".tft2048.pub.txt");
		writer.write(Base64.encode(rsaPublicKey2048.getEncoded()));
		writer.close();

		writer = new FileWriter(".tft2048.txt");
		writer.write(Base64.encode(rsaPrivateKey2048.getEncoded()));
		writer.close();
	}

	private void saveToFileRSAKeyPairBase64WithSize4096() throws IOException
	{
		Writer writer = new FileWriter(".tft4096.pub.txt");
		writer.write(Base64.encode(rsaPublicKey4096.getEncoded()));
		writer.close();

		writer = new FileWriter(".tft4096.txt");
		writer.write(Base64.encode(rsaPrivateKey4096.getEncoded()));
		writer.close();
	}

	public PublicKey loadRsaPublicKey2048() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		Path path = Paths.get("/Users/Pawel/IdeaProjects/TrackFoodTruckBackend/track-food-truck/json-web-token/.tft2048.pub");
		byte[] bytes = Files.readAllBytes(path);
		X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
		return KeyFactory.getInstance("RSA").generatePublic(ks);
	}

	public PublicKey loadRsaPublicKey4096() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		Path path = Paths.get("/Users/Pawel/IdeaProjects/TrackFoodTruckBackend/track-food-truck/json-web-token/.tft4096.pub");
		byte[] bytes = Files.readAllBytes(path);
		X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
		return KeyFactory.getInstance("RSA").generatePublic(ks);
	}

	public PrivateKey loadRsaPrivateKey2048() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		Path path = Paths.get("/Users/Pawel/IdeaProjects/TrackFoodTruckBackend/track-food-truck/json-web-token/.tft2048");
		byte[] bytes = Files.readAllBytes(path);

		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
		return KeyFactory.getInstance("RSA").generatePrivate(ks);
	}

	public PrivateKey loadRsaPrivateKey4096() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		Path path = Paths.get("/Users/Pawel/IdeaProjects/TrackFoodTruckBackend/track-food-truck/json-web-token/.tft4096");
		byte[] bytes = Files.readAllBytes(path);

		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
		return KeyFactory.getInstance("RSA").generatePrivate(ks);
	}
}
