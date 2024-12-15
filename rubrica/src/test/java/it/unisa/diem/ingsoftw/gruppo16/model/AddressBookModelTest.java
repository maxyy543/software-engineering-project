package it.unisa.diem.ingsoftw.gruppo16.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressBookModelTest {

	private AddressBookModel addressBookModel;

	@BeforeEach
	public void init() {
		addressBookModel = new AddressBookModel();
	}

	@Test
	public void oneInstanceTest() {
		AddressBookModel instance_1 = AddressBookModel.getInstance();
		AddressBookModel instance_2 = AddressBookModel.getInstance();
		assertSame(instance_1, instance_2);
	}

	@Test
	public void instanceIsNotNullTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		assertNotNull(instance, "Instance returned is null");
	}

	@Test
	public void getTreeSetIsNotNull() {
		AddressBookModel instance = AddressBookModel.getInstance();
		TreeSet<Contact> contacts = instance.getTreeSet();
		assertNotNull(contacts, "Collection returned is null");
	}

	@Test
	public void existenceOfContactTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		Contact c = new Contact("Orefice", "Marco");
		instance.addNewContact(c);
		assertTrue(instance.contains(c), "The contact is not in the address book");
	}

	@Test
	public void isEmptyReturnTrueTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		assertTrue(instance.isEmpty(), "The return of isEmpty() is false");
	}

	@Test
	public void isEmptyReturnFalseTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		Contact c = new Contact("Orefice", "Marco");
		instance.addNewContact(c);
		assertFalse(instance.isEmpty(), "The return of isEmpty() is true");
	}

	@Test
	public void clearAddressBookTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		instance.addNewContact(new Contact("Orefice", "Marco"));
		instance.addNewContact(new Contact("Lanzetta", "Luca"));
		instance.addNewContact(new Contact("Liguori", "Nicola"));
		instance.clearAddressBook();
		assertTrue(instance.getTreeSet().isEmpty(),
				"The address book is not empty after calling the clearAddressBook() method");
	}

	@Test
	public void clearAddressBookWhenIsEmptyTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		assertTrue(instance.getTreeSet().isEmpty(),
				"the address book was not left empty after calling the clearAddressBook() method.");
	}

	@Test
	public void addNewContactTheContactIsNewTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		Contact c = new Contact("Orefice", "Marco");
		assertTrue(instance.addNewContact(c), "Contact addition failed");
		assertTrue(instance.contains(c), "Address book does not contain the newly added contact");
	}

	@Test
	public void addNewContactTheContactIsExisting() {
		AddressBookModel instance = AddressBookModel.getInstance();
		Contact c = new Contact("Orefice", "Marco");
		instance.addNewContact(c);
		assertFalse(instance.addNewContact(c), "The contact already exists but the return is not false");
	}

	@Test
	public void removeContactTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		Contact c = new Contact("Orefice", "Marco");
		instance.addNewContact(c);
		assertTrue(instance.removeContact(c), "Removal does not return true");
		assertFalse(instance.contains(c), "The contact has not been removed from the address book");
	}

	@Test
	public void modifyContactTest() {
		AddressBookModel instance = AddressBookModel.getInstance();
		Contact oldC = new Contact("Orefice", "Marco");
		instance.addNewContact(oldC);
		Contact newC = new Contact("Liguori", "Nicola");
		assertTrue(instance.modifyContact(oldC, newC), "Modification returns false");
		assertFalse(instance.contains(oldC), "The old contact has not been removed");
		assertTrue(instance.contains(newC), "The new contact is not in the address book");

	}
}
