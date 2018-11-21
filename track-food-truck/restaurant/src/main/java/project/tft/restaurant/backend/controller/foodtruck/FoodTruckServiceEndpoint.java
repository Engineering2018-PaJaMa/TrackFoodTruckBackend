package project.tft.restaurant.backend.controller.foodtruck;

import static project.tft.restaurant.backend.Constants.FOOD_TRUCK_PATH;

import java.util.List;

import javax.validation.Valid;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.tft.restaurant.backend.dto.FoodTruck;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
@Slf4j
@RestController
@RequestMapping(value = FOOD_TRUCK_PATH)
public class FoodTruckServiceEndpoint implements FoodTruckService
{
	@Autowired
	private FoodTruckImpl foodTruckService;

	@PutMapping
	@Override
	public FoodTruck createFoodTruck(@RequestBody @Valid final FoodTruck foodTruck)
	{
		log.info("Creating foodTruck {} in database.", foodTruck);
		foodTruckService.createFoodtruck(foodTruck);
		return foodTruck;
	}

	@GetMapping
	@Override
	public List<Document> findFoodTruck(@RequestBody final Document foodTruck)
	{
		log.info("Returning foodTruck {} from database.", foodTruck);
		return foodTruckService.getFoodtruck(foodTruck);
	}

	@GetMapping("/all")
	public List<Document> findAllFoodTrucks()
	{
		log.info("Returning all foodTrucks from database.");
		return foodTruckService.getAllFoodTrucks();
	}
}
