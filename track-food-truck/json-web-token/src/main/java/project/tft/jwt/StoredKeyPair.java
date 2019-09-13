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
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Paweł Szopa on 21/01/2019
 */
@Component
@Data
@Slf4j
public class StoredKeyPair {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @Value("${path.public_key}")
    private String pathPublicKey;

    @Value("${path.private_key}")
    private String pathPrivateKey;

    PublicKey loadRSAPublicKey() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(pathPublicKey));

            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(bytes);
            return KeyFactory.getInstance("RSA").generatePublic(encodedKeySpec);
        } catch (Exception e) {
            log.error("Error while loading RSA public key {}", e.getMessage());
            return null;
        }
    }

    PrivateKey loadRSAPrivateKey() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(pathPrivateKey));

            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(bytes);
            return KeyFactory.getInstance("RSA").generatePrivate(encodedKeySpec);
        } catch (Exception e) {
            log.error("Error while loading RSA private key {}", e.getMessage());
            return null;
        }
    }
}