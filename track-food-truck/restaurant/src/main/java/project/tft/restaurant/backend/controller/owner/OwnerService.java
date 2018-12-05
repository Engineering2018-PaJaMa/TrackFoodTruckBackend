package project.tft.restaurant.backend.controller.owner;

import org.bson.Document;

/**
 * Created by Paweł Szopa on 05/12/2018
 */
public interface OwnerService
{
	Document registerOwner(Document owner);

	Document loginOwner(Document owner);
}
