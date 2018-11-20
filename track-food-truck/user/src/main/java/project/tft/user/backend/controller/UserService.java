package project.tft.user.backend.controller;

import project.tft.user.backend.dto.User;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface UserService
{
	void register(User user);

	project.tft.user.backend.dao.User login(User user);
}
