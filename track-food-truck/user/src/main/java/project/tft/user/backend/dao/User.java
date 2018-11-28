package project.tft.user.backend.dao;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 31/10/2018
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

	@NotNull
	private String email;

	private List<String> favouriteFoodTrucks;
}
