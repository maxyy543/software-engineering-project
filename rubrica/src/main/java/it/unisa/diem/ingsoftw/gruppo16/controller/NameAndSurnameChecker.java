package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NameAndSurnameChecker extends Validator{

    @Override
    public boolean check(Contact contact) {

        if(contact == null){
            return false;
        }
        if(contact.getSurname().equals("") && contact.getName().equals("")){
            setAlertEmptyFields();
            return false;
        }    
        return checkNext(contact);
    }
    private void setAlertEmptyFields(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Nome e cognome non inseriti!");
        alert.setContentText("I campi del nome e del cognome non possono essere vuoti!");
    }
}
