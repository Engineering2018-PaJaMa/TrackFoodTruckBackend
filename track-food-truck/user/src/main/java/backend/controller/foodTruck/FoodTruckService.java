package backend.controller.foodTruck;

import org.bson.Document;

import backend.dto.FoodTruck;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface FoodTruckService
{
	Document getFoodTruck(final String id);

	FoodTruck setFoodTrack(final String id);
}
