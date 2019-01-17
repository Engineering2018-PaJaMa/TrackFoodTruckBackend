import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
@Component
public class Hasher implements HasherService
{
	@Autowired
	private Salter salter;

	@Override
	public Hash encrypt(final String toEncrypt)
	{
		try
		{
			Hash hash = new Hash();
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			hash.setSalt(salter.generateSalt());
			messageDigest.update(hash.getSalt().getBytes());
			byte[] encrypted = messageDigest.digest(toEncrypt.getBytes(StandardCharsets.UTF_8));
			hash.setHash(Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted));
			return hash;
		}
		catch (NoSuchAlgorithmException e)
		{
			return null;
		}
		finally
		{
			clearData(toEncrypt);
		}
	}

	@Override
	public boolean matches(final String rawPassword, final Hash hash)
	{
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.update(hash.getSalt().getBytes());
			byte[] encrypted = messageDigest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
			return hash.getHash().equals(Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted));
		}
		catch (NoSuchAlgorithmException e)
		{
			return false;
		}
		finally
		{
			clearData(rawPassword);
		}
	}

	public void clearData(final String data)
	{
		if (data == null)
		{
			return;
		}
		Arrays.fill(data.toCharArray(), '0');
	}
}
