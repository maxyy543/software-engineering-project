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

		contacts = new TreeSet<>();
		Contact contact1 = new Contact("Orefice", "Marco");
		contact1.setEmail(new String[] { "marco.orefice@email.com" });
		contact1.setTelephoneNumber(new String[] { "12345" });

		Contact contact2 = new Contact("Liguori", "Nicola");
		contact2.setEmail(new String[] { "nicola.liguori@email.com" });
		contact2.setTelephoneNumber(new String[] { "67890" });

		contacts.add(contact1);
		contacts.add(contact2);
	}

	@Test
	public void testExportToXML() {
		String testFileName = System.getProperty("user.dir") + File.separator + "test_export.xml";

		exporter.exportFile(testFileName, contacts);
		File file = new File(testFileName);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			StringBuilder xmlContent = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				xmlContent.append(line);
			}

			String content = xmlContent.toString();
			// Verifica che il contenuto contenga le informazioni attese
			assertTrue(content.contains("<firstName>Marco</firstName>"), "The XML should contain Marco's first name.");
			assertTrue(content.contains("<lastName>Orefice</lastName>"), "The XML should contain Orefice's last name.");
			assertTrue(content.contains("<email>marco.orefice@email.com</email>"),
					"The XML should contain Marco's email.");
			assertTrue(content.contains("<phoneNumber>12345</phoneNumber>"),
					"The XML should contain Marco's phone number.");

			assertTrue(content.contains("<firstName>Nicola</firstName>"),
					"The XML should contain Nicola's first name.");
			assertTrue(content.contains("<lastName>Orefice</lastName>"), "The XML should contain Orefice's last name.");
			assertTrue(content.contains("<email>nicola.liguori@email.com</email>"),
					"The XML should contain Nicola's email.");
			assertTrue(content.contains("<phoneNumber>67890</phoneNumber>"),
					"The XML should contain Nicola's phone number.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}