package project.tft.jwt;

/**
 * Created by Paweł Szopa on 22/01/2019
 */
public interface AuthorizationService
{
	String generateToken(String login);

	boolean validateToken(String token);
}
