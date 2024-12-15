package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * @brief Classe astratta che eredita tutte le funzionalità della superclasse MainController ed implementa le principali operazioni di gestione dei contatti.
 * Classe che gestisce le operazioni della parte "view" di {@link Contact}.
 * @Class ContactServiceController
 */
public abstract class ContactServiceController extends MainController{
    @FXML
    protected Label contactsLbl;
    @FXML
    protected Button cancelBtn;
    @FXML
    protected TextField telephoneTf;
    @FXML
    protected TextField telephone2Tf;
    @FXML
    protected TextField telephone3Tf;
    @FXML
    protected TextField emailTf;
    @FXML
    protected TextField email2Tf;
    @FXML
    protected TextField email3Tf;
    @FXML
    protected TextField nameTf;
    @FXML
    protected TextField surnameTf;

    /**
     * Se viene premuto il tasto Salva, allora viene impostata la scena della Dashboard.
     * @param[in] event
     * @throws IOException lancia una eccezione se il cambio di scena non va a buon termine
     */
    @FXML
    protected void cancelOnAction(ActionEvent event) throws IOException{
        view.setDashboardScene();
    }
    /**
     * Metodo per ottenere una stringa dalla Textfield di Surname.
     * Se il campo textfield è vuoto, allora surname viene impostato a "".
     * @return surname
     */
    protected String getSurnameTextField(){
        if(surnameTf.getText().isEmpty())
            return "";
        else 
            return surnameTf.getText().trim().substring(0,1).toUpperCase() + 
        surnameTf.getText().trim().substring(1).toLowerCase();
    }
    /**
     * Metodo per ottenere una stringa dalla Textfield di name.
     * Se il campo textfield è vuoto, allora name verrà impostato a "".
     * @return name
     */
    protected String getNameTextField(){
        if(nameTf.getText().isEmpty())
            return "";
        else
            return nameTf.getText().trim().substring(0,1).toUpperCase() + 
        nameTf.getText().trim().substring(1).toLowerCase();
    }
    /**
     * Metodo per ripulire tutte le textfields.
     */
    protected void clearTextFields(){
        surnameTf.clear();
        nameTf.clear();
        emailTf.clear();
        email2Tf.clear();
        email3Tf.clear();
        telephoneTf.clear();
        telephone2Tf.clear();
        telephone3Tf.clear();
    }
    /**
     * Metodo per creare un nuovo contatto con i dati inseriti nei campi delle textfields.
     * @return {@Contact}
     */
    protected Contact contactWithInfoFromTextFields(){
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
