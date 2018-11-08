package engineering.sonpm.trackfoodtruck.restWS.controller.foodTrack;

import engineering.sonpm.trackfoodtruck.restWS.dto.FoodTruck;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface FoodTruckService
{
	FoodTruck getFoodTruck(final String id);

	FoodTruck setFoodTrack(final String id);
}
