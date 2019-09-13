package project.tft.user.backend.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.tft.db.user.User;
import project.tft.db.user.UserWithSalt;
import project.tft.jwt.AuthorizationService;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Slf4j
@RestController
@RequestMapping(value = "/tft/user")
public class UserServiceEndpoint {

    private final static String USER_ROLE = "USER";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/new")
    public ResponseEntity registerUser(@RequestBody @Valid final User user) {
        if (userService.registerUserInDB(user)) {
            return ok().build();
        }
        return unprocessableEntity().build();
    }

    @PostMapping("/new/hash")
    public ResponseEntity registerUserWithHash(@RequestBody @Valid UserWithSalt user) {
        if (userService.registerUserInDBWithHash(user)) {
            return ok().build();
        }
        return unprocessableEntity().build();
    }

    @PostMapping("/new/authorize")
    public ResponseEntity registerUserAndGenerateToken(@RequestBody @Valid UserWithSalt user) {
        log.info("Registering user:{}", user.getLogin());
        if (userService.registerUserInDBWithHash(user)) {
            String token = authorizationService.generateToken(user.getLogin(), USER_ROLE);
            userService.saveUserToken(user, token);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(AUTHORIZATION, token);
            return ok().headers(httpHeaders).build();
        }
        return unprocessableEntity().build();
    }

    @PostMapping
    public ResponseEntity loginUserWithoutHash(@RequestBody @Valid final User user) {
        if (userService.findUserInDBWithLoginAndPassword(user).isPresent()) {
            return ok().build();
        }
        return status(UNAUTHORIZED).build();
    }

    @PostMapping("/authorize")
    public ResponseEntity loginUser(@RequestBody @Valid UserWithSalt user) {
        log.info("Logging user:{}", user.getLogin());
        if (userService.findUserWithHashInDB(user).isPresent()) {
            String token = userService.saveOrRetriveUserToken(user);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(AUTHORIZATION, token);
            return ok().headers(httpHeaders).build();
        }
        return status(UNAUTHORIZED).build();
    }

    @PostMapping("/favourites")
    public ResponseEntity findFavourites(@RequestHeader(name = "Authorization") String token) {
        log.info("Finding favourites for token:{}", token);
        if (authorizationService.validateToken(token).isValid()) {
            return ok().build();
        }
        return status(UNAUTHORIZED).build();
    }


    @DeleteMapping("all")
    public void deleteAllUsers() {
        userService.deleteAll();
    }

    @DeleteMapping("all/hash")
    public void deleteAllHashedUsers() {
        userService.deleteAllHashed();
    }
}