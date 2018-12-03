package project.tft.user.backend.controller;

import java.util.List;

import org.bson.Document;

import project.tft.user.backend.dto.UserProperties;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface UserService
{
	Document registerUser(Document user);

	Document loginUser(Document user);

	List<String> addFavouriteFoodTruck(UserProperties userProperties);
}
