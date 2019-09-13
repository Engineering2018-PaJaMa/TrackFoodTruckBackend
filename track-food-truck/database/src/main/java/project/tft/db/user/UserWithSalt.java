package project.tft.db.user;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Paweł Szopa on 18/01/2019
 */
@Document(collection = "UsersWithHash")
@Data
@NoArgsConstructor
public class UserWithSalt {

    @Id
    public ObjectId _id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    private String salt;

    private String token;

    private String role;
}
