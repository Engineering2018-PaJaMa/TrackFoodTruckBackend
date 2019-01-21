package project.tft.salter;

/**
 * Created by Paweł Szopa on 17/01/2019
 */
public interface SalterService
{
	String generateSalt32();

	String generateSalt64();

	String generateSalt128();

	String generateSalt256();

	String generateSalt512();
}
