package project.tft.user.backend.converter;

import java.util.Set;

import org.bson.Document;

import project.tft.user.backend.dto.User;

/**
 * Created by Paweł Szopa on 20/11/2018
 */
public class UserConverter
{
	public Document convert(final User user)
	{
		return new Document("login", user.getLogin()).append("password", user.getPassword())
				.append("repPassword", user.getRepPassword())
				.append("email", user.getEmail());
	}

	public project.tft.user.backend.dao.User convert(final Document document)
	{
		return new project.tft.user.backend.dao.User(
				document.get("login").toString(),
				document.get("password").toString(),
				document.get("email").toString(),
				document.get("name").toString(),
				document.get("surname").toString(),
				document.get("age").toString(),
				Set.of(document.get("favouriteFoodTrucks").toString()));
	}
}
