package project.tft.user.backend.controller;

import static project.tft.user.backend.Constants.USER_PATH;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(value = "/{id}")
	public User get(@PathVariable("id") final String id)
	{
		log.info("Returning user with id {}", id);
		return new User(id, "Username", "Password", LocalDate.now().toString(), "Name", "Surname", Integer.valueOf(id) * 10, new HashSet());
	}

	@PutMapping(value = "/{id}")
	public User create(@PathVariable("id") final String id)
	{
		log.info("Creating user with id: {}", id);
		User user = new User(id, "Username", "Password", LocalDate.now().toString(), "Name", "Surname", Integer.valueOf(id) * 10, new HashSet());
		userService.createUser(user);
		return user;
	}
}
