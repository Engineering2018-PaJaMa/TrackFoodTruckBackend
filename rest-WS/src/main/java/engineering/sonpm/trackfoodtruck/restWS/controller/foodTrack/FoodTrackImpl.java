package engineering.sonpm.trackfoodtruck.restWS.controller.foodTrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import engineering.sonpm.trackfoodtruck.restWS.dto.FoodTruck;
import engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.MongoDBController;

@Component
public class FoodTrackImpl
{
	@Autowired
	MongoDBController mongoDBController;

	public void createFoodtruck(final FoodTruck foodTruck)
	{
		//		Document document = new Document().append("Name", foodTruck.getName())
		//				.append("Description", foodTruck.getDescription())
		//				.append("Photo", foodTruck.getPhoto())
		//				.append("Rating", foodTruck.getRating())
		//				.append("Rest_ID", foodTruck.getId)
		//				.append("Reviews", foodTruck.getReviews);

		mongoDBController.setUpConnection();
		//mongoDBController.setDocument(mongoDBController.getCollection("Restaurants"), document);
	}
}
