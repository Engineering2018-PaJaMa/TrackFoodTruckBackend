/**
 * Created by Paweł Szopa on 17/01/2019
 */
public interface HasherService
{
	Hash encrypt(String toEncrypt);

	boolean matches(String rawPassword, Hash hash);
}
