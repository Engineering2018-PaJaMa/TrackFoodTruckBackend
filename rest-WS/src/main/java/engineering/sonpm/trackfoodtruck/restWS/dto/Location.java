package engineering.sonpm.trackfoodtruck.restWS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location
{
	private String country;

	private String city;

	private double latitude;

	private double longitude;
}
