package project.tft.restaurant.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review
{
	private String restaurantId;

	@NonNull
	private String headline;

	private String body;

	private Double rating;

	private String authorName;
}
