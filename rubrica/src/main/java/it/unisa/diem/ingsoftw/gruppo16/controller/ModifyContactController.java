package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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



public class ModifyContactController extends ContactServiceController implements Initializable{
    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteContactBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.addrBook = AddressBookModel.getInstance();
        view = ViewUpdateController.getInstance();
        selectedContact = SelectedContactController.getInstance();
        list = new ListViewController();
        initSelectedContactInfo(selectedContact);
        listView.setItems(list.getSharedListView());
        listViewSelectItemInit();
        initSearchbar();
        allContactListBind();
    }    
    @FXML 
    private void delContactOnAction(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION); 
        initAlert(alert);
        Optional<ButtonType> result = alert.showAndWait(); 
        if (result.isPresent() && result.get() == ButtonType.OK) { 
            if(selectedContact.getSelectedContact() != null){
                deleteContactFromAddressBook(selectedContact);
                view.setDashboardScene();
            }
        } 
        else{ 
            System.out.println("Utente ha annullato l'azione.");
        }
    }
    private void deleteContactFromAddressBook(SelectedContactController s){
        selectedContact = SelectedContactController.getInstance();
        addrBook.removeContact(selectedContact.getSelectedContact());
        list.updateList();
        selectedContact.resetSelectedContact();
    }
    private void initAlert(Alert a){
        a.setTitle("Conferma Azione"); 
        a.setHeaderText("Sei sicuro di voler procedere?"); 
        a.setContentText("Questa azione non può essere annullata."); 
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
}
