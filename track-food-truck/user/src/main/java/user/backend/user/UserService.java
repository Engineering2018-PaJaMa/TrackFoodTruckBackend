package user.backend.user;

import user.backend.dto.User;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface UserService
{
	User get(final String id);

	User create(final String id);
}