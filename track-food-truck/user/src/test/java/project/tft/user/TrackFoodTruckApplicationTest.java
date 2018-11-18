package project.tft.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import project.tft.user.backend.application.TrackFoodTruckApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrackFoodTruckApplication.class)
@ComponentScan(basePackages = {"project.tft.user.backend", "project.tft.restaurant.backend"})
public class TrackFoodTruckApplicationTest
{
	@Test
	public void contextLoads()
	{
	}
}
