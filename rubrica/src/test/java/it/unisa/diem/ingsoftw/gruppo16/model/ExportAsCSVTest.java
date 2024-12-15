package it.unisa.diem.ingsoftw.gruppo16.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class ExportAsCSVTest {

	private TreeSet<Contact> contacts;
	private ExportAsCSV exporter;
	private String testFileName = "test_export.csv";

	@BeforeEach
	public void setUp() {

		contacts = new TreeSet<>();
		contacts.add(new Contact("Rossi", "Mario"));
		contacts.add(new Contact("Bianchi", "Luigi"));

		exporter = new ExportAsCSV();
	}

	@Test
	public void testExportToCSV() {
		exporter.exportFile(testFileName, contacts);
		File file = new File(testFileName);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				assertNotNull(line, "Each line in the file should contain data.");
				lineCount++;
			}
			assertEquals(2, lineCount, "There should be three rows corresponding to the three contacts.");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}