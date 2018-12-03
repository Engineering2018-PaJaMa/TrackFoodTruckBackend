package project.tft.restaurant.backend.controller.review;

import java.util.List;

import org.bson.Document;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface ReviewService
{
	Document createReview(Document review);

	List<Document> findReviews(Document review);
}
