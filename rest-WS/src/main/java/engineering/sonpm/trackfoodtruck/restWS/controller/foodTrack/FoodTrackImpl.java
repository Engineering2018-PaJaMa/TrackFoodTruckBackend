package engineering.sonpm.trackfoodtruck.restWS.controller.foodTrack;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import engineering.sonpm.trackfoodtruck.restWS.dto.FoodTruck;
import engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.MongoDBController;

@Component
public class FoodTrackImpl
{
	@Autowired
	MongoDBController mongoDBController;

	public void createFoodtruck(final FoodTruck foodTruck)
	{
		Document document = new Document().append("Name", foodTruck.getName())
				.append("Description", foodTruck.getDescription())
				.append("Photo", foodTruck.getPhoto())
				.append("Rating", foodTruck.getRaiting())
				.append("Rest_ID", foodTruck.getId())
				.append("Reviews", foodTruck.getReviews());

		mongoDBController.setUpConnection();
		mongoDBController.setDocument(mongoDBController.getCollection("Restaurants"), document);
	}

	public Document getFoodtruck(final String restaurantName)
	{
		return mongoDBController.getRestaurant(mongoDBController.getCollection("Restaurants"), restaurantName);
	}
}
