package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public abstract class MainController {
    @FXML
    protected Button exportBtn;
    @FXML
    protected Button addContactBtn;
    @FXML
    protected Button allContactsBtn;
    @FXML
    protected Button favouriteContactsBtn;
    @FXML
    protected Button importBtn;
    @FXML
    protected TextField searchBarTf;
    @FXML
    protected ListView<Contact> listView;
    
    protected AddressBookModel addrBook = AddressBookModel.getInstance();
    protected SelectedContactController selectedContact = SelectedContactController.getInstance();
    protected ViewUpdateController view = ViewUpdateController.getInstance();
    protected ListViewController list = new ListViewController();
    protected FavouriteListController favList = new FavouriteListController();

    @FXML
    protected void exportFileOnAction(ActionEvent event) {
        new ExportFileController(event);
    }
    @FXML
    protected void addButtonOnAction(ActionEvent event) throws IOException {
        view.setAddContactScene();
    }
    @FXML
    protected void allContactsOnAction(ActionEvent event){
        list.updateList();
        listView.setItems(list.getSharedListView());
    }
    @FXML
    protected void favouriteListOnAction(ActionEvent event) {
        list.getSharedListView().setAll(favList.getTreeWithFavContacts());
        listView.setItems(list.getSharedListView());
    }
    @FXML
    protected void importFileOnAction(ActionEvent event) {
        new ImportFileController(event);
        list.updateList();
        listView.setItems(list.getSharedListView());
    }
    @FXML
    protected void contactSelected() throws IOException{
        selectedContact.setSelectedContact(listView.getSelectionModel().getSelectedItem());
        view.setDetailOfContactScene();
    }
    protected void listViewSelectItemInit(){
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
    protected void initSearchbar(){
        searchBarTf.textProperty().addListener((observer, oldValue, newValue) -> {
            list.filterList(newValue);
            listView.setItems(list.getSharedListView());
        });
    }
}
