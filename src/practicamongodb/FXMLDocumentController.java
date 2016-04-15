/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicamongodb;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    final ListView lv = new ListView();
    
    public static String nomSeleccionat;
    
    
    @FXML public ListView listview_nom;
    @FXML public ListView listview_hobbies;
    @FXML public Label label_selecciona;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
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
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }  
    
          
    @FXML //carregar hobbis
    public void handleButtonAction2(ActionEvent event) {
        try{
            CargarllistaHobbies fil2 = new CargarllistaHobbies();
            if((String)(listview_nom.getSelectionModel().getSelectedItem()) == null){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Selecciona un usuari de la llista per a poder veure els seus hobbies");
                alert.showAndWait();
                System.out.println("Selecciona un usuari");

            } else{
                nomSeleccionat = (String)(listview_nom.getSelectionModel().getSelectedItem());
                hobbies.removeAll(hobbies);

                fil2.start();

                label_selecciona.setVisible(false);
                System.out.println("Carregan hobbies.");
                }

        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
    
    @FXML //ordenar hobbis
    public void handleButtonOrdenar(ActionEvent event) {
        try{
            OrdenarDades fil3 = new OrdenarDades();
            if((String)(listview_nom.getSelectionModel().getSelectedItem()) == null){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Selecciona un usuari de la llista per a poder veure els seus hobbies ordenats");
                alert.showAndWait();
                System.out.println("Selecciona un usuari");

            } else{
                nomSeleccionat = (String)(listview_nom.getSelectionModel().getSelectedItem());
                hobbies.removeAll(hobbies);

                fil3.start();
                label_selecciona.setVisible(false);
                System.out.println("Carregan hobbies.");
                }

        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
 
}
