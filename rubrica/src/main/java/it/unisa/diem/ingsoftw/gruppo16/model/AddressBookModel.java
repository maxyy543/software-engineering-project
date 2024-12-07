package classes;
import java.util.TreeSet;

public class AddressBookModel implements AddressBook {

    private TreeSet<Contact> listContacts;

    public AddressBookModel(){
        listContacts = new TreeSet<>();
    }

    @Override
    public boolean addNewContact(Contact c) {
        return listContacts.add(c);
    }

    @Override
    public boolean removeContact(Contact c) {
        return listContacts.remove(c);
    }

    @Override
    public boolean modifyContact(Contact c) {
        if(listContacts.contains(c)){
            Contact modifiedContact = c;
            this.removeContact(c);
            this.addNewContact(modifiedContact);
            return true;
        }
        return false;
    }
}
