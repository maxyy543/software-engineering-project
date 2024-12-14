package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeSet;
import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



public class ModifyContactController implements Initializable{

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

    private AddressBookModel addrBook;
    private ObservableList<Contact> listObservable;
    private SelectedContactController selectedContact;
    private ViewUpdateController view;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addrBook = AddressBookModel.getInstance();
        view = ViewUpdateController.getInstance();
        listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
        selectedContact = SelectedContactController.getInstance();
        initSelectedContactInfo(selectedContact);
        listView.setItems(listObservable);
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
        listObservable = FXCollections.observableArrayList(favList.getTreeWithFavContacts());
        listView.setItems(listObservable);
        favouriteContactsBtn.setStyle("-fx-background-color: #00a1ff; " +
                                       "-fx-text-fill: white; ");
    }

    @FXML
    private void importFileOnAction(ActionEvent event) {
        new ImportFileController(event);
    }

    @FXML
    private void searchbarOnAction(ActionEvent event) {
        searchBarTf.textProperty().addListener((observer, oldValue, newValue) -> {

            TreeSet<Contact> tempTree = addrBook.getTreeSet();
            listObservable.clear();

            for(Contact c : tempTree){
                if(c.getName().toLowerCase().contains(newValue.toLowerCase()) ||
                   c.getSurname().toLowerCase().contains(newValue.toLowerCase())){
                    listObservable.add(c);
                }
            }
            listView.setItems(listObservable);
        });
    }
    @FXML 
    private void delContactOnAction(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION); 
        //initAlert(alert);
        Optional<ButtonType> result = alert.showAndWait(); 
        if (result.isPresent() && result.get() == ButtonType.OK) { 
            if(selectedContact.getSelectedContact() != null){
                //deleteContactFromAddressBook(selectedContact);
                //switchSceneToDashboard(event);
            }
        } 
        else{ 
            System.out.println("Utente ha annullato l'azione.");
        }
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
            addrBook.modifyContact(selectedContact.getSelectedContact(), contact);
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
    private void initSelectedContactInfo(SelectedContactController selectedContact){
        setContactDetail(selectedContact.getSelectedContact());
    }
    public void setContactDetail(Contact contact){
        surnameTf.setText(contact.getSurname());
        nameTf.setText(contact.getName());
        String[] tel = contact.getTelephoneNumber();
        String[] email = contact.getEmail();
        telephoneTf.setText(tel[0]);
        telephone2Tf.setText(tel[1]);
        telephone3Tf.setText(tel[2]);
        emailTf.setText(email[0]);
        email2Tf.setText(email[1]);
        email3Tf.setText(email[2]);
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
}
