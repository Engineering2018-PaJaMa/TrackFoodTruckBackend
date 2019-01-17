import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hash
{
	private String hash;
	private String salt;
}
