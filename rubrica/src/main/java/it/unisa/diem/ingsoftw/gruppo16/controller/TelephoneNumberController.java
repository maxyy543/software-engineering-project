package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.regex.Pattern;

/**
 * @brief Classe che controlla la correttezza dei numeri di telefono di un contatto.
 * Classe che estende {@link Validator} ed implementa il metodo check per la verifica della correttezza
 * dei numeri di telefono inseriti nel contatto. Il giudizio di un numero di telefono corretto si basa
 * sulla verifica di una regular expression. 
 * @Class TelephoneNumberController.
 */
public class TelephoneNumberController extends Validator {

	private static final String PHONE_REGEX = "^(\\+?[0-9]{1,3})?[\\s.-]?\\(?[0-9]{2,4}\\)?[\\s.-]?[0-9]{3,4}[\\s.-]?[0-9]{3,4}$";
	private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

	/**
	 * @brief Implementazione del metodo check per la verifica della correttezza dei numeri di telefono di un contatto.
	 * @param[in] contact contatto da validare 
	 * @return false se almeno un numero di telefono non è corretto oppure se il prossimo validatore restituisce false. True se
	 * il prossimo validatore restituisce true e se tutti i numeri di telefoni sono corretti.
	 */
	@Override
	public boolean check(Contact contact) {

		if (contact == null) {
			return false;
		}

		String[] phoneNumbers = contact.getTelephoneNumber();

		for (String phoneNumber : phoneNumbers) {
			if (!(PHONE_PATTERN.matcher(phoneNumber).matches() || (phoneNumber.equals("")))) {
				setAlertTel();
				return false;
			}
		}

		return checkNext(contact);
	}
	/**
	 * Alert che segnala l'errore nell'inserimento del numero di telefono
	 */
	private void setAlertTel(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Numero di telefono non corretto");
        alert.setContentText("Il numero di telefono inserito non è corretto!");
		alert.showAndWait();
    }
}
