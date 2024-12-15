package it.unisa.diem.ingsoftw.gruppo16.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class ImportAsCSV implements ImportFileStrategy {
    private final int MAX_TELEPHONE_NUMBER = 3; ///< Numero massimo di numeri di telefono utilizzabili.
    private final int MAX_EMAIL_NUMBER = 3; ///< Numero massimo di email utilizzabili.
    /**
     * @brief metodo per importare dei contatti nella rubrica da un file CSV.
     * 
     * Metodo che implementa l'importFile di {@link ExportFileStrategy}.
     * Ogni linea di testo del file CSV deve contenere i dati necessari per inserire un nuovo contatto nella rubrica.
     * Ogni linea corrisponde a un nuovo contatto. Ogni contatto creato viene inserito in una {@link TreeSet}.
     * Ogni linea ha diversi campi che rappresentano, rispettivamente, Cognome, Nome, Numeri di Telefono (max 3), Email (max 3), Preferito
     * 
     * @pre il file da importare deve esistere.
     * @post viene generata una lista di contatti da validare con un {@link Validator}.
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
                String surname = (textFields.length > 0) ? textFields[0].trim(): "";
                String name = (textFields.length > 1) ? textFields[1].trim() : "";
                
                String[] tel = new String[MAX_TELEPHONE_NUMBER];
                String[] email = new String[MAX_EMAIL_NUMBER];

                Arrays.fill(tel, "");
                Arrays.fill(email, "");

                int telIndex=0;
                int emailIndex=0;
                for(int k=2; k< textFields.length; k++){
                    String str = textFields[k].trim();
                    if(str.contains("@") && emailIndex < MAX_EMAIL_NUMBER){
                        email[emailIndex++]=str;
                    }else if(telIndex<MAX_TELEPHONE_NUMBER){
                        tel[telIndex++] =str;
                    }
                    
                }
                
                Contact c = new Contact(surname, name);
                c.setTelephoneNumber(tel);
                c.setEmail(email);
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
