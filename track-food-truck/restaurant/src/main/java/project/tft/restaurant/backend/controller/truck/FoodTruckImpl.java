package project.tft.restaurant.backend.controller.truck;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;

import project.tft.db.MongoDBController;
import project.tft.restaurant.backend.Converter;
import project.tft.restaurant.backend.dto.FoodTruck;

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

	public List<Document> getFoodtruck(final Document foodTruck)
	{
		FindIterable<Document> documentList = mongoDBController.getDatabase().getCollection("Restaurants").find(foodTruck);
		List<Document> list = new ArrayList<>();

		for (Document d : documentList)
		{
			list.add(d);
		}
		return list;
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
