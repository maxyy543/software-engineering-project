package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

import java.util.regex.Pattern;

public class TelephoneNumberController extends Validator {

	private static final String PHONE_REGEX = "^(\\+?[0-9]{1,3})?[-.\\s]?\\(?[0-9]{2,4}\\)?[-.\\s]?[0-9]{3,4}[-.\\s]?[0-9]{3,4}$";
	private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

	@Override
	public boolean check(Contact contact) {

		if (contact == null || contact.getTelephoneNumber() == null) {
			return false;
		}

		String[] phoneNumbers = contact.getTelephoneNumber();

		for (String phoneNumber : phoneNumbers) {
			if (phoneNumber != null && PHONE_PATTERN.matcher(phoneNumber).matches()) {
				return true;
			}
		}

		return false;
	}
}
