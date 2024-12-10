package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
/**
 * @Class ImportAsXML
 * @brief Strategia per importare i contatti in un file in formato XML
 * @verison 1.0
 */


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

                    if (name.isEmpty() && surname.isEmpty()) {
                        continue;
                    }

                    Contact contact = new Contact(name, surname);

                    if (element.getElementsByTagName("phoneNumbers").item(0) != null) {
                        String[] phoneNumbers = element.getElementsByTagName("phoneNumbers").item(0).getTextContent().split(";");
                        contact.setTelephoneNumber(phoneNumbers);
                    }

                    if (element.getElementsByTagName("emails").item(0) != null) {
                        String[] emails = element.getElementsByTagName("emails").item(0).getTextContent().split(";");
                        contact.setEmail(emails);
                    }

                    
                    contacts.add(contact);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }
}