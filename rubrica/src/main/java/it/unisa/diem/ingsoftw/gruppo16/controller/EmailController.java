package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

import java.util.regex.Pattern;

public class EmailController extends Validator {

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	@Override
	public boolean check(Contact contact) {

		if (contact == null || contact.getEmail() == null) {
			return false;
		}

		String[] emails = contact.getEmail();

		for (String email : emails) {
			if (email != null && EMAIL_PATTERN.matcher(email).matches()) {
				return true;
			}
		}

		return false;
	}
}
