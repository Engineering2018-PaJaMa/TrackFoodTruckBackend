package engineering.sonpm.trackfoodtruck.restWS.controller;

import static engineering.sonpm.trackfoodtruck.restWS.Constants.USER_PATH;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import engineering.sonpm.trackfoodtruck.restWS.dto.User;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Slf4j
@RestController
@RequestMapping(value = USER_PATH)
public class UserServiceEndpoint implements UserService
{
	@Autowired
	UserServiceImpl userService;

	@GetMapping(value = "/{id}")
	public User get(@PathVariable("id") final String id)
	{
		log.info("Returning user with id {}", id);
		return new User(id, "Username", "Password", LocalDate.now().toString(), "Name", "Surname", Integer.valueOf(id) * 10, new HashSet());
	}

	@PostMapping(value = "{id}")
	public User create(@PathVariable("id") final String id)
	{
		log.info("Creating user with id: {}", id);
		User user = new User(id, "Username", "Password", LocalDate.now().toString(), "Name", "Surname", Integer.valueOf(id) * 10, new HashSet());
		userService.createUser(user);
		return user;
	}
}