package engineering.sonpm.trackfoodtruck.restWS.mongoDataBase;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnectTODB {

    private static MongoClient mongo;
    private static MongoCredential credential;
    private static MongoDatabase database;

    public static void main( String args[] ) {
        connectDataBase();

    }

    private static void connectDataBase(){

        // Creating a Mongo client
        mongo = new MongoClient( "localhost" , 27017 );

        // Creating Credentials
        credential = MongoCredential.createCredential(
                "FoodTrackMongo",
                "TrackFoodTruck",
                "AdminFoodTrack".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        database = mongo.getDatabase("TrackFoodTruck");
    }

    public MongoCollection<Document> getCollection(String collectionName){
        return database.getCollection(collectionName);
    }

    public void setDocument (MongoCollection<Document> collectionForDoc, Document docForSet){
        collectionForDoc.insertOne(docForSet);
    }

    //TODO: Reszta funkcji oraz zabezpieczeń
}
