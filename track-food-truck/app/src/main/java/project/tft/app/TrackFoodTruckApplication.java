package project.tft.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "project.tft")
@EnableMongoRepositories(basePackages = "project.tft.db")
public class TrackFoodTruckApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TrackFoodTruckApplication.class, args);
	}
}
