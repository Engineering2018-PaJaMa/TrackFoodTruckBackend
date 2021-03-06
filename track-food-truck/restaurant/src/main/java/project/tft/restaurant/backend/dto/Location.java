package project.tft.restaurant.backend.dto;

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
public class Location
{
	private String country;
	private String city;
	@NotNull
	private double latitude;
	@NotNull
	private double longitude;
}
