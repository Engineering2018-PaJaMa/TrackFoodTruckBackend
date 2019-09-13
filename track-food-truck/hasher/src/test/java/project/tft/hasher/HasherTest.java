package project.tft.hasher;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.tft.salter.Salter;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Hasher.class, Salter.class})
public class HasherTest
{
	@Autowired
	private HasherService hasher;

	@Test
	public void hashingPasswordTest()
	{
		//given
		 String passwordToEncrypt = "passwordToEncrypt";

		//when
		SaltedHash saltedHash = hasher.encrypt(passwordToEncrypt);

		//then
		System.out.println(saltedHash.getHash());
		System.out.println(saltedHash.getSalt());
		assertThat(saltedHash.getHash()).isNotEqualTo(passwordToEncrypt);
	}

	@Test
	public void matchingPasswordTest()
	{
		//given
		 String passwordToEncrypt = "passwordToEncrypt";

		//when
		SaltedHash saltedHash = hasher.encrypt(passwordToEncrypt);

		//then
		assertThat(hasher.matches(passwordToEncrypt, saltedHash)).isTrue();
	}
}
