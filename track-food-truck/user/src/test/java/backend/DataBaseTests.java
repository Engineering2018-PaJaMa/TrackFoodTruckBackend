package backend;

import java.io.IOException;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import backend.mongoDataBase.MongoDBController;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class DataBaseTests
{
	private static final String ip = "localhost";
	private static final int port = 27017;

	private MongoDBController mongoDBController;
	private MongoDatabase database;
	private MongoCollection<Document> usersCollection;
	private MongodExecutable mongodExecutable;
	private Document testDocument = new Document().append("username", "newUsername").append("password", "test123").append("last_login", "May 14");

	@Before
	public void setupDatabase() throws IOException
	{
		mongoDBController = new MongoDBController();
		mongoDBController.setUpConnection();
		database = mongoDBController.getDatabase();
		usersCollection = database.getCollection("Users");

		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net(ip, port, Network.localhostIsIPv6())).build();

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
