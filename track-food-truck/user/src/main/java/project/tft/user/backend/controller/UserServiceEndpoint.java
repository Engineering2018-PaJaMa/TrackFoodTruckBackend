package project.tft.user.backend.controller;

import static project.tft.user.backend.Constants.USER_PATH;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.tft.user.backend.dto.User;

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

	@GetMapping
	@Override
	public project.tft.user.backend.dao.User login(@RequestBody @Valid final User user)
	{
		return userService.findUserInDatabase(user);
	}

	@PutMapping
	@Override
	public void register(@RequestBody @Valid final User user)
	{
		userService.registerUserInDatabase(user);
	}
}
