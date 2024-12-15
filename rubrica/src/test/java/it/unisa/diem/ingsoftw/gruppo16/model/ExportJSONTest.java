package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeSet;

public class ExportJSONTest {

	private ExportAsJSON exporter;
	private TreeSet<Contact> contacts;

	@BeforeEach
	public void setUp() {
		exporter = new ExportAsJSON();

		contacts = new TreeSet<>();
		contacts.add(new Contact("Lanzetta", "Luca"));
		contacts.add(new Contact("Liguori", "Nicola"));
	}

	@Test
	public void testExportToJSON() {
		String testFileName = System.getProperty("user.dir") + File.separator + "test_export.json";

		// Chiama la strategia per esportare
		exporter.exportFile(testFileName, contacts);

		try (BufferedReader br = new BufferedReader(new FileReader(testFileName))) {
			String jsonContent = br.readLine();
			assertNotNull(jsonContent, "The JSON content should not be null.");
			assertTrue(jsonContent.contains("Lanzetta"), "The JSON content should contain 'Lanzetta'.");
			assertTrue(jsonContent.contains("Liguori"), "The JSON content should contain 'Liguori'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}