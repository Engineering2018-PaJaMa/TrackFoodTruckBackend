package project.tft.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import lombok.Data;
import lombok.Getter;

@Data
@Component
public class MongoDBController
{
	private MongoClient mongo;

	@Getter
	private MongoDatabase database;

	public MongoDBController(
			@Value("${mongodb.host}") final String host, @Value("${mongodb.port}") final int port, @Value("${mongodb.database.name}") final String name)
	{
		mongo = new MongoClient(new ServerAddress(host, port));
		database = mongo.getDatabase(name);
	}
}
