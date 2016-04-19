/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicamongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;

/**
 *
 * @author Roger
 */
public class UsersTempsReal extends Thread{

    //private final static String HOST = "172.20.48.205";
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;
    ObservableList<String> tempArray2 = FXCollections.observableArrayList();

    @Override
    public void run(){ 
        while(true){
            
            try {
                FXMLDocumentController.usuaris.removeAll(FXMLDocumentController.usuaris);
                
                MongoClient mongoClient = new MongoClient(HOST, PORT);
                //MongoDatabase db = mongoClient.getDatabase("bigdata");
                MongoDatabase db = mongoClient.getDatabase("mongoshell");

                System.out.println("Connectat a la base de dades. AGAFAR USUARIS. ");

                MongoCollection<Document> col = db.getCollection("usuaris");
                MongoCursor<Document> cursor = col.find().iterator();
           
                String tempCursor;
                String[] tempCursor2;

                    while (cursor.hasNext()) {
                        System.out.println(tempCursor = cursor.next().toJson());
                        //System.out.println(tempCursor);

                        tempCursor2= tempCursor.split(",");
                        //System.out.println(tempCursor2[1]);

                        tempCursor2= tempCursor2[1].split(":");
                        //System.out.println(tempCursor2[1]);

                        tempCursor = tempCursor2[1];
                        tempCursor = tempCursor.replace("\"", "");

                        //System.out.println(tempCursor);
                        FXMLDocumentController.usuaris.add(tempCursor);
                        System.out.println("Llista usuaris cargada.");

                    }

            mongoClient.close();
           
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UsersTempsReal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //FI DEL WHILE
        }

    }
}
