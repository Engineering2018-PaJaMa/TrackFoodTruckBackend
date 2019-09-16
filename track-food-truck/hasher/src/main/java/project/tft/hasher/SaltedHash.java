package project.tft.hasher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaltedHash {

    private String hash;
    private String salt;
}
