/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicamongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * @author Roger
 * Aquesta clase es la encarregada de afergir hobbis, en aquest cas nomes afegim hobbis o creem usuaris RES MES. 
 */
public class AfegirDades extends Thread {
    //public AfegirDades(){} per despres exacutar el fil, declaro classe fil = new classe i despres fil.start
    
    FXMLDocumentController controller = new FXMLDocumentController();

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;
    
    @Override
    public void run(){    
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("mongoshell");
            
            System.out.println("Connectat a la base de dades. AGAFAR USUARIS. ");
            
            MongoCollection<Document> col1 = db.getCollection("usuaris");
            MongoCursor<Document> cursor = col1.find().iterator();
            List<String> milestones = new ArrayList<>();   
            String textfie= FXMLDocumentController.text;
            milestones.add(textfie);
            System.out.print("adeu");
            Document doc;
            System.out.print(textfie);
            String nomSel  =FXMLDocumentController.nomSeleccionat;
            nomSel = nomSel.replaceAll("\\s", "");
            System.out.println(nomSel);
            
          // col1.deleteOne(eq("nom", nomSel));
            DeleteResult resultats2;
            resultats2 = col1.deleteMany(eq("nom", nomSel));
            System.out.println(resultats2.getDeletedCount());
             doc = new Document("nom", "Demo").append("hobbis", milestones);
           //doc = new Document("nom", "Demo").append("hobbis", "tenis"+"boley");
            col1.insertOne(doc);
             System.out.print("adeu2");
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        if(!this.isInterrupted()){
            this.interrupt();
            System.out.println("FIL ATURAT.");
        }
    }


}
    
