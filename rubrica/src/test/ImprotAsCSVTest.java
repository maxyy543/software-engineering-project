package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ImportAsCSVTest {

	@Test
	void testImportFile() {
		// Step 1: Creazione di un file CSV di test
		String testFileName = "test_contacts.csv";
		File testFile = new File(testFileName);

		try (FileWriter writer = new FileWriter(testFile)) {
			writer.write("Doe;John;1234567890,9876543210;john.doe@example.com,john.alt@example.com\n");
			writer.write("Smith;Jane;5551234567;jane.smith@example.com\n");
		} catch (IOException e) {
			fail("Impossibile creare il file di test: " + e.getMessage());
		}

		// Step 2: Creazione dell'oggetto ImportAsCSV e importazione del file
		ImportAsCSV importer = new ImportAsCSV();
		TreeSet<Contact> importedContacts = importer.importFile(testFile);

		// Step 3: Validazione del risultato
		assertNotNull(importedContacts, "I contatti importati non devono essere nulli.");
		assertEquals(2, importedContacts.size(), "Il numero di contatti importati non è corretto.");

		// Step 4: Validazione dei dettagli dei contatti
		Contact[] contactsArray = importedContacts.toArray(new Contact[0]);

		Contact firstContact = contactsArray[0];
		assertEquals("Doe", firstContact.getSurname(), "Il cognome del primo contatto non è corretto.");
		assertEquals("John", firstContact.getName(), "Il nome del primo contatto non è corretto.");
		assertArrayEquals(new String[] { "1234567890", "9876543210" }, firstContact.getTelephoneNumber(),
				"I numeri di telefono del primo contatto non sono corretti.");
		assertArrayEquals(new String[] { "john.doe@example.com", "john.alt@example.com" }, firstContact.getEmail(),
				"Le email del primo contatto non sono corrette.");

		Contact secondContact = contactsArray[1];
		assertEquals("Smith", secondContact.getSurname(), "Il cognome del secondo contatto non è corretto.");
		assertEquals("Jane", secondContact.getName(), "Il nome del secondo contatto non è corretto.");
		assertArrayEquals(new String[] { "5551234567" }, secondContact.getTelephoneNumber(),
				"I numeri di telefono del secondo contatto non sono corretti.");
		assertArrayEquals(new String[] { "jane.smith@example.com" }, secondContact.getEmail(),
				"Le email del secondo contatto non sono corrette.");

		// Step 5: Pulizia del file di test
		assertTrue(testFile.delete(), "Il file di test non è stato eliminato correttamente.");
	}
}