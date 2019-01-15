package project.tft.user.backend.controller;

import static project.tft.user.backend.Constants.USER_PATH;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.tft.db.user.User;

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

	@PostMapping("/new")
	@Override
	public ResponseEntity registerUser(@RequestBody @Valid final User user)
	{
		try
		{
			userService.registerUserInDatabase(user);
			log.info("Registering user {} in database.", user.getLogin());
			return ResponseEntity.ok().build();
		}
		catch (Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	@Override
	public ResponseEntity loginUser(@RequestBody @Valid final User user)
	{
		try
		{
			userService.findUserInDatabase(user);
			log.info("Returning user {} from database.", user.getLogin());
			return ResponseEntity.ok().build();
		}
		catch (Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}
}
