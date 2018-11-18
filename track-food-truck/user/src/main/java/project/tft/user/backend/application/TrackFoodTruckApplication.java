package project.tft.user.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"project.tft.user.backend", "project.tft.restaurant.backend"})
public class TrackFoodTruckApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TrackFoodTruckApplication.class, args);
	}
}
