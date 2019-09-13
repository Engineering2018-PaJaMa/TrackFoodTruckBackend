package project.tft.user.backend.controller;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.tft.db.user.HashedUserRepository;
import project.tft.db.user.User;
import project.tft.db.user.UserRepository;
import project.tft.db.user.UserWithSalt;
import project.tft.hasher.HasherService;
import project.tft.hasher.SaltedHash;
import project.tft.jwt.AuthorizationService;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HashedUserRepository hashedUserRepository;

    @Autowired
    private HasherService hasher;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public boolean registerUserInDB(final User user) {
        if (findUserInDatabaseByLogin(user).isEmpty()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean registerUserInDBWithHash(final UserWithSalt user) {
        if (findHashedUserInDatabaseByLogin(user).isEmpty()) {
            SaltedHash saltedHash = hasher.encrypt(user.getPassword());
            user.setPassword(saltedHash.getHash());
            user.setSalt(saltedHash.getSalt());
            hashedUserRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Document> findUserInDatabaseByLogin(final User user) {
        return userRepository.findByLogin(user.getLogin());
    }

    @Override
    public Optional<Document> findHashedUserInDatabaseByLogin(final UserWithSalt user) {
        return hashedUserRepository.findByLogin(user.getLogin());
    }

    @Override
    public Optional<Document> findUserInDBWithLoginAndPassword(final User user) {
        return userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
    }

    @Override
    public Optional<Document> findUserInDB(User user) {
        Optional<Document> userWithSalt = hashedUserRepository.findByLogin(user.getLogin());
        if (userWithSalt.isPresent()) {
            SaltedHash saltedHash = new SaltedHash(userWithSalt.get().get("password").toString(), userWithSalt.get().get("salt").toString());
            if (hasher.matches(user.getPassword(), saltedHash)) {
                return hashedUserRepository.findByLoginAndPassword(user.getLogin(), saltedHash.getHash());
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Document> findUserWithHashInDB(UserWithSalt user) {
        Optional<Document> userWithSalt = hashedUserRepository.findByLogin(user.getLogin());
        if (userWithSalt.isPresent()) {
            SaltedHash saltedHash = new SaltedHash(userWithSalt.get().get("password").toString(), userWithSalt.get().get("salt").toString());
            if (hasher.matches(user.getPassword(), saltedHash)) {
                return hashedUserRepository.findByLoginAndPassword(user.getLogin(), saltedHash.getHash());
            }
        }
        return Optional.empty();
    }

    @Override
    public String saveOrRetriveUserToken(UserWithSalt user) {
        UserWithSalt userWithSalt = hashedUserRepository.findByLogin(user.getLogin(), true);
        String retrievedToken = userWithSalt.getToken();
        if (!authorizationService.validateToken(retrievedToken).isValid()) {
            String token = authorizationService.generateToken(user.getLogin(), userWithSalt.getRole());
            saveUserToken(userWithSalt, token);
            return token;
        }
        return retrievedToken;
    }

    @Override
    public String saveUserToken(UserWithSalt user, String token) {
        user.setToken(token);
        hashedUserRepository.save(user);
        return token;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteAllHashed() {
        hashedUserRepository.deleteAll();
    }
}
