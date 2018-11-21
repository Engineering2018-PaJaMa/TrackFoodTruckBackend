package project.tft.restaurant.backend.controller.review;

import java.util.List;

import org.bson.Document;

import project.tft.restaurant.backend.dto.Review;

/**
 * Created by Paweł Szopa on 31/10/2018
 */
public interface ReviewService
{
	Review createReview(Review review);

	List<Document> findReviews(Review review);
}
