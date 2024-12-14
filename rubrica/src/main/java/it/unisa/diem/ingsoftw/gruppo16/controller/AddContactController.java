package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddContactController implements Initializable{

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
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveBtn;
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

    private AddressBookModel addrBook;
    private FilteredList<Contact> filteredContactsList;
    private SelectedContactController selectedContact;
    private ViewUpdateController view;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addrBook = AddressBookModel.getInstance();
        view = ViewUpdateController.getInstance();
        ObservableList<Contact> contactList = FXCollections.observableArrayList(addrBook.getTreeSet());
        selectedContact = SelectedContactController.getInstance();
        filteredContactsList = new FilteredList<>(contactList, p -> true);
        listView.setItems(filteredContactsList);
        listViewSelectItemInit();
    }
    
    @FXML
    private void exportFileOnAction(ActionEvent event) {
        new ExportFileController(event);
    }

    @FXML
    private void addButtonOnAction(ActionEvent event){
        view.setAddContactScene();
    }

    @FXML
    private void favouriteListOnAction(ActionEvent event) {
        FavouriteListController favList = new FavouriteListController();
        ObservableList<Contact> obsList = FXCollections.observableArrayList(favList.getTreeWithFavContacts());
        listView.setItems(new FilteredList<>(obsList, p -> true));
        favouriteContactsBtn.setStyle("-fx-background-color: #00a1ff; " +
                                       "-fx-text-fill: white; ");
    }

    @FXML
    private void importFileOnAction(ActionEvent event) {
        new ImportFileController(event);
    }

    @FXML
    private void searchbarOnAction(ActionEvent event) {
        searchBarTf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredContactsList.setPredicate(c -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                return c.getSurname().toLowerCase().contains(newValue.toLowerCase()) ||
                       c.getName().toLowerCase().contains(newValue.toLowerCase());
            });
        });
    }
    @FXML
    private void cancelOnAction(ActionEvent event) throws IOException{
        view.setDashboardScene();
    }

    @FXML
    private void saveBtnOnAction(ActionEvent event) throws IOException{
        Contact contact = contactWithInfoFromTextFields();     
        Validator contactVerifier = Validator.link(new TelephoneNumberController(), new EmailController(), new NameAndSurnameChecker());
        if(contactVerifier.check(contact) && (addrBook != null)){
            addrBook.addNewContact(contact);
            ObservableList<Contact> listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
            listView.setItems(listObservable);
            clearTextFields();
        }
    }
    @FXML
    private void contactSelected() throws IOException{
        selectedContact.setSelectedContact(listView.getSelectionModel().getSelectedItem());
        view.setDetailOfContactScene();
    }
    private String getSurnameTextField(){
        if(surnameTf.getText().isEmpty())
            return "";
        else 
            return surnameTf.getText().trim().substring(0,1).toUpperCase() + 
        surnameTf.getText().trim().substring(1).toLowerCase();
    }
    private String getNameTextField(){
        if(nameTf.getText().isEmpty())
            return "";
        else
            return nameTf.getText().trim().substring(0,1).toUpperCase() + 
        nameTf.getText().trim().substring(1).toLowerCase();
    }
    private void clearTextFields(){
        surnameTf.clear();
        nameTf.clear();
        emailTf.clear();
        email2Tf.clear();
        email3Tf.clear();
        telephoneTf.clear();
        telephone2Tf.clear();
        telephone3Tf.clear();
    }
    private Contact contactWithInfoFromTextFields(){
        String surname = getSurnameTextField();
        String name = getNameTextField();
        Contact contact = new Contact(surname, name);
        String[] tel =  {telephoneTf.getText().trim().toUpperCase(), telephone2Tf.getText().trim(), telephone3Tf.getText().trim()};
        String[] email =  {emailTf.getText().trim(), email2Tf.getText().trim(), email3Tf.getText().trim()};
        contact.setTelephoneNumber(tel);
        contact.setEmail(email);
        return contact;
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
}
