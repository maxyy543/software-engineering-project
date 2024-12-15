package it.unisa.diem.ingsoftw.gruppo16.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactTest {
	private AddressBookModel addressBookModel;

	@BeforeEach
	public void init() {
		addressBookModel = new AddressBookModel();
	}

	@Test
	public void compareToTest() {
		Contact c_1 = new Contact("Orefice", "Marco");
		Contact c_2 = new Contact("Orefice", "Marco");
		Contact c_3 = new Contact("Liguori", "Nicola");
		assertEquals(0, c_1.compareTo(c_2),
				"Contacts have the same surname and name but were not considered equal in the comparison");
		assertTrue(c_3.compareTo(c_1) < 0, "Liguori Nicola was not ordained before Orefice Marco");
		assertTrue(c_1.compareTo(c_3) > 0, "Orefice Marco was not ordained after Liguori Nicola");
	}

	@Test
	public void equalWithSameSurnameTest() {
		Contact c_1 = new Contact("Orefice", "Marco");
		Contact c_2 = new Contact("Orefice", "Mario");
		assertFalse(c_1.equals(c_2),
				"Contacts with the same surname but different names should not be considered the same");
	}

	@Test
	public void equalWithSameNameTest() {
		Contact c_1 = new Contact("Orefice", "Marco");
		Contact c_2 = new Contact("Lanzetta", "Marco");
		assertFalse(c_1.equals(c_2),
				"Contacts with the same first name but different last name should not be considered the same");
	}

	@Test
	public void equalWithSameNameAndSurnameTest() {
		Contact c_1 = new Contact("Orefice", "Marco");
		Contact c_2 = new Contact("Orefice", "Marco");
		assertTrue(c_1.equals(c_2),
				"The two contacts have the same first and last names but are not considered the same");
	}
	
}
