package project.tft.hasher;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import project.tft.salter.Salter;
import project.tft.salter.SalterService;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
@Component
@Slf4j
public class Hasher implements HasherService
{
	private SalterService salter;

	public Hasher()
	{
		salter = new Salter();
	}

	@Override
	public Hash encrypt(final String toEncrypt)
	{
		try
		{
			Hash hash = new Hash();
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			hash.setSalt(salter.generateSalt32());
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
			log.error("Exception {}", e);
		}
		finally
		{
			clearData(rawPassword);
		}
		return false;
	}

	private void clearData(final String data)
	{
		if (data == null)
		{
			return;
		}

		try
		{
			Field field = String.class.getDeclaredField("value");
			field.setAccessible(true);
			byte[] value = (byte[]) field.get(data);
			Arrays.fill(value, (byte) '0');
		}
		catch (Exception e)
		{
			log.error("Exception {}", e);
		}
	}
}
