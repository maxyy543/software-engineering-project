package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddandModifyController implements Initializable{

    @FXML
    private Button exportBtn;
    @FXML
    private Button addContactBtn;
    @FXML
    private Button allContactsBtn;
    @FXML
    private Button favouriteContactsBtn;
    @FXML
    private Button importBtn;
    @FXML
    private Label contactsLbl;
    @FXML
    private TextField searchBarTf;
    @FXML
    private ListView<?> listView;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteContactBtn;
    @FXML
    private TextField telephoneTf;
    @FXML
    private TextField telephone2Tf;
    @FXML
    private TextField telephone3Tf;
    @FXML
    private TextField emailTf;
    @FXML
    private TextField email2Tf;
    @FXML
    private TextField email3Tf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField surnameTf;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exportFileOnAction(ActionEvent event) {
    }

    @FXML
    private void addButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void favouriteListOnAction(ActionEvent event) {
    }

    @FXML
    private void importFileOnAction(ActionEvent event) {
    }

    @FXML
    private void searchbarOnAction(ActionEvent event) {
    }
    @FXML 
    private void delContactOnAction(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION); 
        alert.setTitle("Conferma Azione"); 
        alert.setHeaderText("Sei sicuro di voler procedere?"); 
        alert.setContentText("Questa azione non può essere annullata."); 
        Optional<ButtonType> result = alert.showAndWait(); 
            if (result.isPresent() && result.get() == ButtonType.OK) { 
                System.out.println("Utente ha confermato l'azione."); 
            } 
            else{ 
                System.out.println("Utente ha annullato l'azione.");
            }
    }
    @FXML
    private void cancelOnAction(ActionEvent event) throws IOException{
        switchSceneToDashboard(event);
    }
    @FXML
    private void saveBtnOnAction(ActionEvent event) throws IOException{
        switchSceneToDashboard(event);
    }



    private void switchSceneToDashboard(ActionEvent event) throws IOException{
        try {
            Parent scene2Root = FXMLLoader.load(getClass().getResource("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface.fxml")); 
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow(); 
            Scene scene2 = new Scene(scene2Root); 
            stage.setScene(scene2); 
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
