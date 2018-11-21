package project.tft.restaurant.backend.controller.review;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;

import project.tft.db.MongoDBController;
import project.tft.restaurant.backend.Converter;
import project.tft.restaurant.backend.dto.Review;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
public class ReviewServiceImpl
{
	@Autowired
	private MongoDBController mongoDBController;

	@Autowired
	private Converter converter;

	public void createReview(final Review review)
	{
		mongoDBController.getDatabase().getCollection("Reviews").insertOne(converter.covert(review));
	}

	public List<Document> getAllReviews(final String restaurantName)
	{
		FindIterable<Document> documentList = mongoDBController.getDatabase().getCollection("Reviews").find(eq("restaurantName", restaurantName));
		List<Document> list = new ArrayList<>();

		for (Document d : documentList)
		{
			list.add(d);
		}
		return list;
	}
}
