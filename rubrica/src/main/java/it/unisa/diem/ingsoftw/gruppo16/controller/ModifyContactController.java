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


/**
 * @brief Classe per la gestione della modifica ed eliminazione di un contatto.
 * Questa classe eredita tutte le funzionalità di ContactServiceController e, quindi, anche di MainController. 
 * @Class ModifyContactController
 */
public class ModifyContactController extends ContactServiceController implements Initializable{
    @FXML
    private Button saveBtn; ///< bottone per salvare le modifiche.
    @FXML
    private Button deleteContactBtn; ///< bottone per eliminare un contatto.
    /**
     * Inizializzazione di tutti gli attributi necessari.
     */
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
    /**
     * @brief Elimina un contatto ad una seconda conferma da parte dell'utente.
     * Viene mostrato a schermo un messaggio per riconfermare la volontà di eliminare il contatto selezionato.
     * Se l'utente conferma, allora il contatto viene eliminato dalla rubrica, altrimenti l'azione viene anullata.
     * @param[in] event
     */
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
    /**
     * Elimina un contatto dalla rubrica.
     * @param[in] s contatto selezionato.
     */
    private void deleteContactFromAddressBook(SelectedContactController s){
        selectedContact = SelectedContactController.getInstance();
        addrBook.removeContact(selectedContact.getSelectedContact());
        list.updateList();
        selectedContact.resetSelectedContact();
    }
    /**
     * Vengono impostati i dettagli testuali nell'alert.
     * @param[in] a Alert.
     */
    private void initAlert(Alert a){
        a.setTitle("Conferma Azione"); 
        a.setHeaderText("Sei sicuro di voler procedere?"); 
        a.setContentText("Questa azione non può essere annullata."); 
    }
    /**
     * @brief Salva le modifiche di un contatto selezionato, già esistente nella rubrica.
     * Prima di poter modificare il contatto deve essere verificato che il contatto selezionato
     * rispetti i criteri di controllo di validazione (email, numero di telefono e campi vuoti in nome e cognome).
     * Se le modifiche non violano i requisiti di validazione, allora il contatto selezionato viene aggiornato con le nuove modifiche.
     * @param[in] event
     * @throws IOException nel caso in cui DashboardScene non venisse caricato correttamente
     */
    @FXML
    private void saveBtnOnAction(ActionEvent event) throws IOException{
        Contact contact = contactWithInfoFromTextFields();     
        Validator contactVerifier = Validator.link(new TelephoneNumberController(), new EmailController(), new NameAndSurnameChecker());
        if(contactVerifier.check(contact) && (addrBook != null)){
            addrBook.modifyContact(selectedContact.getSelectedContact(), contact);
            ObservableList<Contact> listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
            listView.setItems(listObservable);
            clearTextFields();
            view.setDashboardScene();
        }
    }
    /**
     * @brief Inizializza i dati del contatto selezionato sulla scena DetailContact.
     * @param selectedContact contatto selezionato.
     */
    private void initSelectedContactInfo(SelectedContactController selectedContact){
        setContactDetail(selectedContact.getSelectedContact());
    }
    /**
     * @brief Vengono impostati nelle label tutte le informazioni del contatto selezionato.
     * @param[in] contact Contatto da mostrare sulla pagina Contact Detail.
     */
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
