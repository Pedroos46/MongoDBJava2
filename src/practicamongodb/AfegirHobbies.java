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
 * @author Roger
 * Aquesta clase es la encarregada de afergir hobbis, en aquest cas nomes afegim hobbis o creem usuaris RES MES. 
 */
public class AfegirHobbies extends Thread {
    //public AfegirDades(){} per despres exacutar el fil, declaro classe fil = new classe i despres fil.start
    
    FXMLDocumentController controller = new FXMLDocumentController();

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
        
        //POSEM A LA BD LO QUE VOLEM    
            Document doc = new Document("nom", nomSel).append("hobbis", FXMLDocumentController.tempArray);
            col1.insertOne(doc);
         
        //CRIDEM EL FIL DE CARREGAR USUARIS
            CargarllistaHobbies fil2 = new CargarllistaHobbies();
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


/*

//RECUPEREM I NETEJEM INFORMACIO DE LA BD
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
                }*//*
                    
            System.out.println("Llista de hobbies cargada.");
            
            mongoClient.close();
*/
