package it.unisa.diem.ingsoftw.gruppo16.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeSet;

public class ImportAsCSV implements ImportFileStrategy {
    AddressBookModel addrBook = AddressBookModel.getInstance();
    @Override
    public TreeSet<Contact> importFile(File file) {
        String filename = file.getAbsolutePath();
        TreeSet<Contact> treeContacts = new TreeSet<>();
        try(Scanner i = new Scanner(
                new BufferedReader(
                        new FileReader(filename)))){
            i.useLocale(Locale.ITALY);
            i.useDelimiter("\n*");
            String surname;
            String name;
            String[] telNum;
            String[] email;
            while(i.hasNext()){
                surname = i.next();
                name = i.next();
                String telNumStr = i.next();
                String emailStr = i.next();
                telNum = telNumStr.split(",");
                email = emailStr.split(",");
                Contact c = new Contact(surname, name);
                c.setTelephoneNumber(telNum);
                c.setEmail(email);
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
