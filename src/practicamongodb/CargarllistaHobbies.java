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
import org.bson.Document;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Roger
 */
public class CargarllistaHobbies extends Thread {
    public CargarllistaHobbies(){}
    
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;
    
    @Override
    public void run(){    
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("mongoshell");
            
            System.out.println("Connectat a la base de dades. AGAFAR HOBBIES");
            
            String tempUserCerca = FXMLDocumentController.nomSeleccionat;
            tempUserCerca = tempUserCerca.replaceAll("\\s", "");
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

            } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            } finally {
                cursor.close();
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }



}
