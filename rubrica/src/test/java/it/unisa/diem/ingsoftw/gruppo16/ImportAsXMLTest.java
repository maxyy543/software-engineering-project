package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ImportAsXMLTest {

    @Test
    void testImportFile() {
        // Step 1: Creazione di un file XML di test
        String testFileName = "test_contact.xml";
        File testFile = new File(testFileName);

        String xmlContent = "<List>" +
                "  <Contact>" +
                "    <surname>Orefice</surname>" +
                "    <name>Max</name>" +
                "    <telephoneNumber>3319142301</telephoneNumber>" +
                "    <email>lucalanzett@icloud.com</email>" +
                "    <isFavourite>false</isFavourite>" +
                "  </Contact>" +
                "</List>";

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(xmlContent);
        } catch (IOException e) {
            fail("Impossibile creare il file di test: " + e.getMessage());
        }

        // Step 2: Importazione dei contatti usando ImportAsXML
        ImportAsXML importer = new ImportAsXML();
        TreeSet<Contact> importedContacts = importer.importFile(testFile);

        // Step 3: Validazione del risultato
        assertNotNull(importedContacts, "I contatti importati non devono essere nulli.");
        assertEquals(1, importedContacts.size(), "Il numero di contatti importati non è corretto.");

        // Validazione del contatto importato
        Contact contact = importedContacts.stream()
                .findFirst()
                .orElse(null);

        assertNotNull(contact, "Il contatto non è stato importato correttamente.");
        assertEquals("Orefice", contact.getSurname(), "Il cognome del contatto non è corretto.");
        assertEquals("Max", contact.getName(), "Il nome del contatto non è corretto.");
        assertEquals("3319142301", contact.getTelephoneNumber()[0],
                "Il numero di telefono del contatto non è corretto.");
        assertEquals("lucalanzett@icloud.com", contact.getEmail()[0], "L'email del contatto non è corretta.");
        assertFalse(contact.getIsFavourite(), "Lo stato isFavourite del contatto non è corretto.");

        // Pulizia del file di test
        assertTrue(testFile.delete(), "Il file di test non è stato eliminato correttamente.");
    }
}

