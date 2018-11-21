package project.tft.user.backend.controller;

import org.bson.Document;

import project.tft.user.backend.dto.User;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface UserService
{
	User registerUser(User user);

	Document loginUser(Document user);
}
