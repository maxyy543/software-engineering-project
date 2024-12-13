package it.unisa.diem.ingsoftw.gruppo16.model;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ImportAsJSONTest {

@Test
void testImportFile() {
// Step 1: Creazione di un file JSON di test
String testFileName = "test_contacts.json";
File testFile = new File(testFileName);

String jsonContent = "[" +
"  {" +
"    \"MAX_TELEPHONE_NUMBER\": 3," +
"    \"MAX_EMAIL_NUMBER\": 3," +
"    \"surname\": \"Orefice\"," +
"    \"name\": \"Max\"," +
"    \"telephoneNumber\": [\"3319142301\", \"\", \"\"]," +
"    \"email\": [\"lucalanzett@icloud.com\", \"\", \"\"]," +
"    \"isFavourite\": false" +
"  }" +
"]";

try (FileWriter writer = new FileWriter(testFile)) {
writer.write(jsonContent);
} catch (IOException e) {
fail("Impossibile creare il file di test: " + e.getMessage());
}

// Step 2: Creazione dell'oggetto ImportAsJSON e importazione del file
ImportAsJSON importer = new ImportAsJSON();
TreeSet<Contact> importedContacts = importer.importFile(testFile);

// Step 3: Validazione del risultato
assertNotNull(importedContacts, "I contatti importati non devono essere nulli.");
assertEquals(1, importedContacts.size(), "Il numero di contatti importati non è corretto.");

// Step 4: Validazione dei dettagli dei contatti
Contact[] contactsArray = importedContacts.toArray(new Contact[0]);

Contact firstContact = contactsArray[0];
assertEquals("Orefice", firstContact.getSurname(), "Il cognome del primo contatto non è corretto.");
assertEquals("Max", firstContact.getName(), "Il nome del primo contatto non è corretto.");
assertArrayEquals(new String[] { "3319142301", "", "" }, firstContact.getTelephoneNumber(),
"I numeri di telefono del primo contatto non sono corretti.");
assertArrayEquals(new String[] { "lucalanzett@icloud.com", "", "" }, firstContact.getEmail(),
"Le email del primo contatto non sono corrette.");
firstContact.setIsFavourite(true);
assertTrue(firstContact.getIsFavourite(), "Lo stato 'isFavourite' non è stato impostato correttamente.");

// Step 5: Pulizia del file di test
assertTrue(testFile.delete(), "Il file di test non è stato eliminato correttamente.");
}
}