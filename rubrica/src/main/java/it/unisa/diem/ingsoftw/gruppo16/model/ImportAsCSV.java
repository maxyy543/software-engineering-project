package it.unisa.diem.ingsoftw.gruppo16.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class ImportAsCSV implements ImportFileStrategy {
    @Override
    public TreeSet<Contact> importFile(String filename) {
        TreeSet<Contact> contacts = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("contacts.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Divido la riga in 4 parti
                String[] data = line.split(",");
                String name = (data.length > 0 && !data[0].isEmpty()) ? data[0] : "";
                String surname = (data.length > 1 && !data[1].isEmpty()) ? data[1] : "";

                // Verifico che almeno uno tra nome e cognome sia presente
                if (name.isEmpty() && surname.isEmpty()) {
                    continue;
                }

                // Creo un nuovo oggetto Contact
                Contact contact = new Contact(name, surname);

                // Aggiungo i numeri di telefono se presenti
                if (data.length > 3 && !data[3].isEmpty()) {
                    // Divido i numeri di telefono separati da ';' in un array di stringhe
                    String[] phoneNumbers = data[3].split(";");
                    contact.setTelephoneNumber(phoneNumbers); // Passo l'array di stringhe
                }

                // Aggiungo il contatto al TreeSet
                contacts.add(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
