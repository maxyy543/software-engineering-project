package it.unisa.diem.ingsoftw.gruppo16.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

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
    private ListView<Contact> listView;
    
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
        listView.setItems(listObservable);
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
        
        FavouriteListController favList = new FavouriteListController();
        listObservable = FXCollections.observableArrayList(favList.getTreeWithFavContacts());
        listView.setItems(listObservable);
        favouriteContactsBtn.setStyle("-fx-background-color: #00a1ff; " +
                                       "-fx-text-fill: white; ");
    }
    @FXML
    private void importFileOnAction(ActionEvent event) {
        new ImportFileController(event);
        listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
        listView.setItems(listObservable);
    }
    @FXML
    private void contactSelected() throws IOException{
        selectedContact.setSelectedContact(listView.getSelectionModel().getSelectedItem());
        view.setDetailOfContactScene();
    }
    private void listViewSelectItemInit(){
        listView.setCellFactory(param -> new ListCell<Contact>() {
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
    private void searchBarOnAction(ActionEvent event) {
        searchBarTf.textProperty().addListener((observer, oldValue, newValue) -> {

            TreeSet<Contact> tempTree = addrBook.getTreeSet();
            listObservable.clear();

            for(Contact c : tempTree){
                if(contactIsFiltered(c, newValue)){
                    listObservable.add(c);
                }
            }
            listView.setItems(listObservable);
        });
    }
    private boolean contactIsFiltered(Contact c, String newValue){
        return c.getName().toLowerCase().contains(newValue.toLowerCase()) ||
                c.getSurname().toLowerCase().contains(newValue.toLowerCase());
    }
}
