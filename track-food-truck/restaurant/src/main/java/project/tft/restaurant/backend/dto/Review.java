package project.tft.restaurant.backend.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review
{
	@NotNull
	private String restaurantName;

	@NotNull
	private String headline;

	private String body;

	private Double rating;

	private String author;
}
