package project.tft.db.user;

import java.util.Optional;

import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paweł Szopa on 15/01/2019
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>
{
	Optional<Document> findByLoginAndPassword(String login, String password);
}
