import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import project.tft.hasher.Hash;
import project.tft.hasher.Hasher;
import project.tft.hasher.HasherService;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
public class HasherTest
{
	private HasherService hasher = new Hasher();
	private String passwordToEncrypt = "passwordToEncrypt";

	@Test
	public void hashingPasswordTest()
	{
		//given

		//when
		Hash hash = hasher.encrypt(passwordToEncrypt);

		//then
		System.out.println(hash.getHash());
		System.out.println(hash.getSalt());
		assertThat(hash.getHash()).isNotEqualTo(passwordToEncrypt);
	}

	@Test
	public void matchingPasswordTest()
	{
		//given

		//when
		Hash hash = hasher.encrypt(passwordToEncrypt);

		//then
		assertThat(hasher.matches(passwordToEncrypt, hash)).isTrue();
	}
}
