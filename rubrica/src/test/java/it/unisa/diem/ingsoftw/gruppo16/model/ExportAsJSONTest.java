package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeSet;

public class ExportAsJSONTest {

	private ExportAsJSON exporter;
	private TreeSet<Contact> contacts;

	@BeforeEach
	public void setUp() {
		exporter = new ExportAsJSON();

		// Crea un insieme di contatti di test
		contacts = new TreeSet<>();
		contacts.add(new Contact("Rossi", "Mario"));
		contacts.add(new Contact("Bianchi", "Luigi"));
		contacts.add(new Contact("Verdi", "Anna"));
	}

	@Test
	public void testExportToJSON() {
		String testFileName = System.getProperty("user.dir") + File.separator + "test_export.json";

		// Elimina il file prima del test se già esiste
		File file = new File(testFileName);
		if (file.exists()) {
			file.delete();
		}

		// Chiama la strategia per esportare
		exporter.exportFile(testFileName, contacts);

		// Verifica che il file sia stato creato
		assertTrue(file.exists(), "The JSON file should exist after export.");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String jsonContent = br.readLine();
			assertNotNull(jsonContent, "The JSON content should not be null.");
			assertTrue(jsonContent.contains("Rossi"), "The JSON content should contain 'Rossi'.");
			assertTrue(jsonContent.contains("Bianchi"), "The JSON content should contain 'Bianchi'.");
			assertTrue(jsonContent.contains("Verdi"), "The JSON content should contain 'Verdi'.");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occurred while reading the JSON file.");
		} finally {
			// Pulizia: elimina il file dopo il test
			if (file.exists()) {
				file.delete();
			}
		}
	}
}
