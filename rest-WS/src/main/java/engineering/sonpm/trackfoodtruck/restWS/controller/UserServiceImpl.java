package engineering.sonpm.trackfoodtruck.restWS.controller;

import static engineering.sonpm.trackfoodtruck.restWS.Constants.USER_PATH;

import java.util.HashSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class UserServiceImpl implements UserService
{
	@GetMapping(value = "/{id}")
	public User getUser(@PathVariable("id") final String id)
	{
		log.info("Returning user with id {}", id);
		return new User(id, "Name", "Surname", Integer.valueOf(id) * 10, new HashSet());
	}
}
