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
        String testFileName = "test_contacts.json";
        File testFile = new File(testFileName);
        String jsonContent = "[" +
            "{" +
            "\"surname\": \"Orefice\"," +
            "\"name\": \"Marco\"," +
            "\"telephoneNumber\": [\"3762969769\", \"\", \"\"]," +
            "\"email\": [\"m.orefice@example.com\", \"\", \"\"]," +
            "\"isFavourite\": false" +
            "}" +
            "]";

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(jsonContent); 
        } catch (IOException e) {
            fail("Impossibile creare il file di test: " + e.getMessage());
        }

        ImportAsJSON importer = new ImportAsJSON();
        TreeSet<Contact> importedContacts = importer.importFile(testFile);

        assertNotNull(importedContacts);
        assertEquals(1, importedContacts.size()); 

        Contact firstContact = importedContacts.first(); 
        assertEquals("Orefice", firstContact.getSurname());
        assertEquals("Marco", firstContact.getName());
        assertArrayEquals(new String[] { "3762960769", "", "" }, firstContact.getTelephoneNumber());
        assertArrayEquals(new String[] { "m.orefice@example.com", "", "" }, firstContact.getEmail());
        assertFalse(firstContact.getIsFavourite()); 

        assertTrue(testFile.delete());
    }
}
