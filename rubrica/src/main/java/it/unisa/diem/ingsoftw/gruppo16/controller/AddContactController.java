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

public class AddContactController extends ContactServiceController implements Initializable{
    @FXML
    private Button saveBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.addrBook = AddressBookModel.getInstance();
        view = ViewUpdateController.getInstance();
        selectedContact = SelectedContactController.getInstance();
        list = new ListViewController();
        listView.setItems(list.getSharedListView());
        listViewSelectItemInit();
        initSearchbar();
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
}
