package project.tft.jwt;

import org.springframework.context.annotation.Bean;

/**
 * Created by Paweł Szopa on 22/01/2019
 */
public class TestConfig
{
	@Bean
	public AuthorizationService authorizationService()
	{
		return new AuthorizationServiceImpl();
	}

	@Bean
	public StoredKeyPair storedKeyPair()
	{
		return new StoredKeyPair();
	}
}
