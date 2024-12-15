package it.unisa.diem.ingsoftw.gruppo16.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
* @Class ExportAsCSV
* @brief Strategia per esportare i contatti in un file in formato CSV
* @version 1.0
*
*/
public class ExportAsCSV implements ExportFileStrategy {

    /**
     * @brief metodo per esportare una rubrica in un file CSV.
     * 
     * Metodo che implementa l'exportFile di {@link ExportFileStrategy}.
     * Il metodo visita tutta la lista di contatti e sovrascrive sul file ogni contatto(cognome,nome, numeri di telefono e email)
     * presente nella lista. 
     * 
     * 
     * @param[inout] filename path del file su cui esportare la rubrica.
     * @param[in] contacts {@link TreeSet} lista di contatti.
     *  
     */
    @Override
    public void exportFile(String filename, TreeSet<Contact> contacts) {
    try (FileWriter writer = new FileWriter(filename)) {
        for (Contact contact : contacts) {
            writer.append(contact.getSurname()).append(',').append(contact.getName()).append(',');
                String[] phoneNumbers = contact.getTelephoneNumber();
                for (int i = 0; i < 3; i++) {
                    if (i < phoneNumbers.length) {
                        writer.append(phoneNumbers[i]);
                    }
                    writer.append(',');
                }
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