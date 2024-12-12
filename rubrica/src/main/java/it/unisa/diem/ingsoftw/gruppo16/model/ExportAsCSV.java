package it.unisa.diem.ingsoftw.gruppo16.model;

import java.io.FileWriter;
import java.io.IOException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
 * @Class ExportAsCSV
 * @brief Strategia per esportare i contatti in un file in formato CSV
 * @version 1.0
 *
 * @param[in] filename Path del file da esportare
 * @param[in] contacts Contatti da salvare nel file CSV
 */

public class ExportAsCSV implements ExportFileStrategy {
    @Override
    public void exportFile(String filename, TreeSet<Contact> contacts) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Contact contact : contacts) {
                writer.append(contact.getName()).append(',').append(contact.getSurname()).append(',');

                // Append up to three phone numbers
                String[] phoneNumbers = contact.getTelephoneNumber();
                for (int i = 0; i < 3; i++) {
                    if (i < phoneNumbers.length) {
                        writer.append(phoneNumbers[i]);
                    }
                    writer.append(',');
                }

                // Append up to three emails
                String[] emails = contact.getEmail();
                for (int i = 0; i < 3; i++) {
                    if (i < emails.length) {
                        writer.append(emails[i]);
                    }
                    writer.append(',');
                }

                writer.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}