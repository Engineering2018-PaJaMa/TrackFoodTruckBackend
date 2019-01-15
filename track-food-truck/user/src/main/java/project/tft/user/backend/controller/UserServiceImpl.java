package project.tft.user.backend.controller;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.tft.db.user.User;
import project.tft.db.user.UserRepository;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
public class UserServiceImpl
{
	@Autowired
	private UserRepository userRepository;

	public boolean registerUserInDatabase(final User user)
	{
		if (!findUserInDatabase(user).isPresent())
		{
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public Optional<Document> findUserInDatabase(final User user)
	{
		return userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
	}
}
