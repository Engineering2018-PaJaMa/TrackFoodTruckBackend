package project.tft.salter;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
@Component
public class Salter implements SalterService
{
	private SecureRandom random;

	public Salter()
	{
		random = new SecureRandom();
	}

	@Override
	public String generateSalt()
	{
		byte[] bytes = new byte[32];
		random.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
}
