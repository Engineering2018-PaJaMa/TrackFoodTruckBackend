package backend.controller;

import backend.dto.FoodTruck;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface FoodTruckService
{
	FoodTruck getFoodTruck(final String id);
}
