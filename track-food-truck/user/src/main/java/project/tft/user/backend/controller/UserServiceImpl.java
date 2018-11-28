package project.tft.user.backend.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.tft.db.MongoDBController;
import project.tft.user.backend.converter.UserConverter;
import project.tft.user.backend.dto.User;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
public class UserServiceImpl
{
	@Autowired
	private MongoDBController mongoDBController;

	@Autowired
	private UserConverter converter;

	public void registerUserInDatabase(final User user)
	{
		mongoDBController.getDatabase().getCollection("Users").insertOne(converter.convert(user));
	}

	public User findUserInDatabase(final Document user)
	{
		return converter.convert(mongoDBController.getDatabase().getCollection("Users").find(user).first());
	}
}
