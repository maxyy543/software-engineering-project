package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.regex.Pattern;

public class EmailController extends Validator {

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

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
	private void setAlertEmail(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Email non corretta");
        alert.setContentText("L'email inserita non è corretta!");
		alert.showAndWait();
    }
}
