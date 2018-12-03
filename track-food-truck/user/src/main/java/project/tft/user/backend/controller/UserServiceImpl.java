package project.tft.user.backend.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.tft.db.MongoDBController;
import project.tft.user.backend.dto.UserProperties;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
public class UserServiceImpl
{
	@Autowired
	private MongoDBController mongoDBController;

	public Document registerUserInDatabase(final Document user)
	{
		mongoDBController.getDatabase().getCollection("Users").insertOne(user);
		return findUserInDatabase(user);
	}

	public Document findUserInDatabase(final Document user)
	{
		return mongoDBController.getDatabase().getCollection("Users").find(user).first();
	}

	public Document addFavouriteFoodTruck(final UserProperties userProperties)
	{
		for (String foodTruck : userProperties.getFavouriteFoodTrucks())
		{
			mongoDBController.getDatabase()
					.getCollection("Users")
					.findOneAndUpdate(new org.bson.Document("login", userProperties.getName()),
							new org.bson.Document("$push", new Document("favouriteFoodTrucks", foodTruck)));
		}
		return findUserInDatabase(new Document("login", userProperties.getName()));
	}

	public Document deleteFavouriteFoodTruck(final UserProperties userProperties)
	{
		for (String foodTruck : userProperties.getFavouriteFoodTrucks())
		{
			mongoDBController.getDatabase()
					.getCollection("Users")
					.findOneAndUpdate(new org.bson.Document("login", userProperties.getName()),
							new org.bson.Document("$pull", new Document("favouriteFoodTrucks", foodTruck)));
		}
		return findUserInDatabase(new Document("login", userProperties.getName()));
	}
}
