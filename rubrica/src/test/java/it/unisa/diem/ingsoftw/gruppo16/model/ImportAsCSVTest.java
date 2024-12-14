package it.unisa.diem.ingsoftw.gruppo16.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
 * Test class for ImportAsCSV.
 */
public class ImportAsCSVTest {

	@Test
	public void testImportFile() {
		// Prepare the test file
		File testFile = new File("test_contacts.csv");
		try (FileWriter writer = new FileWriter(testFile)) {
			writer.write("Rossi;Mario;1234567890,0987654321;john.doe@example.com,mario.rossi@example.com\n");
			writer.write("Bianchi;Luca;3334445556;luca.bianchi@example.com\n");
		} catch (IOException e) {
			fail("Failed to create test file: " + e.getMessage());
		}

		// Create the ImportAsCSV instance
		ImportAsCSV importAsCSV = new ImportAsCSV();

		// Perform the import
		TreeSet<Contact> importedContacts = importAsCSV.importFile(testFile);

		// Validate the results
		assertNotNull(importedContacts, "Imported contacts should not be null.");
		assertEquals(2, importedContacts.size(), "There should be exactly 2 contacts imported.");

		// Check the first contact
		Contact firstContact = importedContacts.stream().filter(c -> c.getSurname().equals("Rossi")).findFirst()
				.orElse(null);
		assertNotNull(firstContact, "First contact should exist.");
		assertEquals("Mario", firstContact.getName(), "First contact's name should be Mario.");
		assertArrayEquals(new String[] { "1234567890", "0987654321" }, firstContact.getTelephoneNumber(),
				"First contact's phone numbers should match.");
		assertArrayEquals(new String[] { "john.doe@example.com", "mario.rossi@example.com" }, firstContact.getEmail(),
				"First contact's emails should match.");

		// Check the second contact
		Contact secondContact = importedContacts.stream().filter(c -> c.getSurname().equals("Bianchi")).findFirst()
				.orElse(null);
		assertNotNull(secondContact, "Second contact should exist.");
		assertEquals("Luca", secondContact.getName(), "Second contact's name should be Luca.");
		assertArrayEquals(new String[] { "3334445556" }, secondContact.getTelephoneNumber(),
				"Second contact's phone numbers should match.");
		assertArrayEquals(new String[] { "luca.bianchi@example.com" }, secondContact.getEmail(),
				"Second contact's emails should match.");

		// Clean up the test file
		assertTrue(testFile.delete(), "Failed to delete test file.");
	}
}
