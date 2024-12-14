package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
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
    private SelectedContactController selectedContact;
    private ViewUpdateController view;
    private ListViewController list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addrBook = AddressBookModel.getInstance();
        view = ViewUpdateController.getInstance();
        selectedContact = SelectedContactController.getInstance();
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
    private void addButtonOnAction(ActionEvent event){
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

    private void initSearchbar(){
        searchBarTf.textProperty().addListener((observer, oldValue, newValue) -> {
            list.filterList(newValue);
            listView.setItems(list.getSharedListView());
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
            list.updateList();
            listView.setItems(list.getSharedListView());
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
