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
	boolean registerUserInDB(User user);

	boolean registerUserInDBWithHash(UserWithSalt user);

	Optional<Document> findUserInDatabaseByLogin(User user);

	Optional<Document> findHashedUserInDatabaseByLogin(UserWithSalt user);

	Optional<Document> findUserInDBWithLoginAndPassword(User user);

	Optional<Document> findUserInDB(User user);

	Optional<Document> findUserWithHashInDB(UserWithSalt user);

	void deleteAll();

	void deleteAllHashed();

    String saveOrRetriveUserToken(UserWithSalt user);

	String saveUserToken(UserWithSalt user, String token);
}
