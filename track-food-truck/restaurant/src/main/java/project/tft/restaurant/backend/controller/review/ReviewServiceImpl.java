package project.tft.restaurant.backend.controller.review;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

		mongoDBController.setUpConnection();
		mongoDBController.setDocument(mongoDBController.getCollection("Reviews"), document);
	}

	public List<Document> getAllReviews(final String restaurantName)
	{
		return mongoDBController.showAllReviews(mongoDBController.getCollection("Reviews"), restaurantName);
	}
}
