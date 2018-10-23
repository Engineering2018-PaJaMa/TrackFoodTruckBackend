package engineering.sonpm.trackfoodtruck.restWS;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Test;

import static engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.ConnectTODB.database;
import static engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.ConnectTODB.setDocument;
import static engineering.sonpm.trackfoodtruck.restWS.mongoDataBase.ConnectTODB.deleteDocument;

public class DataBaseTests {

    //Testy działają na kolekcji: "Users" i przykładowym dokumencie\

    private Document testDocument = new Document()
            .append("username","newUsername")
            .append("password","test123")
            .append("last_login","May 14");

    private MongoCollection<Document> usersCollection = database.getCollection("Users");

    @Test
    public void newDocumentTest(){

        setDocument(usersCollection,testDocument);
    }

    @Test
    public void deleteDocumentTest() {
        deleteDocument(usersCollection,testDocument);
    }

}
