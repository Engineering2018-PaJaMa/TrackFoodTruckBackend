package engineering.sonpm.trackfoodtruck.restWS.controller.review;

import java.util.List;

import org.bson.Document;

import engineering.sonpm.trackfoodtruck.restWS.dto.Review;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface ReviewService
{
	List<Document> getReviews(final String id);

	Review createReview(final String id);
}
