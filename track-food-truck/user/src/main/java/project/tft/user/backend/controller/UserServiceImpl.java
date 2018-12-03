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

	public void addFavouriteFoodTruck(final UserProperties userProperties)
	{
		mongoDBController.getDatabase().getCollection("Users").findOneAndUpdate(
				new org.bson.Document("login", userProperties.getName()),
				new org.bson.Document("$set", new Document("favouriteFoodTrucks", userProperties.getFavouriteFoodTrucks())));
	}
}
