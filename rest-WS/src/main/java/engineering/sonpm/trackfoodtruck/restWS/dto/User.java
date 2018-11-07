package engineering.sonpm.trackfoodtruck.restWS.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
	@NonNull
	private String id;
	private String username;
	private String password;
	private String lastlogin;
	private String name;
	private String surname;
	private int age;
	private Set<String> favouriteFoodTrucks;
}
