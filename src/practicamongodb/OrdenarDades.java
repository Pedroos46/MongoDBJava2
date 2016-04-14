/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicamongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Sorts.descending;
import java.util.Collections;
import org.bson.Document;

/**
 *
 * @author Roger
 */
public class OrdenarDades extends Thread {
    
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    @Override
    public void run(){    
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("mongoshell");
            MongoCollection<Document> col1 = db.getCollection("usuaris");

            Document doc = col1.find(exists("nom")).sort(descending("nom")).first();
            System.out.println(doc.toJson());
            
            
            BasicDBObject cerca = new BasicDBObject();
            cerca.put("nom", tempUserCerca);
            
            System.out.println(cerca);

            MongoCollection<Document> col = db.getCollection("usuaris");
            MongoCursor<Document> cursor = col.find(cerca).iterator();
            
            try {            
                String tempCursor;
                String[] tempCursor2;

                System.out.println(tempCursor = cursor.next().toJson());
                //System.out.println("tempCursor: " + tempCursor);
                    
                tempCursor2= tempCursor.split("\\[");
                //System.out.println(tempCursor2[1]);

                tempCursor = tempCursor2[1];
                tempCursor = tempCursor.replaceAll("]", "");
                tempCursor = tempCursor.replaceAll("}", "");
                tempCursor = tempCursor.replaceAll("\"", "");
                tempCursor = tempCursor.replaceAll("\\s", "");
                //System.out.println(tempCursor);
                    
                tempCursor2= tempCursor.split(",");
                //System.out.println(tempCursor2[0]);
                //System.out.println(tempCursor2[1]);
                //System.out.println(tempCursor2[2]);
                    
                Collections.addAll(FXMLDocumentController.hobbies, tempCursor2);

                /* AQUEST TAMBÉ FUNCIONARIA!!
                for(String element : tempCursor2) {
                    FXMLDocumentController.hobbies.add(element);
                }*/
                    
                System.out.println("Llista de hobbies cargada.");
            
            mongoClient.close();

            /*Block<Document> printBlock = new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    System.out.println(document.toJson());
                }
            };

            col1.find(and(gt("anys", 20), lte("anys", 100))).forEach(printBlock);*/
           
            if(!this.isInterrupted()){
            this.interrupt();
            System.out.println("FIL ATURAT.");
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
}
