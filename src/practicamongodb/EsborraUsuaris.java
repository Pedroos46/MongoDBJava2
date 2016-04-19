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
import org.bson.Document;

/**
 *
 * @author Roger
 */
public class EsborraUsuaris extends Thread {
   
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;
    
    @Override
    public void run(){    
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("mongoshell");
            
            System.out.println("Connectat a la base de dades. CARREGAR USUARIS. ");
            
            MongoCollection<Document> col1 = db.getCollection("usuaris");
            MongoCursor<Document> cursor = col1.find().iterator();           

        //AGAFEM EL NOM
            String nomSel  = FXMLDocumentController.nomSeleccionat;
            nomSel = nomSel.replaceAll("\\s", "");
            System.out.println(nomSel);
            
        // ELIMINEM EL USUARI SELECCIONAT
           DeleteResult resultats2 = col1.deleteMany(eq("nom", nomSel));
           System.out.println(resultats2.getDeletedCount());
         
        //CRIDEM EL FIL DE CARREGAR USUARIS
            CargarllistaUsuaris fil2 = new CargarllistaUsuaris();
            fil2.start();

                    
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        

        if(!this.isInterrupted()){
            this.interrupt();
            System.out.println("FIL ATURAT.");
        }
    }


}