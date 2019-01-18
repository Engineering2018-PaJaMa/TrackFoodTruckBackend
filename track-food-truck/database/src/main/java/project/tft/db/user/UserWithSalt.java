package project.tft.db.user;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 18/01/2019
 */
@Document(collection = "UsersWithHash")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserWithSalt extends User
{
	private String salt;
}
