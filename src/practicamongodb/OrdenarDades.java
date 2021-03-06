/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicamongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
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

            String tempUserCerca = FXMLDocumentController.nomSeleccionat;
            tempUserCerca = tempUserCerca.replaceAll("\\s", "");
            System.out.println("tempUserCerca: " + tempUserCerca);
            
            BasicDBObject cerca = new BasicDBObject();
            cerca.put("nom", tempUserCerca);
            System.out.println(cerca);

            MongoCursor<Document> cursor = col1.find(cerca).iterator();
            //exists("hobbis")).sort(ascending("hobbis")
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
            
            Arrays.sort(tempCursor2);
            Collections.addAll(FXMLDocumentController.hobbies, tempCursor2);

                /* AQUEST TAMBÉ FUNCIONARIA!!
                for(String element : tempCursor2) {
                    FXMLDocumentController.hobbies.add(element);
                }*/
                    
            System.out.println("Llista de hobbies cargada.");
            
            mongoClient.close();
           
            if(!this.isInterrupted()){
                this.interrupt();
            System.out.println("FIL ATURAT.");}
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
}
