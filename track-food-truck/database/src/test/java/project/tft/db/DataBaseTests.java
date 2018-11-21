package project.tft.db;

import java.io.IOException;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class DataBaseTests
{
	private static final String host = "localhost";
	//Test port has to be different than default one so in case local instance is running exception won't shut it down (Default is 27017)
	private static final int port = 27000;

	private MongoDBController mongoDBController;
	private MongodExecutable mongodExecutable;

	//Test documents
	private Document testDocument = new Document("login", "login").append("password", "password").append("email", "email");

	private Document location = new Document("country", "Polska").append("city", "Lodz").append("latitude", "11").append("longitude", "22");

	private Document testRestaurant = new Document("name", "foodTruckName").append("cuisine", "foodTruckCuisine")
			.append("description", "foodTruckDescription")
			.append("location", location)
			.append("rating", "5.5");

	private Document testReview = new Document("restaurantName", "U Benka").append("headline", "EKSTRA PIZZA")
			.append("body", "Bylem widziałem dobra pizza")
			.append("rating", 4).append("author", "JanuszWielki");

	@Before
	public void setupDatabase() throws IOException
	{
		mongoDBController = new MongoDBController(host, port, "TrackFoodTruck");
		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net(host, port, Network.localhostIsIPv6())).build();

		MongodStarter starter = MongodStarter.getDefaultInstance();
		mongodExecutable = starter.prepare(mongodConfig);
		mongodExecutable.start();
	}

	@After
	public void clean()
	{
		mongodExecutable.stop();
	}

	@Test
	public void insertTestUser()
	{
		mongoDBController.getDatabase().getCollection("Users").insertOne(testDocument);
	}

	@Test
	public void deleteTestUser()
	{
		mongoDBController.getDatabase().getCollection("Users").findOneAndDelete(testDocument);
	}

	@Test
	public void insertTestRestaurantAndReviews()
	{
		mongoDBController.getDatabase().getCollection("Restaurants").insertOne(testRestaurant);
		mongoDBController.getDatabase().getCollection("Reviews").insertOne(testReview);
	}
}
