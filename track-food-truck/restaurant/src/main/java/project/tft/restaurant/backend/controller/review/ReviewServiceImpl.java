package project.tft.restaurant.backend.controller.review;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;

import project.tft.db.MongoDBController;
import project.tft.restaurant.backend.dto.Review;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
public class ReviewServiceImpl
{
	@Autowired
	private MongoDBController mongoDBController;

	public void createReview(final Review review)
	{
		Document document = new Document().append("headline", review.getHeadline())
				.append("body", review.getBody())
				.append("rating", review.getRating())
				.append("author_name", review.getAuthorName())
				.append("rest_id", review.getRestaurantId());

		mongoDBController.getDatabase().getCollection("Reviews").insertOne(document);
	}

	public List<Document> getAllReviews(final String restaurantName)
	{
		List<Document> list = new ArrayList<>();
		FindIterable<Document> documentList = mongoDBController.getDatabase().getCollection("Reviews").find(eq("Name", restaurantName));

		for (Document d : documentList)
		{
			list.add(d);
		}
		return list;
	}
}
