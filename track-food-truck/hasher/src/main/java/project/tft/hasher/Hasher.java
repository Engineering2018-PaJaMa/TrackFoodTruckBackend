package project.tft.hasher;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.tft.salter.SalterService;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
@Component
@Slf4j
public class Hasher implements HasherService {

    private final static String SHA_3_512 = "SHA3-512";

    @Autowired
    private SalterService salter;

    @Value("${hasher.pepper}")
    private String PEPPER;

    @Override
    public SaltedHash encrypt(final String toEncrypt) {
        try {
            SaltedHash saltedHash = new SaltedHash();
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_3_512);
            saltedHash.setSalt(salter.generateSalt1024());
            messageDigest.update(saltedHash.getSalt().getBytes());
            messageDigest.update(PEPPER.getBytes());

            byte[] encrypted = messageDigest.digest(toEncrypt.getBytes(UTF_8));
            saltedHash.setHash(Base64.getEncoder().withoutPadding().encodeToString(encrypted));
            return saltedHash;
        } catch (NoSuchAlgorithmException e) {
            log.error("Exception {}", e.getMessage());
            return null;
        } finally {
            clearData(toEncrypt);
        }
    }

    @Override
    public boolean matches(String rawPassword, SaltedHash saltedHash) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_3_512);
            messageDigest.update(saltedHash.getSalt().getBytes());
            messageDigest.update(PEPPER.getBytes());

            byte[] encrypted = messageDigest.digest(rawPassword.getBytes(UTF_8));
            String calculatedHash = Base64.getEncoder().withoutPadding().encodeToString(encrypted);
            return saltedHash.getHash().equals(calculatedHash);
        } catch (Exception e) {
            log.error("Exception {}", e.getMessage());
            return false;
        } finally {
            clearData(rawPassword);
            log.error(rawPassword);
        }
    }

    private void clearData(String data) {
        if (data == null) {
            return;
        }
        try {
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);
            byte[] value = (byte[]) field.get(data);
            Arrays.fill(value, Byte.parseByte("48"));
        } catch (Exception e) {
            log.error("Exception {}", e.getMessage());
        }
    }
}
