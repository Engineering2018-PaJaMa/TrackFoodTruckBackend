package backend.controller.foodTruck;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backend.dto.FoodTruck;
import backend.mongoDataBase.MongoDBController;

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
				.append("Rating", foodTruck.getRating())
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
