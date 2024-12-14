package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;

public class ImportAsCSVTest {

    private File tempFile; 

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("testContacts", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            
            writer.write("Lanzetta,Luca,1234567890,0987654321,1122334455,luca.lanzetta@example.com,llanzetta@example.org,luca.l@example.net\n");
            writer.write("Liguori,Nicola,9876543210,0123456789,5566778899,nicola.liguori@example.com,nl@example.org,nicola.l@example.net\n");
        }
        tempFile.deleteOnExit(); 
    }

    @Test
    public void testImportFile() {
        ImportAsCSV importAsCSV = new ImportAsCSV();

        TreeSet<Contact> contacts = importAsCSV.importFile(tempFile);

        assertNotNull(contacts, "Il risultato non deve essere nullo.");
        assertEquals(2, contacts.size(), "Dovrebbero esserci 2 contatti.");

        Contact firstContact = contacts.first();
        assertEquals("Lanzetta", firstContact.getSurname(), "Il cognome del primo contatto deve essere 'Lanzetta'.");
        assertEquals("Luca", firstContact.getName(), "Il nome del primo contatto deve essere 'Luca'.");
        
        Contact secondContact = contacts.last();
        assertEquals("Liguori", secondContact.getSurname(), "Il cognome del secondo contatto deve essere 'Liguori'.");
        assertEquals("Nicola", secondContact.getName(), "Il nome del secondo contatto deve essere 'Nicola'.");
    }

    @Test
    public void testImportFileWithInvalidFile() {
        ImportAsCSV importAsCSV = new ImportAsCSV();

        File invalidFile = new File("non_existent_file.csv");
        TreeSet<Contact> contacts = importAsCSV.importFile(invalidFile);

        assertNull(contacts, "Il risultato dovrebbe essere nullo per un file non valido.");
    }
}
