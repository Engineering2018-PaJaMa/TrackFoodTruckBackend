package project.tft.user.backend.controller;

import static project.tft.user.backend.Constants.USER_PATH;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.tft.user.backend.dto.UserProperties;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Slf4j
@RestController
@RequestMapping(value = USER_PATH)
public class UserServiceEndpoint implements UserService
{
	@Autowired
	private UserServiceImpl userService;

	@PutMapping
	@Override
	public Document registerUser(@RequestBody final Document user)
	{
		log.info("Registering user {} in database.", user);
		return userService.registerUserInDatabase(user);
	}

	@PostMapping
	@Override
	public Document loginUser(@RequestBody final Document user)
	{
		log.info("Returning user {} from database.", user);
		return userService.findUserInDatabase(user);
	}

	@PatchMapping(path = "/favourites")
	@Override
	public Document addFavouriteFoodTruck(@RequestBody final UserProperties userProperties)
	{
		log.info("Adding favourite foodTruck {} for user {}", userProperties.getFavouriteFoodTrucks(), userProperties.getName());
		return userService.addFavouriteFoodTruck(userProperties);
	}

	@DeleteMapping(path = "/favourites")
	@Override
	public Document deleteFavouriteFoodTruck(@RequestBody final UserProperties userProperties)
	{
		log.info("Adding favourite foodTruck {} for user {}", userProperties.getFavouriteFoodTrucks(), userProperties.getName());
		return userService.deleteFavouriteFoodTruck(userProperties);
	}
}
