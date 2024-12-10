package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

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


public class AddandModifyController implements Initializable{

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

    private AddressBook addrBook;
    private ObservableList<Contact> listObservable;
    private SelectedContactController selectedContact;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addrBook = AddressBookModel.getInstance();
        listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
        selectedContact = SelectedContactController.getInstance();
        listView.setItems(listObservable);
        
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
    private void exportFileOnAction(ActionEvent event) {
    }

    @FXML
    private void addButtonOnAction(ActionEvent event) {
    }
    /* 
    @FXML
    private void favouriteListOnAction(ActionEvent event) {
    }
    */
    @FXML
    private void importFileOnAction(ActionEvent event) {
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
    /* 
    @FXML 
    private void delContactOnAction(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION); 
        alert.setTitle("Conferma Azione"); 
        alert.setHeaderText("Sei sicuro di voler procedere?"); 
        alert.setContentText("Questa azione non può essere annullata."); 
        Optional<ButtonType> result = alert.showAndWait(); 
            if (result.isPresent() && result.get() == ButtonType.OK) { 
                System.out.println("Utente ha confermato l'azione."); 
            } 
            else{ 
                System.out.println("Utente ha annullato l'azione.");
            }
    }*/
    @FXML
    private void cancelOnAction(ActionEvent event) throws IOException{
        switchSceneToDashboard(event);
    }

    @FXML
    private void saveBtnOnAction(ActionEvent event) throws IOException{
        
        if(!((surnameTf.getText().trim().isEmpty()) || (nameTf.getText().trim().isEmpty()))){
            Contact contact = new Contact(
                surnameTf.getText().trim().substring(0,1).toUpperCase() + surnameTf.getText().trim().substring(1).toLowerCase(),
                nameTf.getText().trim().substring(0,1).toUpperCase() + nameTf.getText().trim().substring(1).toLowerCase());
            String[] tel =  {telephoneTf.getText().trim().toUpperCase(), telephone2Tf.getText().trim(), telephone3Tf.getText().trim()};
            String[] email =  {emailTf.getText().trim(), email2Tf.getText().trim(), email3Tf.getText().trim()};
            contact.setTelephoneNumber(tel);
            contact.setEmail(email);

            
            Validator verificaContatto = Validator.link(new EmailController(), new TelephoneNumberController());

            if(verificaContatto.check(contact) && (addrBook != null)){
                addrBook.addNewContact(contact);
                ObservableList<Contact> listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
                listView.setItems(listObservable);
                surnameTf.clear();
                nameTf.clear();
                emailTf.clear();
                email2Tf.clear();
                email3Tf.clear();
                telephoneTf.clear();
                telephone2Tf.clear();
                telephone3Tf.clear();
            } 
        }
    }
    @FXML
    private void contactSelected() throws IOException{
        selectedContact.setSelectedContact(listView.getSelectionModel().getSelectedItem());
        openDetailOf(selectedContact.getSelectedContact());
    }
    private void openDetailOf(Contact contact) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface2.fxml"));;
            Parent root = loader.load();
            DetailController detailController = loader.getController();
            detailController.setContactDetail(contact);
            Scene scene = new Scene(root);
            Stage stage = (Stage) listView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void switchSceneToDashboard(ActionEvent event) throws IOException{
        try {
            Parent scene2Root = FXMLLoader.load(getClass().getResource("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface.fxml")); 
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow(); 
            Scene scene2 = new Scene(scene2Root); 
            stage.setScene(scene2); 
            stage.show();
        } catch (Exception e) {
            System.out.println("Errore qui");
        }
    }
}
