package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DashboardController implements Initializable{

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
    private ListView<?> contactListListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exportFileOnAction(ActionEvent event) {
    }
    @FXML
    private void addButtonOnAction(ActionEvent event) throws IOException {
        switchSceneToModifyContact(event);
    }
    @FXML
    private void favouriteListOnAction(ActionEvent event) {
    }

    @FXML
    private void importFileOnAction(ActionEvent event) {
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
