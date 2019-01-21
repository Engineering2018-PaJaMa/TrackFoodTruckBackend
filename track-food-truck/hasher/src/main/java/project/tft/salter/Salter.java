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
	public String generateSalt32()
	{
		byte[] bytes = new byte[32];
		random.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	@Override
	public String generateSalt64()
	{
		byte[] bytes = new byte[64];
		random.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	@Override
	public String generateSalt128()
	{
		byte[] bytes = new byte[128];
		random.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	@Override
	public String generateSalt256()
	{
		byte[] bytes = new byte[256];
		random.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	@Override
	public String generateSalt512()
	{
		byte[] bytes = new byte[512];
		random.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
}
