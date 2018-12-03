package project.tft.restaurant.backend.controller.foodtruck;

import org.bson.Document;

import project.tft.restaurant.backend.dto.FoodTruckProperties;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface FoodTruckService
{
	Document createFoodTruck(Document foodTruck);

	Document findFoodTruck(Document foodTruck);

	Document changeFoodTruckLocation(FoodTruckProperties foodTruck);
}
