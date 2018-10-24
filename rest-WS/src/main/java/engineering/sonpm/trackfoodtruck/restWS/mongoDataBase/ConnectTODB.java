package engineering.sonpm.trackfoodtruck.restWS.mongoDataBase;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectTODB
{

	private static MongoClient mongo = new MongoClient("localhost", 27017);
	private static MongoCredential credential;
	public static MongoDatabase database = mongo.getDatabase("TrackFoodTruck");

	public static void main(String args[])
	{
		credentialsForDataBase();
		listAllCollection();
	}

	private static void credentialsForDataBase()
	{

		// Creating Credentials
		credential = MongoCredential.createCredential("FoodTrackMongo", "TrackFoodTruck", "AdminFoodTrack".toCharArray());
		System.out.println("Connected to the database successfully");
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
