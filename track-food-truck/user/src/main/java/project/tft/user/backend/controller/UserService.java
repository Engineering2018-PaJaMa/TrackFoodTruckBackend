package project.tft.user.backend.controller;

import org.springframework.http.ResponseEntity;

import project.tft.db.user.User;
import project.tft.db.user.UserWithSalt;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface UserService
{
	ResponseEntity registerUser(User user);

	ResponseEntity registerUserWithHash(UserWithSalt user);

	ResponseEntity loginUser(User user);

	ResponseEntity loginUserWithHash(User user);

	void deleteAllUsers();
}
