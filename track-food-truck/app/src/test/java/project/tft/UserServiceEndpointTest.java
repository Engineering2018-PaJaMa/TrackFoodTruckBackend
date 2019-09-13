package project.tft;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import project.tft.app.TrackFoodTruckApplication;
import project.tft.db.user.User;

/**
 * Created by Paweł Szopa on 14/01/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TrackFoodTruckApplication.class, TestConfig.class}, webEnvironment = RANDOM_PORT)
public class UserServiceEndpointTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Before
    public void deleteAllUsers() {
        restTemplate.delete("http://localhost:" + port + "/tft/user/all");
        restTemplate.delete("http://localhost:" + port + "/tft/user/all/hash");
    }

    @Test
    public void registerThenLoginOneUser() {
        restTemplate.postForEntity("http://localhost:" + port + "/tft/user/new", new User("userLogin", "userPassword"), HttpEntity.class);
        restTemplate.postForEntity("http://localhost:" + port + "/tft/user", new User("userLogin", "userPassword"), HttpEntity.class);
    }

    @Test
    public void registerThenLoginThousandUsers() {
        for (int i = 0; i < 1000; i++) {
            restTemplate.postForEntity("http://localhost:" + port + "/tft/user/new", new User("userLogin" + i, "userPassword"), HttpEntity.class);
            restTemplate.postForEntity("http://localhost:" + port + "/tft/user", new User("userLogin" + i, "userPassword"), HttpEntity.class);
        }
    }

    @Test
    public void registerThenLoginOneUserWithHash() {
        restTemplate.postForEntity("http://localhost:" + port + "/tft/user/new/authorize", new User("userLogin", "userPassword"), HttpEntity.class);
        restTemplate.postForEntity("http://localhost:" + port + "/tft/user/authorize", new User("userLogin", "userPassword"), HttpEntity.class);
    }

    @Test
    public void registerThenLoginThousandUsersWithHashes() {
        for (int i = 0; i < 1000; i++) {
            restTemplate.postForEntity("http://localhost:" + port + "/tft/user/new/authorize", new User("userLogin" + i, "userPassword"), HttpEntity.class);
            restTemplate.postForEntity("http://localhost:" + port + "/tft/user/authorize", new User("userLogin" + i, "userPassword"), HttpEntity.class);
        }
    }

}