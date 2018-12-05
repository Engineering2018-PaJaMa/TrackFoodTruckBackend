package project.tft.restaurant.backend.controller.owner;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.tft.db.MongoDBController;

/**
 * Created by Paweł Szopa on 05/12/2018
 */
@Component
public class OwnerServiceImpl
{
	@Autowired
	private MongoDBController mongoDBController;

	public Document createOwner(final Document owner)
	{
		mongoDBController.getDatabase().getCollection("Owners").insertOne(owner);
		return getOwner(owner);
	}

	public Document getOwner(final Document owner)
	{
		Optional<Document> document = Optional.ofNullable(mongoDBController.getDatabase().getCollection("Owners").find(owner).first());
		return document.get();
	}
}
