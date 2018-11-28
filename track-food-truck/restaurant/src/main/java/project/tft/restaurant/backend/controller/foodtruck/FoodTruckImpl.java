package project.tft.restaurant.backend.controller.foodtruck;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;

import project.tft.db.MongoDBController;
import project.tft.restaurant.backend.Converter;
import project.tft.restaurant.backend.dto.FoodTruck;
import project.tft.restaurant.backend.dto.FoodTruckProperties;

@Component
public class FoodTruckImpl
{
	@Autowired
	private MongoDBController mongoDBController;

	@Autowired
	private Converter converter;

	public void createFoodtruck(final FoodTruck foodTruck)
	{
		mongoDBController.getDatabase().getCollection("Restaurants").insertOne(converter.convert(foodTruck));
	}

	public Document getFoodtruck(final Document foodTruck)
	{
		return mongoDBController.getDatabase().getCollection("Restaurants").find(foodTruck).first();
	}

	public void changeFoodTruckLocation(final FoodTruckProperties foodTruckProperties)
	{
		mongoDBController.getDatabase()
				.getCollection("Restaurants")
				.findOneAndUpdate(new Document("name", foodTruckProperties.getName()),
						new Document("$set",
								new Document("location.country", foodTruckProperties.getLocation().getCountry()).append("location.city",
										foodTruckProperties.getLocation().getCity())
										.append("location.latitude", foodTruckProperties.getLocation().getLatitude())
										.append("location.longitude", foodTruckProperties.getLocation().getLongitude())));
	}

	public List<Document> getAllFoodTrucks()
	{
		FindIterable<Document> documentList = mongoDBController.getDatabase().getCollection("Restaurants").find();
		List<Document> list = new ArrayList<>();

		for (Document d : documentList)
		{
			list.add(d);
		}
		return list;
	}
}
