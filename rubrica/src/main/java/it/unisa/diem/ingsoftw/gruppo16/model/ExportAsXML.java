package it.unisa.diem.ingsoftw.gruppo16.model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
 * @Class ExportAsXML
 * @brief Strategia per esportare i contatti in un file in formato XML
 * @verison 1.0
 */
public class ExportAsXML implements ExportFileStrategy {
    /**
     * 
     * @param[in] filename Path del file da esportare
     * @param[in] contacts Contatti da salvare nel file XML
     */
    @Override
    public void exportFile(String filename, TreeSet<Contact> contacts) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("<contacts>\n");
            for (Contact c : contacts) {
                writer.write("  <contact>\n");
                writer.write("    <firstName>" + c.getName() + "</firstName>\n"); // Scrive il nome del contatto
                writer.write("    <lastName>" + c.getSurname() + "</lastName>\n"); // Scrive il cognome del contatto
                
                // Scrive fino a tre email
                String[] emails = c.getEmail();  // Cambia qui, per usare l'array
                for (int i = 0; i < emails.length && i < 3; i++) { // Itera sugli array
                    writer.write("    <email>" + emails[i] + "</email>\n");
                }
    
                // Scrive fino a tre numeri di telefono
                String[] phoneNumbers = c.getTelephoneNumber();  // Cambia qui, per usare l'array
                for (int i = 0; i < phoneNumbers.length && i < 3; i++) { // Itera sugli array
                    writer.write("    <phoneNumber>" + phoneNumbers[i] + "</phoneNumber>\n");
                }
    
                writer.write("  </contact>\n");
            }
            writer.write("</contacts>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
    }

