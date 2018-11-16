package restaurant.backend.controller.foodTruck;

import static user.backend.Constants.FOOD_TRUCK_PATH;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restaurant.backend.dto.FoodTruck;
import restaurant.backend.dto.Location;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
@Slf4j
@RestController
@RequestMapping(value = FOOD_TRUCK_PATH)
public class FoodTruckServiceEndpoint implements FoodTruckService
{
	@Autowired
	FoodTrackImpl foodTruckService;

	@GetMapping(value = "/{id}")
	@Override
	public Document getFoodTruck(@PathVariable("id") final String id)
	{
		log.info("Returning foodtrack with id {}", id);
		return foodTruckService.getFoodtruck(id);
	}

	@GetMapping(value = "/{id}")
	@Override
	public FoodTruck setFoodTrack(String id)
	{
		log.info("Creating foodtrack with id {}", id);
		FoodTruck foodtrack = new FoodTruck(
				id,
				"FoodTruckName",
				"FoodTruckOwner",
				"Delicious",
				"QuickTastyNotSoCheap",
				new Location("PL", "Łódź", 10, 20),
				"Url to photo",
				5.5,
				id + "FoodTruckName");
		foodTruckService.createFoodtruck(foodtrack);
		return foodtrack;
	}
}