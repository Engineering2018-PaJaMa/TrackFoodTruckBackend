package project.tft.db.user;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
	@NotNull
	private String login;

	@NotNull
	private String password;

	private String email;
}
