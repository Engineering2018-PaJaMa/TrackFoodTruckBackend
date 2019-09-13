package project.tft.db.user;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Document(collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    public ObjectId _id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    private String email;

    public User(String login, final String password) {
        this.login = login;
        this.password = password;
    }
}
