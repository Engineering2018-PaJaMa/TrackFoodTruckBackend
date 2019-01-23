package project.tft.user.backend.controller;

import static project.tft.user.backend.Constants.USER_PATH;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.tft.db.user.User;
import project.tft.db.user.UserWithSalt;
import project.tft.jwt.AuthorizationService;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@RestController
@RequestMapping(value = USER_PATH)
public class UserServiceEndpoint
{
	@Autowired
	private UserService userService;

	@Autowired
	private AuthorizationService authorizationService;

	@PostMapping("/new")
	public ResponseEntity registerUser(@RequestBody @Valid final User user)
	{
		if (userService.registerUserInDatabase(user))
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/new/authorize")
	public ResponseEntity registerUserAndGenerateToken(@RequestHeader("Login") final String login, @RequestHeader("Password") final String password)
	{
		if (userService.registerUserInDatabaseWithHash(new UserWithSalt(login, password)))
		{
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", "Bearer " + authorizationService.generateToken(login));
			return ResponseEntity.ok().headers(httpHeaders).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/new/hash")
	public ResponseEntity registerUserWithHash(@RequestBody @Valid final UserWithSalt user)
	{
		if (userService.registerUserInDatabaseWithHash(user))
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping
	public ResponseEntity loginUser(@RequestBody @Valid final User user)
	{
		if (userService.findUserInDatabaseByLoginAndPassword(user).isPresent())
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/hash")
	public ResponseEntity loginUserWithHash(@RequestBody @Valid final User user)
	{
		if (userService.findUserInDatabaseByLoginAndHashedPassword(user).isPresent())
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/authorize")
	public ResponseEntity loginUserWithHashAndGenerateToken(@RequestHeader("Login") final String login, @RequestHeader("Password") final String password)
	{
		if (userService.findUserInDatabaseByLoginAndHashedPassword(new UserWithSalt(login, password)).isPresent())
		{
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", "Bearer " + authorizationService.generateToken(login));
			return ResponseEntity.ok().headers(httpHeaders).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("all")
	public void deleteAllUsers()
	{
		userService.deleteAll();
	}

	@DeleteMapping("all/hash")
	public void deleteAllHashedUsers()
	{
		userService.deleteAllHashed();
	}
}
