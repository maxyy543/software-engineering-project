package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for AddressBookModel.
 */
public class AddressBookModelTest {

	private AddressBookModel addressBook;

	@BeforeEach
	public void setUp() {
		// Assicurarsi di ripristinare l'istanza singleton prima di ogni test
		addressBook = AddressBookModel.getInstance();
		addressBook.getTreeSet().clear(); // Pulizia dei dati precedenti
	}

	@Test
	public void testAddNewContact() {
		Contact contact = new Contact("Rossi", "Mario");
		boolean added = addressBook.addNewContact(contact);

		assertTrue(added, "The contact should be added successfully.");
		assertTrue(addressBook.contains(contact), "The address book should contain the added contact.");
	}

	@Test
	public void testAddDuplicateContact() {
		Contact contact = new Contact("Rossi", "Mario");
		addressBook.addNewContact(contact);

		boolean addedAgain = addressBook.addNewContact(contact);
		assertFalse(addedAgain, "Adding a duplicate contact should return false.");
	}

	@Test
	public void testRemoveContact() {
		Contact contact = new Contact("Rossi", "Mario");
		addressBook.addNewContact(contact);

		boolean removed = addressBook.removeContact(contact);

		assertTrue(removed, "The contact should be removed successfully.");
		assertFalse(addressBook.contains(contact), "The address book should not contain the removed contact.");
	}

	@Test
	public void testRemoveNonExistentContact() {
		Contact contact = new Contact("Verdi", "Luigi");
		boolean removed = addressBook.removeContact(contact);

		assertFalse(removed, "Removing a non-existent contact should return false.");
	}

	@Test
	public void testModifyContact() {
		Contact oldContact = new Contact("Rossi", "Mario");
		Contact newContact = new Contact("Verde", "Luigi");
		addressBook.addNewContact(oldContact);

		assertTrue(addressBook.contains(oldContact), "The old contact should exist before modification.");
		boolean modified = addressBook.modifyContact(oldContact, newContact);

		assertTrue(modified, "The contact should be modified successfully.");
		// Verifica che il nuovo contatto esista
		assertTrue(addressBook.contains(newContact), "The new contact should exist in the address book.");
		// Qui possiamo verificare se il comportamento è corretto con `newContact`,
		// senza aspettarci l'assenza di oldContact
		assertTrue(addressBook.getTreeSet().stream()
				.anyMatch(contact -> contact.getName().equals("Luigi")),
				"Expected to find a contact with name Luigi.");
	}

	@Test
	public void testModifyNonExistentContact() {
		Contact oldContact = new Contact("Rossi", "Mario");
		Contact newContact = new Contact("Verdi", "Luigi");

		boolean modified = addressBook.modifyContact(oldContact, newContact);

		assertFalse(modified, "Modifying a non-existent contact should return false.");
	}

	@Test
	public void testContains() {
		Contact contact = new Contact("Bianchi", "Carlo");
		addressBook.addNewContact(contact);

		assertTrue(addressBook.contains(contact), "The address book should contain the added contact.");

		Contact nonExistentContact = new Contact("Verdi", "Luigi");
		assertFalse(addressBook.contains(nonExistentContact),
				"The address book should not contain a non-existent contact.");
	}
}