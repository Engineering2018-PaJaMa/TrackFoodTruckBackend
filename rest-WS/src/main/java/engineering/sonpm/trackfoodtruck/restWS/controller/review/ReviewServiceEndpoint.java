package engineering.sonpm.trackfoodtruck.restWS.controller.review;

import static engineering.sonpm.trackfoodtruck.restWS.Constants.USER_PATH;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import engineering.sonpm.trackfoodtruck.restWS.dto.Review;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paweł Szopa on 23/10/2018
 */
@Slf4j
@RestController
@RequestMapping(value = USER_PATH)
public class ReviewServiceEndpoint implements ReviewService
{
	@Autowired
	ReviewServiceImpl reviewService;

	@GetMapping(value = "/{id}")
	public List<Document> getReviews(@PathVariable("id") final String id)
	{
		log.info("Returning reviews for restaurant {}", id);
		List<Document> listOfReviews = new ArrayList<Document>();
		listOfReviews = reviewService.getAllReviews(id);
		return listOfReviews;
	}

	@PutMapping(value = "/{id}")
	public Review createReview(@PathVariable("id") final String id)
	{
		log.info("Creating review with name: {}", id);
		Review review = new Review("EKSTRA PIZZA", "Bylem widziałem dobra pizza", 4, "Maciek", id);

		reviewService.createReview(review);
		return review;
	}
}