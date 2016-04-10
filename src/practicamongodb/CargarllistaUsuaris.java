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
import org.bson.Document;



public class CargarllistaUsuaris extends Thread {
    public CargarllistaUsuaris(){}
    
    FXMLDocumentController controller = new FXMLDocumentController();

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;
    
    @Override
    public void run(){    
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("mongoshell");
            
            System.out.println("Connectat a la base de dades. AGAFAR USUARIS. ");
            
            MongoCollection<Document> col = db.getCollection("usuaris");
            MongoCursor<Document> cursor = col.find().iterator();
            try {            
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


/*Document doc;
            doc = new Document("nom", "sergi").append("anys", 45)
                    .append("telf", "321-654-987");
            col.insertOne(doc);*/