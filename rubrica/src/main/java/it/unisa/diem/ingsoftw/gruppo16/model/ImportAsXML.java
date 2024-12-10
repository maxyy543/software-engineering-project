package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;

/**
 * @Class ImportAsXML
 * @brief Strategia per importare i contatti in un file in formato XML
 * @verison 1.0
 */
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class ImportAsXML implements ImportFileStrategy {
    @Override
    public TreeSet<Contact> importFile(String filename) {
        TreeSet<Contact> contacts = new TreeSet<>();
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("contact");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String surname = element.getElementsByTagName("surname").item(0) != null ? element.getElementsByTagName("surname").item(0).getTextContent() : "";

                    // Verifico che almeno uno tra nome e cognome sia presente
                    if (name.isEmpty() && surname.isEmpty()) {
                        continue;
                    }

                    Contact contact = new Contact(name, surname);

                    // Aggiungo i numeri di telefono (separati da ";")
                    if (element.getElementsByTagName("phoneNumbers").item(0) != null) {
                        String[] phoneNumbers = element.getElementsByTagName("phoneNumbers").item(0).getTextContent().split(";");
                        for (int j = 0; j < Math.min(phoneNumbers.length, 3); j++) {
                            contact.setTelephoneNumber(phoneNumbers[j]);
                        }
                    }

                    // Aggiungo le email (separate da ";") solo se presenti
                    if (element.getElementsByTagName("emails").item(0) != null) {
                        String[] emails = element.getElementsByTagName("emails").item(0).getTextContent().split(";");
                        for (int j = 0; j < Math.min(emails.length, 3); j++) {
                            contact.setEmail(emails[j]);
                        }
                    }

                    // Aggiungo il contatto al TreeSet
                    contacts.add(contact);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
