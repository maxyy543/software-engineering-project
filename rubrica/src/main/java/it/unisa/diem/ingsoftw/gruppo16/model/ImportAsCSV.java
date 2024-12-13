package it.unisa.diem.ingsoftw.gruppo16.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class ImportAsCSV implements ImportFileStrategy {
    @Override
    public TreeSet<Contact> importFile(File file) {
        System.out.println("Sei arrivato qui");
        TreeSet<Contact> treeContacts = new TreeSet<>();
        try(Scanner i = new Scanner(new BufferedReader(new FileReader(file)))){
            while(i.hasNextLine()){
                String line = i.nextLine().trim();
                String[] textFields = line.split(",");
                String surname = textFields[0].trim();
                String name = textFields[1].trim();
                System.out.println(textFields[0] + textFields[1]);
                String telNum1 = textFields[2];
                String telNum2 = textFields[3];
                String telNum3 = textFields[4];
                System.out.println(telNum1 + telNum2 + telNum3);
                String email1 = textFields[5];
                String email2 = textFields[6];
                String email3 = textFields[7];
                System.out.println(email1 + email2 + email3);
                String[] telNum = {telNum1, telNum2, telNum3};
                String[] emails = {email1, email2, email3};
                Contact c = new Contact(surname, name);
                c.setTelephoneNumber(telNum);
                c.setEmail(emails);
                System.out.println(c.toString());
                treeContacts.add(c);
            }
            return treeContacts;
        }catch(FileNotFoundException e){
            //e.printStackTrace();
            System.out.println("Ecco qua l'errore");
        }catch(RuntimeException e){   
            //e.printStackTrace();
            System.out.println("Errore lanciato dal RuntimeException");
        }
        return null;
    }
}
