package project.tft.db;

import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
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
}
