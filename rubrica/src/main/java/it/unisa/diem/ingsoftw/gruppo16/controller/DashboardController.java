package it.unisa.diem.ingsoftw.gruppo16.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private AddressBookModel addrBook;
    private SelectedContactController selectedContact;
    private ViewUpdateController view;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addrBook = AddressBookModel.getInstance();
        selectedContact = SelectedContactController.getInstance();
        view = ViewUpdateController.getInstance();
        listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
        contactListListView.setItems(listObservable);
        listViewSelectItemInit();
    }    
    @FXML
    private void exportFileOnAction(ActionEvent event) {
        new ExportFileController(event);
    }
    @FXML
    private void addButtonOnAction(ActionEvent event) throws IOException {
        view.setAddContactScene();
    }
    @FXML
    private void favouriteListOnAction(ActionEvent event) {
    }
    @FXML
    private void importFileOnAction(ActionEvent event) {
        new ImportFileController(event);
        listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
        contactListListView.setItems(listObservable);
    }
    @FXML
    private void contactSelected() throws IOException{
        selectedContact.setSelectedContact(contactListListView.getSelectionModel().getSelectedItem());
        openDetailOf(selectedContact.getSelectedContact());
        view.setDetailOfContactScene();
    }

    void switchSceneToModifyContact(ActionEvent event) throws IOException{
        view.setModifyScene();
    }
    private void openDetailOf(Contact contact) throws IOException{
        /*try{
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
        }*/
        selectedContact.resetSelectedContact();
        selectedContact.setSelectedContact(contact);
        view.setDetailOfContactScene();
    }
    private void listViewSelectItemInit(){
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
}
