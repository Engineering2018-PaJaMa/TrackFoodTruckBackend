package project.tft.user.backend.controller;

import org.bson.Document;

import project.tft.user.backend.dto.UserProperties;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface UserService
{
	Document registerUser(Document user);

	Document loginUser(Document user);

	Document addFavouriteFoodTruck(UserProperties userProperties);

	Document deleteFavouriteFoodTruck(UserProperties userProperties);
}
