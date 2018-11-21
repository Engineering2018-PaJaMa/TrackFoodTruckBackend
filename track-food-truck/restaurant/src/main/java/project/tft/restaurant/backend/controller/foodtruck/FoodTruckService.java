package project.tft.restaurant.backend.controller.foodtruck;

import java.util.List;

import org.bson.Document;

import project.tft.restaurant.backend.dto.FoodTruck;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface FoodTruckService
{
	FoodTruck createFoodTruck(FoodTruck foodTruck);

	List<Document> findFoodTruck(Document foodTruck);
}
