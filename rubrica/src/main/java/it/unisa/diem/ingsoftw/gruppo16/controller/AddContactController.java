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

/**
 * @brief Classe view-controller per l'aggiunta di nuovi contatti.
 * Questa classe eredita tutti i metodi principali di {@link ContactServiceController} e definisce il metodo per salvare un nuovo contatto
 * nella rubrica (saveBtnAction).
 * @Class AddContactController
 */
public class AddContactController extends ContactServiceController implements Initializable{
    @FXML
    private Button saveBtn; ///< bottone per il salvataggio del contatto;

    /**
     * Implementazione metodo initizalize di Initializable
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.addrBook = AddressBookModel.getInstance();
        view = ViewUpdateController.getInstance();
        selectedContact = SelectedContactController.getInstance();
        list = new ListViewController();
        listView.setItems(list.getSharedListView());
        listViewSelectItemInit();
        initSearchbar();
        allContactListBind();
    }
    /**
     * Metodo che raccoglie tutti i dati dalle textfield (richiamando un metodo esterno) ed effettua il controllo con il Validator
     * prima di inserire il contatto nella lista. Se il contatto inserito rispetta i criteri di valutazione stabiliti
     * ({@link TelephoneNumberController},{@link EmailController}, {@link NameAndSurnameChecker}), allora 
     * può essere inserito nella rubrica
     * @param[in] event
     * @throws IOException
     */
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
