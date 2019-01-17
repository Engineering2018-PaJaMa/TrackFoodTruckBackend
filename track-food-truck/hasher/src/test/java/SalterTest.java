import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
public class SalterTest
{
	@Test
	public void salterTest()
	{
		//given
		Salter salter = new Salter();

		//when
		String salt = salter.generateSalt();
		String password = "password";

		//then
		assertThat(password.concat(salt)).isEqualTo("password".concat(salt));
	}
}
