package project.tft.restaurant.backend.controller.review;

import static project.tft.restaurant.backend.Constants.FOOD_TRUCK_REVIEW_PATH;

import java.util.List;

import javax.validation.Valid;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.tft.restaurant.backend.dto.Review;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Slf4j
@RestController
@RequestMapping(value = FOOD_TRUCK_REVIEW_PATH)
public class ReviewServiceEndpoint implements ReviewService
{
	@Autowired
	ReviewServiceImpl reviewService;

	@PutMapping
	@Override
	public Review createReview(@RequestBody @Valid Review review)
	{
		log.info("Creating foodTruck review {} in database.", review);
		reviewService.createReview(review);
		return review;
	}

	@PostMapping
	@Override
	public List<Document> findReviews(@RequestBody final Review review)
	{
		log.info("Returning foodTruck {} reviews from database.", review.getRestaurantName());
		return reviewService.getAllReviews(review.getRestaurantName());
	}
}