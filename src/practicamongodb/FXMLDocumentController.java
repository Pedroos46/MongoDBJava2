/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicamongodb;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



/**
 *
 * @author Roger
 */
public class FXMLDocumentController implements Initializable {
    public FXMLDocumentController(){}
    //private final static String HOST = "127.0.0.1";
    public static ObservableList<String> usuaris = FXCollections.observableArrayList();
    public static ObservableList<String> hobbies = FXCollections.observableArrayList();
    public static ObservableList<String> tempArray = FXCollections.observableArrayList();
    public static ObservableList<String> tempArray2 = FXCollections.observableArrayList();


    public static String nomSeleccionat;
    public static String hobbiesSeleccionat2[];
    public static String nouNom;

    
    @FXML public ListView listview_nom;
    @FXML public ListView listview_hobbies;
    @FXML public Label label_selecciona;
    @FXML public TextField TextField1;
    @FXML public TextField TextField2;
   
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
                
                CargarllistaHobbies fil2 = new CargarllistaHobbies();
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
        String hobbiesSeleccionat;
        
        try{
            if((String)(listview_nom.getSelectionModel().getSelectedItem()) == null){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Selecciona un usuari de la llista per a poder veure els seus hobbies");
                alert.showAndWait();
                System.out.println("Selecciona un usuari");

            } else{
                nomSeleccionat = (String)(listview_nom.getSelectionModel().getSelectedItem());
                System.out.println("Afegir hobbis");
                
                hobbies = tempArray;
                hobbies.removeAll(hobbies);
                hobbiesSeleccionat = TextField1.getText();
                hobbiesSeleccionat2 = hobbiesSeleccionat.split("\\s");

                Collections.addAll(tempArray, hobbiesSeleccionat2);

                AfegirHobbies fil4 = new AfegirHobbies();
                fil4.start();
                
                }

        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }    
    }
    
     @FXML 
    public void handleButtonActionUsers(ActionEvent event) {
        System.out.println("Afegin usuaris a la BD");
        String hobbiesEntrat;
        try{
            if(TextField2.getText() == null || TextField2.getText().trim().isEmpty()){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Introdueix un nom");
                alert.showAndWait();
                System.out.println("Selecciona un usuari");
            } else{ if (usuaris.contains(TextField2.getText().trim())){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("L'usuari ja existeix");
                alert.showAndWait();
                System.out.println("Selecciona un usuari");
            } else { if (TextField1.getText() == null || TextField1.getText().isEmpty()){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Introdueix els hobbies");
                alert.showAndWait();
                System.out.println("Selecciona un usuari");

            } else{
                nouNom = (String)(listview_nom.getSelectionModel().getSelectedItem());
                System.out.println("Crean usuari ");
                
                hobbies.removeAll(hobbies);
                
                hobbiesEntrat = TextField1.getText();
                hobbiesSeleccionat2 = hobbiesEntrat.split("\\s");
               
                Collections.addAll(tempArray2, hobbiesSeleccionat2);
 
                nouNom = TextField2.getText();

                AfegirUsuaris fil7 = new AfegirUsuaris();
                fil7.start();
                
                }
            }
            }

        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } 
    }
    
    @FXML
    public void handleButtonActionBorrarUsers(ActionEvent event) {
        try{
            if((String)(listview_nom.getSelectionModel().getSelectedItem()) == null){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Selecciona un usuari de la llista per a poder esborrarlo");
                alert.showAndWait();
                System.out.println("Selecciona un usuari");

            } else{
                nomSeleccionat = (String)(listview_nom.getSelectionModel().getSelectedItem());
                hobbies.removeAll(hobbies);
                usuaris.removeAll(usuaris);
                
                EsborraUsuaris fil6 = new EsborraUsuaris();
                fil6.start();
                
                label_selecciona.setVisible(false);
                System.out.println("Carregan hobbies.");
                }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    
    }
    
    @FXML //ordenar hobbis
    public void handleButtonOrdenar(ActionEvent event) {
        try{
            
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
                
                OrdenarDades fil3 = new OrdenarDades();
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
