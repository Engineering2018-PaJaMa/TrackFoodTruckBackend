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
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bson.internal.Base64;
import org.springframework.stereotype.Component;

/**
 * Created by Paweł Szopa on 21/01/2019
 */
@Component
public class GenerateKeys {

    public KeyPair generateRSAKeyPairWithSize(int keySize) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        saveToFileRSAKeyPairWithSize(keySize, keyPair);
        saveToFileRSAKeyPairBase64WithSize(keySize, keyPair);

        return keyPair;
    }

    private void saveToFileRSAKeyPairWithSize(int keySize, KeyPair keyPair) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(".tft" + keySize + ".pub");
        fileOutputStream.write(keyPair.getPublic().getEncoded());
        fileOutputStream.close();

        fileOutputStream = new FileOutputStream(".tft" + keySize);
        fileOutputStream.write(keyPair.getPrivate().getEncoded());
        fileOutputStream.close();
    }

    private void saveToFileRSAKeyPairBase64WithSize(int keySize, KeyPair keyPair) throws IOException {
        Writer writer = new FileWriter(".tft" + keySize + ".pub.txt");
        writer.write(Base64.encode(keyPair.getPublic().getEncoded()));
        writer.close();

        writer = new FileWriter(".tft" + keySize + ".txt");
        writer.write(Base64.encode(keyPair.getPrivate().getEncoded()));
        writer.close();
    }

    public PublicKey loadRsaPublicKey(int keySize) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Path path = Paths.get(".tft" + keySize + ".pub");
        byte[] bytes = Files.readAllBytes(path);
        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(bytes);
        return KeyFactory.getInstance("RSA").generatePublic(encodedKeySpec);
    }

    public PrivateKey loadRsaPrivateKey(int keySize) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Path path = Paths.get(".tft" + keySize);
        byte[] bytes = Files.readAllBytes(path);

        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(bytes);
        return KeyFactory.getInstance("RSA").generatePrivate(encodedKeySpec);
    }
}
