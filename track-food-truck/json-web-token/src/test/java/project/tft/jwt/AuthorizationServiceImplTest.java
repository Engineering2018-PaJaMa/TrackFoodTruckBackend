package project.tft.jwt;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Paweł Szopa on 22/01/2019
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AuthorizationServiceImplTest
{
	@Autowired
	private AuthorizationService authorizationService;

	@Test
	public void generateToken()
	{
		String token = authorizationService.generateToken("username");
		assertThat(token).isNotNull();
	}

	@Test
	public void generateAndValidateToken()
	{
		String token = authorizationService.generateToken("username");
		assertThat(authorizationService.validateToken(token)).isTrue();
	}
}
