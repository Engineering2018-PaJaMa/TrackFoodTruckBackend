package project.tft.jwt;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Paweł Szopa on 22/01/2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AuthorizationServiceImpl.class, StoredKeyPair.class})
public class AuthorizationServiceImplTest {

    @Autowired
    private AuthorizationService authorizationService;

    @Test
    public void generateToken() {
        String token = authorizationService.generateToken("username", "USER");
        assertThat(token).isNotNull();
    }

    @Test
    public void generateAndValidateToken() {
        String token = authorizationService.generateToken("username", "USER");
        assertThat(authorizationService.validateToken(token).isValid()).isTrue();
    }
}
