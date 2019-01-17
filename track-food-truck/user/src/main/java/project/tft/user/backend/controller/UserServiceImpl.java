package project.tft.user.backend.controller;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import project.tft.db.user.User;
import project.tft.db.user.UserRepository;
import project.tft.hasher.HasherService;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
@Slf4j
public class UserServiceImpl
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HasherService hasher;

	public boolean registerUserInDatabase(final User user)
	{
		if (!findUserInDatabaseByLogin(user).isPresent())
		{
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public boolean registerUserInDatabaseWithHash(final User user)
	{
		//TODO
		return false;
	}

	public Optional<Document> findUserInDatabaseByLogin(final User user)
	{
		return userRepository.findByLogin(user.getLogin());
	}

	public Optional<Document> findUserInDatabaseByLoginAndPassword(final User user)
	{
		return userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
	}

	public void deleteAll()
	{
		userRepository.deleteAll();
	}
}
