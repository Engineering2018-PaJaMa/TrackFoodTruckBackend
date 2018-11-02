package engineering.sonpm.trackfoodtruck.restWS.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.MongoDBController;

@SpringBootApplication
@ComponentScan("engineering.sonpm.trackfoodtruck.restWS")
public class RestWsApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(RestWsApplication.class, args);
		new MongoDBController().initializeDataBase();
	}
}
