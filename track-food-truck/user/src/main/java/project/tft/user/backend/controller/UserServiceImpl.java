package project.tft.user.backend.controller;

import java.time.LocalDate;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.tft.db.MongoDBController;
import project.tft.user.backend.dto.User;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
public class UserServiceImpl
{
	@Autowired
	private MongoDBController mongoDBController;

	public void createUser(final User user)
	{
		Document document = new Document().append("username", user.getUsername()).append("password", user.getPassword()).append("lastlogin", LocalDate.now());

		mongoDBController.setUpConnection();
		mongoDBController.setDocument(mongoDBController.getCollection("Users"), document);
	}
}
