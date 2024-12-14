package it.unisa.diem.ingsoftw.gruppo16.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
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

    private SelectedContactController selectedContact;
    private ViewUpdateController view;
    private ListViewController list;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedContact = SelectedContactController.getInstance();
        view = ViewUpdateController.getInstance();
        list = new ListViewController();
        listView.setItems(list.getSharedListView());
        listViewSelectItemInit();
        initSearchbar();
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
        list.getSharedListView().setAll(favList.getTreeWithFavContacts());
        listView.setItems(list.getSharedListView());
        favouriteContactsBtn.setStyle("-fx-background-color: #00a1ff; " +
                                       "-fx-text-fill: white; ");
    }
    @FXML
    private void importFileOnAction(ActionEvent event) {
        new ImportFileController(event);
        list.updateList();
        listView.setItems(list.getSharedListView());
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
    private void initSearchbar(){
        searchBarTf.textProperty().addListener((observer, oldValue, newValue) -> {
            list.filterList(newValue);
            listView.setItems(list.getSharedListView());
        });
    }
}
