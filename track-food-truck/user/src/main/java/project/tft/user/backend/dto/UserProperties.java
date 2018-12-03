package project.tft.user.backend.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 02/12/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProperties
{
	@NotNull
	private String name;

	@NotNull
	private List<String> favouriteFoodTrucks;
}
