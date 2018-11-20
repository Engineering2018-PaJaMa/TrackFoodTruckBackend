package project.tft.db;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Component
public class MongoDBController
{
	private MongoClient mongo;

	@Getter
	private MongoDatabase database;

	public MongoDBController()
	{
		mongo = new MongoClient(new ServerAddress("localhost", 27017));
		database = mongo.getDatabase("TrackFoodTruck");
	}

	public MongoCollection<Document> getCollection(String collectionName)
	{
		return database.getCollection(collectionName);
	}

	private void setCollection(String collectionName)
	{
		database.createCollection(collectionName);
	}

	public void setDocument(MongoCollection<Document> collectionForDoc, Document docForSet)
	{
		collectionForDoc.insertOne(docForSet);
	}

	public Document getRestaurant(MongoCollection<Document> collectionForDoc, String restaurantName)
	{
		FindIterable<Document> documents = collectionForDoc.find(eq("Name", restaurantName));
		return documents.first();
	}

	public void deleteDocument(MongoCollection<Document> collectionForDoc, Document docForDel)
	{
		collectionForDoc.deleteOne(docForDel);
	}

	public List<Document> showAllReviews(MongoCollection<Document> collectionForDoc, String restaurantName)
	{

		List<Document> arrayToSend = new ArrayList<>();

		FindIterable<Document> goodReviews = collectionForDoc.find(eq("restaurantId", restaurantName));
		for (Document reviews : goodReviews)
		{
			arrayToSend.add(reviews);
		}
		return arrayToSend;
	}

	public void listAllCollection()
	{
		for (String name : database.listCollectionNames())
		{
			System.out.println(name);
		}
	}
}
