package restaurant.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodTruck
{
	@NonNull
	private String id;

	private String name;

	private String owner;

	private String typeOfFood;

	private String description;

	private Location location;

	private String photo;

	private Double rating;

	private String reviews;
}
