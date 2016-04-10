/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicamongodb;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author Roger
 */
public class FXMLDocumentController implements Initializable {
    public FXMLDocumentController(){}
    
    static ObservableList<String> usuaris = FXCollections.observableArrayList();
    static ObservableList<String> hobbies = FXCollections.observableArrayList();
   
    public static String nomSeleccionat;
    
    
    @FXML public ListView listview_nom;
    @FXML public ListView listview_hobbies;
    @FXML public Label label_selecciona;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Task task = new Task<Void>() {
            
        @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    final int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            listview_nom.setItems(usuaris);
                            listview_hobbies.setItems(hobbies);

                        }
                    });
                i++;
                Thread.sleep(1000);
                }
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }  
    
      
     //LIST VIEW EVENT
    @FXML public void handleMouseClick(MouseEvent arg0) {
        System.out.println("clicked on " + listview_nom.getSelectionModel().getSelectedItem());
        
        //onMousePressed="#handleMouseClick"
    }
    
    @FXML //botosimulacre emulacio list view de dalt
    public void handleButtonAction2(ActionEvent event) {
        CargarllistaHobbies fil2 = new CargarllistaHobbies();
        if((String)(listview_nom.getSelectionModel().getSelectedItem()) == null){
            System.out.println("Selecciona un usuari");
            
        } else{
            label_selecciona.setVisible(false);
            System.out.println("BOTO SIMULACIO DEL LISTVIEW EVENT");
            nomSeleccionat = (String)(listview_nom.getSelectionModel().getSelectedItem());

            fil2.start();
        }
    }
    
    //AFEGIR USUARI I HOBBIS 
    @FXML
    public void handleButtonActionAddHobbies(ActionEvent event) {
        System.out.println("Afegir hobbis");
    }
    
     @FXML 
    public void handleButtonActionUsers(ActionEvent event) {
        System.out.println("Afegin usuaris a la BD");
        
    }
 
}
