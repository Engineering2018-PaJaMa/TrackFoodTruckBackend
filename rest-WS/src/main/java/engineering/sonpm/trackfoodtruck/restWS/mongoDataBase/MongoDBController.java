package engineering.sonpm.trackfoodtruck.restWS.mongoDataBase;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoDBController
{
	@Value("${mongodb.username}")
	private String userName;
	@Value("${mongodb.database}")
	private String databaseName;
	@Value("${mongodb.password}")
	private String password;

	//TODO: Dokończyć credentials
	//private MongoCredential credential = MongoCredential.createCredential("admin", "TrackFoodTruck", "Admin123".toCharArray());
	private MongoClient mongo;

	@Getter
	private MongoDatabase database;

	public void setUpConnection()
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
		Document docToSend = new Document();
		FindIterable<Document> documents = collectionForDoc.find(eq("Name", restaurantName));
		for (Document document : documents)
		{
			docToSend = document;
		}
		return docToSend;
	}

	public void deleteDocument(MongoCollection<Document> collectionForDoc, Document docForDel)
	{
		collectionForDoc.deleteOne(docForDel);
	}

	public List<Document> showAllReviews(MongoCollection<Document> collectionForDoc, String restaurantName)
	{

		List<Document> arrayToSend = new ArrayList<Document>();

		FindIterable<Document> goodReviews = collectionForDoc.find(eq("rest_id", restaurantName));
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

	//Nie potrzebne narazie ale może się przyda
	/*
	public void initializeDataBase()
	{
		setCollection("Users");
		setCollection("Restaurants");
		setCollection("Reviews");

		Document simpleDocument = new Document().append("username", "First user").append("password", "simplepassword123").append("last_login", "May 14");
		setDocument(database.getCollection("Users"), simpleDocument);

		Document restaurantDocument = new Document().append("Name", "Pyszna pizza u Bieńka")
				.append("Description", "Tu jest pyszna pizza")
				.append("Photo", "null for now")
				.append("Rating", "5.5");
		setDocument(database.getCollection("Restaurants"), restaurantDocument);
	}*/
	//TODO: Reszta funkcji oraz zabezpieczeń
}
