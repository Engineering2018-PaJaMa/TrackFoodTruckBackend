package engineering.sonpm.trackfoodtruck.restWS.controller.review;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import engineering.sonpm.trackfoodtruck.restWS.dto.Review;
import engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.MongoDBController;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
public class ReviewServiceImpl
{
	@Autowired
	MongoDBController mongoDBController;

	public void createReview(final Review review)
	{
		Document document = new Document().append("headline", review.getHeadline())
				.append("body", review.getBody())
				.append("rating", review.getRating())
				.append("author_name", review.getAuthor_name())
				.append("rest_id", review.getRest_id());

		mongoDBController.setUpConnection();
		mongoDBController.setDocument(mongoDBController.getCollection("Reviews"), document);
	}

	public List<Document> getAllReviews(final String restaurantName)
	{
		return mongoDBController.showAllReviews(mongoDBController.getCollection("Reviews"), restaurantName);
	}
}
