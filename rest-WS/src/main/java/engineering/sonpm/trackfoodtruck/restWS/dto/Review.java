package engineering.sonpm.trackfoodtruck.restWS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review
{
	@NonNull
	private String headline;

	private String body;

	private Integer rating;

	private String author_name;

	private String rest_id;

}
