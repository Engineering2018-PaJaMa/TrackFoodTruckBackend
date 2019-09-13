package project.tft.hasher;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import project.tft.salter.Salter;
import project.tft.salter.SalterService;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
public class SalterTest
{
	@Test
	public void salterTest()
	{
		//given
		SalterService salter = new Salter();

		//when
		String salt = salter.generateSalt1024();
		String password = "password";

		//then
		assertThat(password.concat(salt)).isEqualTo("password".concat(salt));
	}
}
