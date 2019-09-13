package project.tft.jwt;

import project.tft.jwt.AuthorizationServiceImpl.TokenWithStatus;

/**
 * Created by Paweł Szopa on 22/01/2019
 */
public interface AuthorizationService {

    String generateToken(String login, String role);

    TokenWithStatus validateToken(String token);
}
