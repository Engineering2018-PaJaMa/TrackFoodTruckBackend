package engineering.sonpm.trackfoodtruck.restWS;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.MongoDBController;

public class DataBaseTests
{
	private MongoDBController mongoDBController = new MongoDBController();
	private MongoDatabase database = mongoDBController.getDatabase();
	private MongoCollection<Document> usersCollection = database.getCollection("Users");

	private Document testDocument = new Document().append("username", "newUsername").append("password", "test123").append("last_login", "May 14");

	@Test
	public void newDocumentTest()
	{

		mongoDBController.setDocument(usersCollection, testDocument);
	}

	@Test
	public void deleteDocumentTest()
	{
		mongoDBController.deleteDocument(usersCollection, testDocument);
	}
}
