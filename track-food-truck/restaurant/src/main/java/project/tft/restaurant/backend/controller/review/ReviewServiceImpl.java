package project.tft.restaurant.backend.controller.review;

import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.tft.db.MongoDBController;

/**
 * Created by Paweł Szopa on 06/11/2018
 */
@Component
public class ReviewServiceImpl {

    @Autowired
    private MongoDBController mongoDBController;

    public Document createReview(final Document review) {
        mongoDBController.getDatabase().getCollection("Reviews").insertOne(review);
        return mongoDBController.getDatabase().getCollection("Reviews").find(review).first();
    }

    public List<Document> getAllReviews(final Document restaurantName) {
        FindIterable<Document> documentList = mongoDBController.getDatabase().getCollection("Reviews").find(restaurantName);
        List<Document> list = new ArrayList<>();

        for (Document d : documentList) {
            list.add(d);
        }
        return list;
    }
}
