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
public class FoodTruck
{
	@NotNull
	private String name;

	@NotNull
	private String cuisine;

	@NotNull
	private String description;

	@NotNull
	private Location location;

	private Double rating;
	private String photo;
}
