package project.tft.user.backend.controller;

import static project.tft.user.backend.Constants.USER_PATH;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.tft.db.user.User;
import project.tft.db.user.UserWithSalt;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
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
		if (userService.registerUserInDatabase(user))
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/new/hash")
	@Override
	public ResponseEntity registerUserWithHash(@RequestBody @Valid final UserWithSalt user)
	{
		if (userService.registerUserInDatabaseWithHash(user))
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping
	@Override
	public ResponseEntity loginUser(@RequestBody @Valid final User user)
	{
		if (userService.findUserInDatabaseByLoginAndPassword(user).isPresent())
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/hash")
	@Override
	public ResponseEntity loginUserWithHash(@RequestBody @Valid final User user)
	{
		if (userService.findUserInDatabaseByLoginAndHashedPassword(user).isPresent())
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("all")
	@Override
	public void deleteAllUsers()
	{
		userService.deleteAll();
	}
}
