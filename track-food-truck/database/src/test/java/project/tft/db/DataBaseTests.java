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
	private Document testDocument = new Document().append("login", "newUsername").append("password", "test123").append("last_login", "May 14");

	private Document testRestaurant = new Document().append("Name", "U Benka")
			.append("Description", "Dobra pizza")
			.append("Photo", "http://balblabl")
			.append("Rating", 5.1)
			.append("Rest_ID", "U Benka")
			.append("Reviews", "Reviews");
	private Document testReview1 = new Document().append("headline", "EKSTRA PIZZA")
			.append("body", "Bylem widziałem dobra pizza")
			.append("rating", 4)
			.append("rest_id", "U Benka");
	private Document testReview2 = new Document().append("headline", "Słaba PIZZA")
			.append("body", "Bylem widziałem zla pizza")
			.append("rating", 1)
			.append("rest_id", "U Benka");

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
		mongoDBController.getDatabase().getCollection("Reviews").insertOne(testReview1);
		mongoDBController.getDatabase().getCollection("Reviews").insertOne(testReview2);
	}
}
