package engineering.sonpm.trackfoodtruck.restWS;

import static engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.MongoDBController.database;
import static engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.MongoDBController.deleteDocument;
import static engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.MongoDBController.setDocument;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.client.MongoCollection;

public class DataBaseTests
{
	//Testy działają na kolekcji: "Users" i przykładowym dokumencie\

	private Document testDocument = new Document().append("username", "newUsername").append("password", "test123").append("last_login", "May 14");

	private MongoCollection<Document> usersCollection = database.getCollection("Users");

	@Test
	public void newDocumentTest()
	{

		setDocument(usersCollection, testDocument);
	}

	@Test
	public void deleteDocumentTest()
	{
		deleteDocument(usersCollection, testDocument);
	}
}
