package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBook;
import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
    private ListView<Contact> contactListListView;
    
    private ObservableList<Contact> listObservable;
    private AddressBook addrBook;
    private SelectedContactController selectedContact;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addrBook = AddressBookModel.getInstance();
        selectedContact = SelectedContactController.getInstance();
        listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
        contactListListView.setItems(listObservable);

        contactListListView.setCellFactory(param -> new ListCell<Contact>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);

                if (empty || contact == null) {
                    setText(null);
                } else {
                    setText(contact.getSurname() + " " + contact.getName());
                }
            }});
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
    @FXML
    private void contactSelected() throws IOException{
        selectedContact.setSelectedContact(contactListListView.getSelectionModel().getSelectedItem());
        openDetailOf(selectedContact.getSelectedContact());
    }
    private void openDetailOf(Contact contact) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface2.fxml"));;
            Parent root = loader.load();
            DetailController detailController = loader.getController();
            detailController.setContactDetail(contact);
            Scene scene = new Scene(root);
            Stage stage = (Stage) contactListListView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
