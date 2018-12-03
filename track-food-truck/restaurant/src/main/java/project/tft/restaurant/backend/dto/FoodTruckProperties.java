package project.tft.restaurant.backend.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 25/11/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodTruckProperties
{
	@NotNull
	private String name;
	@NotNull
	private Location location;
}
