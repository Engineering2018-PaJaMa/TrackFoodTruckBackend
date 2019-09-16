package project.tft.salter;

import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Component;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
@Component
public class Salter implements SalterService {

    private SecureRandom random = new SecureRandom();

    @Override
    public String generateSalt(int saltBytes) {
        byte[] bytes = new byte[saltBytes];
        random.nextBytes(bytes);
        return Base64.getEncoder().withoutPadding().encodeToString(bytes);
    }
}
