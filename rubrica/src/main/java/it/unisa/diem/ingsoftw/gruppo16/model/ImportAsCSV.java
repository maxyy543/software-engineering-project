package it.unisa.diem.ingsoftw.gruppo16.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeSet;

public class ImportAsCSV implements ImportFileStrategy {
    /**
     * @brief metodo per importare dei contatti nella rubrica da un file CSV.
     * 
     * Metodo che implementa l'importFile di {@link ExportFileStrategy}.
     * Ogni linea di testo del file CSV deve contenere i dati necessari per inserire un nuovo contatto nella rubrica.
     * Ogni linea corrisponde a un nuovo contatto. Ogni contatto creato viene inserito in una {@link TreeSet}.
     * Ogni linea ha diversi campi che rappresentano, rispettivamente, Cognome, Nome, Numeri di Telefono (max 3), Email (max 3), Preferito
     * 
     * @param[inout] filename path del file da cui importare la rubrica.
     *  
     * @return una treeset contenente contatti da validare con il {@link Validator}.
     */
    @Override
    public TreeSet<Contact> importFile(File file) {
        TreeSet<Contact> treeContacts = new TreeSet<>();
        try(Scanner i = new Scanner(new BufferedReader(new FileReader(file)))){
            while(i.hasNextLine()){
                String line = i.nextLine().trim();
                String[] textFields = line.split(",");
                String surname = textFields[0].trim();
                String name = textFields[1].trim();
                String telNum1 = (textFields.length > 2) ? textFields[2].trim() : "";
                String telNum2 = (textFields.length > 3) ? textFields[3].trim() : "";
                String telNum3 = (textFields.length > 4) ? textFields[4].trim() : "";
                String email1 = (textFields.length > 5) ? textFields[5].trim() : "";
                String email2 = (textFields.length > 6) ? textFields[6].trim() : "";
                String email3 = (textFields.length > 7) ? textFields[7].trim() : "";
                String[] telNum = {telNum1, telNum2, telNum3};
                String[] emails = {email1, email2, email3};
                Contact c = new Contact(surname, name);
                c.setTelephoneNumber(telNum);
                c.setEmail(emails);
                treeContacts.add(c);
            }
            return treeContacts;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(RuntimeException e){   
            e.printStackTrace();
        }
        return treeContacts;
    }
}
