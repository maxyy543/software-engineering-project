package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for ExportAsCSV.
 */
public class ExportAsCSVTest {

	private TreeSet<Contact> contacts;
	private ExportAsCSV exporter;
	private String testFileName = "test_export.csv";

	@BeforeEach
	public void setUp() {
		// Prepara il set di contatti
		contacts = new TreeSet<>();
		contacts.add(new Contact("Rossi", "Mario"));
		contacts.add(new Contact("Bianchi", "Luigi"));
		contacts.add(new Contact("Verdi", "Anna"));

		// Inizializza la strategia di esportazione
		exporter = new ExportAsCSV();
	}

	@Test
	public void testExportToCSV() {
		// Chiama la strategia per esportare
		exporter.exportFile(testFileName, contacts);

		// Verifica che il file sia stato creato
		File file = new File(testFileName);
		assertTrue(file.exists(), "The CSV file should exist after export.");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			// Leggi le righe del file
			String line;
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				assertNotNull(line, "Each line in the file should contain data.");
				lineCount++;
			}
			assertEquals(3, lineCount, "There should be three rows corresponding to the three contacts.");
		} catch (Exception e) {
			e.printStackTrace();
			fail("An exception occurred while reading the exported CSV file.");
		} finally {
			// Pulisci il file di test
			if (file.exists()) {
				file.delete();
			}
		}
	}
}
