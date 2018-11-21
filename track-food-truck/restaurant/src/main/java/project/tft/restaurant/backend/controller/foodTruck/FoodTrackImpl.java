package project.tft.restaurant.backend.controller.foodTruck;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.tft.db.MongoDBController;
import project.tft.restaurant.backend.dto.FoodTruck;

@Component
public class FoodTrackImpl
{
	@Autowired
	private MongoDBController mongoDBController;

	public void createFoodtruck(final FoodTruck foodTruck)
	{
		Document document = new Document().append("Name", foodTruck.getName())
				.append("Description", foodTruck.getDescription())
				.append("Photo", foodTruck.getPhoto())
				.append("Rating", foodTruck.getRating())
				.append("Rest_ID", foodTruck.getId())
				.append("Reviews", foodTruck.getReviews());

		mongoDBController.getDatabase().getCollection("Restaurants").insertOne(document);
	}

	public Document getFoodtruck(final String restaurantName)
	{
		return mongoDBController.getDatabase().getCollection("Restaurants").find(eq("Name", restaurantName)).first();
	}
}
