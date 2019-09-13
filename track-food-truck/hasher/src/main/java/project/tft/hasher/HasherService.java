package project.tft.hasher;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
public interface HasherService
{
	SaltedHash encrypt(String toEncrypt);

	boolean matches(String rawPassword, SaltedHash saltedHash);
}
