package project.tft.user.backend.converter;

import org.bson.Document;
import org.springframework.stereotype.Component;

import project.tft.user.backend.dto.User;

/**
 * Created by Paweł Szopa on 20/11/2018
 */
@Component
public final class UserConverter
{
	public Document convert(final User user)
	{
		return new Document("login", user.getLogin()).append("password", user.getPassword()).append("email", user.getEmail());
	}
}
