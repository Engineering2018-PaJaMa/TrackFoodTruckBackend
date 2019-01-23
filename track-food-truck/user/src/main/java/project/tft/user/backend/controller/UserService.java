package project.tft.user.backend.controller;

import java.util.Optional;

import org.bson.Document;

import project.tft.db.user.User;
import project.tft.db.user.UserWithSalt;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface UserService
{
	boolean registerUserInDatabase(User user);

	boolean registerUserInDatabaseWithHash(UserWithSalt user);

	Optional<Document> findUserInDatabaseByLogin(User user);

	Optional<Document> findHashedUserInDatabaseByLogin(User user);

	Optional<Document> findUserInDatabaseByLoginAndPassword(User user);

	Optional<Document> findUserInDatabaseByLoginAndHashedPassword(User user);

	void deleteAll();

	void deleteAllHashed();
}
