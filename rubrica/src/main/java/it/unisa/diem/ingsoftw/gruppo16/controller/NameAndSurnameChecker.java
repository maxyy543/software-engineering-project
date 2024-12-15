package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @brief Classe che controlla se sono stati inseriti nome e cognome nel contatto.
 * Classe che estende {@link Validator} ed implementa il metodo check per la verifica del nome e del cognome di un contatto.
 * La verifica si basa su un semplice confronto tra nome e cognome del contatto e con una stringa ""  
 * @Class NameAndSurnameChecker.
 */
public class NameAndSurnameChecker extends Validator{
    /**
	 * @brief Implementazione del metodo check per la verifica della correttezza del nome e del cognome del contatto.
	 * @param[in] contact contatto da validare 
	 * @return false se nome e cognome del contatto sono vuoti oppure se il prossimo validatore restituisce false. True se
	 * il prossimo validatore restituisce true e se nome e cognome sono non vuoti.
	 */
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
    /**
     * Creazione di un alert che segnala il non inserimento di caratteri nel nome e nel cognome del contatto.
     */
    private void setAlertEmptyFields(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Nome e cognome non inseriti!");
        alert.setContentText("I campi del nome e del cognome non possono essere vuoti!");
        alert.showAndWait();
    }
}
