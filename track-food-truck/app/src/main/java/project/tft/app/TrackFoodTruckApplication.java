package project.tft.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "project.tft")
public class TrackFoodTruckApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TrackFoodTruckApplication.class, args);
	}
}
