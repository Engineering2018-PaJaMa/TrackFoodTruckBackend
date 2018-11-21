package project.tft.restaurant.backend;

import org.bson.Document;
import org.springframework.stereotype.Component;

import project.tft.restaurant.backend.dto.FoodTruck;
import project.tft.restaurant.backend.dto.Location;
import project.tft.restaurant.backend.dto.Review;

/**
 * Created by Paweł Szopa on 21/11/2018
 */
@Component
public class Converter
{
	public Document convert(final FoodTruck foodTruck)
	{
		Document location = convert(foodTruck.getLocation());

		return new Document("name", foodTruck.getName()).append("cuisine", foodTruck.getCuisine())
				.append("description", foodTruck.getDescription())
				.append("location", location)
				.append("rating", foodTruck.getRating())
				.append("photo", foodTruck.getPhoto());
	}

	private Document convert(final Location location)
	{
		return new Document("country", location.getCountry()).append("city", location.getCity())
				.append("latitude", location.getLatitude())
				.append("longitude", location.getLongitude());
	}

	public Document covert(final Review review)
	{
		return new Document("restaurantName", review.getRestaurantName()).append("headline", review.getHeadline())
				.append("body", review.getBody())
				.append("rating", review.getRating())
				.append("author", review.getAuthor());
	}
}
