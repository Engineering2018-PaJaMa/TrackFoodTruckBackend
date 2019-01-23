package project.tft.user.backend.controller;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import project.tft.db.user.HashedUserRepository;
import project.tft.db.user.User;
import project.tft.db.user.UserRepository;
import project.tft.db.user.UserWithSalt;
import project.tft.hasher.Hash;
import project.tft.hasher.HasherService;
import project.tft.jwt.StoredKeyPair;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
@Slf4j
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HashedUserRepository hashedUserRepository;

	@Autowired
	private HasherService hasher;

	@Autowired
	private StoredKeyPair keyPair;

	@Override
	public boolean registerUserInDatabase(final User user)
	{
		if (!findUserInDatabaseByLogin(user).isPresent())
		{
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean registerUserInDatabaseWithHash(final UserWithSalt user)
	{
		if (!findHashedUserInDatabaseByLogin(user).isPresent())
		{
			Hash hash = hasher.encrypt(user.getPassword());
			user.setPassword(hash.getHash());
			user.setSalt(hash.getSalt());
			hashedUserRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public Optional<Document> findUserInDatabaseByLogin(final User user)
	{
		return userRepository.findByLogin(user.getLogin());
	}

	@Override
	public Optional<Document> findHashedUserInDatabaseByLogin(final User user)
	{
		return hashedUserRepository.findByLogin(user.getLogin());
	}

	@Override
	public Optional<Document> findUserInDatabaseByLoginAndPassword(final User user)
	{
		return userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
	}

	@Override
	public Optional<Document> findUserInDatabaseByLoginAndHashedPassword(final User user)
	{
		Optional<Document> userWithSalt = hashedUserRepository.findByLogin(user.getLogin());
		if (userWithSalt.isPresent())
		{
			Hash hash = new Hash(userWithSalt.get().get("password").toString(), userWithSalt.get().get("salt").toString());
			if (hasher.matches(user.getPassword(), hash))
			{
				return hashedUserRepository.findByLoginAndPassword(user.getLogin(), hash.getHash());
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteAll()
	{
		userRepository.deleteAll();
	}

	@Override
	public void deleteAllHashed()
	{
		hashedUserRepository.deleteAll();
	}
}
