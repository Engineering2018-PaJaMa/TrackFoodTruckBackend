package project.tft.db.user;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Document(collection = "Users")
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
