package backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("engineering.sonpm.trackfoodtruck.restWS")
public class TrackFoodTruckApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TrackFoodTruckApplication.class, args);
	}
}
