package engineering.sonpm.trackfoodtruck.restWS.mongoDataBase;

import java.util.Arrays;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.Getter;

public class MongoDBController
{
	@Value("${mongodb.userName}")
	private String userName;
	@Value("${mongodb.databaseName}")
	private String databaseName;
	@Value("${mongodb.password}")
	private String password;

	private MongoCredential credential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
	private MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));

	@Getter
	private MongoDatabase database = mongo.getDatabase(databaseName);

	public MongoCollection<Document> getCollection(String collectionName)
	{
		return database.getCollection(collectionName);
	}

	public void setDocument(MongoCollection<Document> collectionForDoc, Document docForSet)
	{
		collectionForDoc.insertOne(docForSet);
	}

	public void deleteDocument(MongoCollection<Document> collectionForDoc, Document docForDel)
	{
		collectionForDoc.deleteOne(docForDel);
	}

	private void listAllCollection()
	{
		for (String name : database.listCollectionNames())
		{
			System.out.println(name);
		}
	}
	//TODO: Reszta funkcji oraz zabezpieczeń
}
