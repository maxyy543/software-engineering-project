package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeSet;

public class ExportAsXMLTest {

	private ExportAsXML exporter;
	private TreeSet<Contact> contacts;

	@BeforeEach
	public void setUp() {
		exporter = new ExportAsXML();

		// Crea un insieme di contatti di test
		contacts = new TreeSet<>();
		Contact contact1 = new Contact("Rossi", "Mario");
		contact1.setEmail(new String[] { "mario.rossi@email.com" });
		contact1.setTelephoneNumber(new String[] { "12345" });

		Contact contact2 = new Contact("Bianchi", "Luigi");
		contact2.setEmail(new String[] { "luigi.bianchi@email.com" });
		contact2.setTelephoneNumber(new String[] { "67890" });

		Contact contact3 = new Contact("Verdi", "Anna");
		contact3.setEmail(new String[] { "anna.verdi@email.com" });
		contact3.setTelephoneNumber(new String[] { "54321" });

		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact3);
	}

	@Test
	public void testExportToXML() {
		String testFileName = System.getProperty("user.dir") + File.separator + "test_export.xml";

		// Elimina il file prima del test se già esiste
		File file = new File(testFileName);
		if (file.exists()) {
			file.delete();
		}

		// Chiama il metodo per esportare
		exporter.exportFile(testFileName, contacts);

		// Verifica che il file sia stato creato
		assertTrue(file.exists(), "The XML file should exist after export.");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			StringBuilder xmlContent = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				xmlContent.append(line);
			}

			String content = xmlContent.toString();
			// Verifica che il contenuto contenga le informazioni attese
			assertTrue(content.contains("<firstName>Mario</firstName>"), "The XML should contain Mario's first name.");
			assertTrue(content.contains("<lastName>Rossi</lastName>"), "The XML should contain Rossi's last name.");
			assertTrue(content.contains("<email>mario.rossi@email.com</email>"),
					"The XML should contain Mario's email.");
			assertTrue(content.contains("<phoneNumber>12345</phoneNumber>"),
					"The XML should contain Mario's phone number.");

			assertTrue(content.contains("<firstName>Luigi</firstName>"), "The XML should contain Luigi's first name.");
			assertTrue(content.contains("<lastName>Bianchi</lastName>"), "The XML should contain Bianchi's last name.");
			assertTrue(content.contains("<email>luigi.bianchi@email.com</email>"),
					"The XML should contain Luigi's email.");
			assertTrue(content.contains("<phoneNumber>67890</phoneNumber>"),
					"The XML should contain Luigi's phone number.");

			assertTrue(content.contains("<firstName>Anna</firstName>"), "The XML should contain Anna's first name.");
			assertTrue(content.contains("<lastName>Verdi</lastName>"), "The XML should contain Verdi's last name.");
			assertTrue(content.contains("<email>anna.verdi@email.com</email>"), "The XML should contain Anna's email.");
			assertTrue(content.contains("<phoneNumber>54321</phoneNumber>"),
					"The XML should contain Anna's phone number.");

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occurred while reading the XML file.");
		} finally {
			// Pulizia: elimina il file dopo il test
			if (file.exists()) {
				file.delete();
			}
		}
	}
}
