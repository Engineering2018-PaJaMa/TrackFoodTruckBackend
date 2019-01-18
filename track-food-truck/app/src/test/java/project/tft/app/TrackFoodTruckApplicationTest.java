package project.tft.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import project.tft.db.user.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrackFoodTruckApplicationTest
{
	@Autowired
	private RestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Before
	public void deleteAllUsers()
	{
		restTemplate.delete("http://localhost:" + port + "/tft/user/all");
	}

	@Test
	public void registerThenLoginThousandUsers()
	{
		for (int i = 0; i < 1000; i++)
		{
			restTemplate.postForEntity("http://localhost:" + port + "/tft/user/new", new User("userLogin" + i, "userPassword" + i), HttpEntity.class);
			restTemplate.postForEntity("http://localhost:" + port + "/tft/user", new User("userLogin" + i, "userPassword" + i), HttpEntity.class);
		}
	}

	@Test
	public void registerThenLoginTenThousand()
	{
		for (int i = 0; i < 10000; i++)
		{
			restTemplate.postForEntity("http://localhost:" + port + "/tft/user/new", new User("userLogin" + i, "userPassword" + i), HttpEntity.class);
			restTemplate.postForEntity("http://localhost:" + port + "/tft/user", new User("userLogin" + i, "userPassword" + i), HttpEntity.class);
		}
	}

	@Test
	public void registerThenLoginThousandUsersWithHashes()
	{
		for (int i = 0; i < 1000; i++)
		{
			restTemplate.postForEntity("http://localhost:" + port + "/tft/user/new/hash", new User("userLogin" + i, "userPassword" + i), HttpEntity.class);
			restTemplate.postForEntity("http://localhost:" + port + "/tft/user/hash", new User("userLogin" + i, "userPassword" + i), HttpEntity.class);
		}
	}

	@Test
	public void registerThenLoginTenThousandWithHashes()
	{
		for (int i = 0; i < 10000; i++)
		{
			restTemplate.postForEntity("http://localhost:" + port + "/tft/user/new/hash", new User("userLogin" + i, "userPassword" + i), HttpEntity.class);
			restTemplate.postForEntity("http://localhost:" + port + "/tft/user/hash", new User("userLogin" + i, "userPassword" + i), HttpEntity.class);
		}
	}
}
