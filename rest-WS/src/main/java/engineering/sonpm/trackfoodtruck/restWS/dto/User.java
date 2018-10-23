package engineering.sonpm.trackfoodtruck.restWS.dto;

import java.util.HashSet;

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

	private String name;

	private String surname;

	private int age;

	private HashSet favouriteFoodTrucks;
}
