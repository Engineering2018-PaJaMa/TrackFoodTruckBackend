package engineering.sonpm.trackfoodtruck.restWS.mongoDataBase;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBController
{
	private static MongoCredential credential = MongoCredential.createCredential("admin", "TrackFoodTruck", "Admin123".toCharArray());
	private static MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));
	public static MongoDatabase database = mongo.getDatabase("TrackFoodTruck");

	public static void main(String args[])
	{
		listAllCollection();
	}

	public MongoCollection<Document> getCollection(String collectionName)
	{
		return database.getCollection(collectionName);
	}

	public static void setDocument(MongoCollection<Document> collectionForDoc, Document docForSet)
	{
		collectionForDoc.insertOne(docForSet);
	}

	public static void deleteDocument(MongoCollection<Document> collectionForDoc, Document docForDel)
	{
		collectionForDoc.deleteOne(docForDel);
	}

	private static void listAllCollection()
	{
		for (String name : database.listCollectionNames())
		{
			System.out.println(name);
		}
	}
	//TODO: Reszta funkcji oraz zabezpieczeń
}
