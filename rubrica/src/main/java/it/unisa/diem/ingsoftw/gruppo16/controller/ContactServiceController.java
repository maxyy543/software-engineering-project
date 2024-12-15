package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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


    @FXML
    protected void cancelOnAction(ActionEvent event) throws IOException{
        view.setDashboardScene();
    }
    protected String getSurnameTextField(){
        if(surnameTf.getText().isEmpty())
            return "";
        else 
            return surnameTf.getText().trim().substring(0,1).toUpperCase() + 
        surnameTf.getText().trim().substring(1).toLowerCase();
    }
    protected String getNameTextField(){
        if(nameTf.getText().isEmpty())
            return "";
        else
            return nameTf.getText().trim().substring(0,1).toUpperCase() + 
        nameTf.getText().trim().substring(1).toLowerCase();
    }
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
