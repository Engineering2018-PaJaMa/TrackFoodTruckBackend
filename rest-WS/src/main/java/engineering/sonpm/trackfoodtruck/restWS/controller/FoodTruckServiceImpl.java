package engineering.sonpm.trackfoodtruck.restWS.controller;

import static engineering.sonpm.trackfoodtruck.restWS.Constants.FOOD_TRUCK_PATH;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import engineering.sonpm.trackfoodtruck.restWS.dto.FoodTruck;
import engineering.sonpm.trackfoodtruck.restWS.dto.Location;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
@Slf4j
@RestController
@RequestMapping(value = FOOD_TRUCK_PATH)
public class FoodTruckServiceImpl implements FoodTruckService
{
	@GetMapping(value = "/{id}")
	@Override
	public FoodTruck getFoodTruck(@PathVariable("id") final String id)
	{
		log.info("Returning user with id {}", id);
		return new FoodTruck(id, "FoodTruckName", "FoodTruckOwner", "Delicious", "QuickTastyNotSoCheap", new Location("PL", "Łódź", 10, 20));
	}
}
