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


public class DetailController implements Initializable{


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
    private Label contactsBtn;
    @FXML
    private TextField searchBarTf;
    @FXML
    private ListView<?> contactListListView;
    @FXML
    private Button modifyBtn;
    @FXML
    private Button deleteContactBtn;
    @FXML
    private Label contactNameLbl;
    @FXML
    private Label contactDetailsLbl;
    @FXML
    private Label telephoneLbl;
    @FXML
    private Label telephone2Lbl;
    @FXML
    private Label telephone3Lbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Label email2Lbl;
    @FXML
    private Label email3Lbl;

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
    private void searchBarOnAction(ActionEvent event) {
    }

    @FXML
    private void modifyBtnOnAction(ActionEvent event) throws IOException{
        switchSceneToModifyContact(event);
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
    void switchSceneToModifyContact(ActionEvent event) throws IOException{
        try{
            Parent scene2Root = FXMLLoader.load(getClass().getResource("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface3.fxml")); 
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow(); 
            Scene scene2 = new Scene(scene2Root); 
            stage.setScene(scene2); 
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
