package project.tft.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Paweł Szopa on 16/01/2019
 */
@Configuration
public class TestConfig
{
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
