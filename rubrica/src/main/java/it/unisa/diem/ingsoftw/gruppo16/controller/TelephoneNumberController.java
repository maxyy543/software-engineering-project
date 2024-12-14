package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.regex.Pattern;

public class TelephoneNumberController extends Validator {

	private static final String PHONE_REGEX = "^(\\+?[0-9]{1,3})?[\\s.-]?\\(?[0-9]{2,4}\\)?[\\s.-]?[0-9]{3,4}[\\s.-]?[0-9]{3,4}$";
	private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

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
	private void setAlertTel(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Numero di telefono non corretto");
        alert.setContentText("Il numero di telefono inserito non è corretto!");
		alert.showAndWait();
    }
}
