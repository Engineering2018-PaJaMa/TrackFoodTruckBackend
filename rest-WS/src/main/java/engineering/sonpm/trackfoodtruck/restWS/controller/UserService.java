package engineering.sonpm.trackfoodtruck.restWS.controller;

import engineering.sonpm.trackfoodtruck.restWS.dto.User;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface UserService
{
	User getUser(final String id);
}
