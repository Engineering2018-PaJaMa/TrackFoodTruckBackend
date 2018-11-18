package project.tft.restaurant.backend.controller.foodTruck;

import org.bson.Document;

import project.tft.restaurant.backend.dto.FoodTruck;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface FoodTruckService
{
	Document getFoodTruck(final String id);

	FoodTruck setFoodTrack(final String id);
}