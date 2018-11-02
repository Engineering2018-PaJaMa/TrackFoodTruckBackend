package engineering.sonpm.trackfoodtruck.restWS.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("engineering.sonpm.trackfoodtruck.restWS")
public class RestWsApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(RestWsApplication.class, args);
	}
}
