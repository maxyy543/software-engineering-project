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
    private ListView<Contact> contactListListView;
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

    private AddressBookModel addrBook;
    private ObservableList<Contact> listObservable;
    private SelectedContactController selectedContact;
    private ViewUpdateController view;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addrBook = AddressBookModel.getInstance();
        selectedContact = SelectedContactController.getInstance();
        view = ViewUpdateController.getInstance();
        initSelectedContactInfo(selectedContact);
        updateListView();
        listViewSelectItemInit();
    }    

    @FXML
    private void exportFileOnAction(ActionEvent event) {
        new ExportFileController(event);
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
        new ImportFileController(event);
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
            contactListListView.setItems(listObservable);
        });
    }

    @FXML
    private void modifyBtnOnAction(ActionEvent event) throws IOException{
        switchSceneToModifyContact(event);
    }

    @FXML 
    private void delContactOnAction(ActionEvent event) throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION); 
        initAlert(alert);
        Optional<ButtonType> result = alert.showAndWait(); 
            if (result.isPresent() && result.get() == ButtonType.OK) { 
                if(selectedContact.getSelectedContact() != null){
                    System.out.println("Sono arrivato fino a qui");;
                    deleteContactFromAddressBook(selectedContact);
                    switchSceneToDashboard(event);
                }
            } 
            else{ 
                System.out.println("Utente ha annullato l'azione.");
            }
    }
    @FXML
    private void contactSelected() throws IOException{
        try {
            selectedContact.setSelectedContact(contactListListView.getSelectionModel().getSelectedItem());
            setContactDetail(selectedContact.getSelectedContact());
        } catch (Exception e) {
            System.out.println("Errore verificato qui");
        }
    }

    void switchSceneToModifyContact(ActionEvent event) throws IOException{
        view.setModifyScene();
    }
    public void setContactDetail(Contact contact){
        contactNameLbl.setText(contact.getSurname() + " " + contact.getName());
        String[] tel = contact.getTelephoneNumber();
        String[] email = contact.getEmail();
        telephoneLbl.setText(tel[0]);
        telephone2Lbl.setText(tel[1]);
        telephone3Lbl.setText(tel[2]);
        emailLbl.setText(email[0]);
        email2Lbl.setText(email[1]);
        email3Lbl.setText(email[2]);
    }
    private void switchSceneToDashboard(ActionEvent event) throws IOException{
        view.setDashboardScene();
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
    private boolean contactIsFiltered(Contact c, String newValue){
        return c.getName().toLowerCase().contains(newValue.toLowerCase()) ||
                c.getSurname().toLowerCase().contains(newValue.toLowerCase());
    }
    private void initAlert(Alert a){
        a.setTitle("Conferma Azione"); 
        a.setHeaderText("Sei sicuro di voler procedere?"); 
        a.setContentText("Questa azione non può essere annullata."); 
    }
    private void deleteContactFromAddressBook(SelectedContactController s){
        System.out.println("Contatto eliminato!");
        selectedContact = SelectedContactController.getInstance();
        addrBook.removeContact(selectedContact.getSelectedContact());
        updateListView();
        selectedContact.resetSelectedContact();
    }
    private void updateListView(){
        if(addrBook.isEmpty()){
            System.out.println("Rubrica Vuota");
        }
        listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
        contactListListView.setItems(listObservable);
    }
    private void initSelectedContactInfo(SelectedContactController selectedContact){
        setContactDetail(selectedContact.getSelectedContact());
    }
}
