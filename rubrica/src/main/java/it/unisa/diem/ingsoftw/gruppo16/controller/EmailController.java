package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.regex.Pattern;

/**
 * @brief Classe che controlla la correttezza d3lle email di un contatto.
 * Classe che estende {@link Validator} ed implementa il metodo check per la verifica della correttezza
 * delle email inserite in un contatto. Il giudizio di un numero di telefono corretto si basa
 * sulla verifica di una regular expression. 
 * @Class TelephoneNumberController.
 */
public class EmailController extends Validator {

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	/**
	 * @brief Implementazione del metodo check per la verifica della correttezza deelle email di un contatto.
	 * @param[in] contact contatto da validare 
	 * @return false se almeno una delle email non è corretta oppure se il prossimo validatore restituisce false. True se
	 * il prossimo validatore restituisce true e se tutte le email sono corrette.
	 */
	@Override
	public boolean check(Contact contact) {

		if (contact == null) {
			return false;
		}

		String[] emails = contact.getEmail();

		for (String email : emails) {
			if (!(EMAIL_PATTERN.matcher(email).matches() || (email.equals("")))) {
				setAlertEmail();
				return false;
			}
		}

		return checkNext(contact);
	}
	/**
	 * Creazione di alert per segnalare l'errore nell'inserimento dell'email
	 */
	private void setAlertEmail(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Email non corretta");
        alert.setContentText("L'email inserita non è corretta!");
		alert.showAndWait();
    }
}
