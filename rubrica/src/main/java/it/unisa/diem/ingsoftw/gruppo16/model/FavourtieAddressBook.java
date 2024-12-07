package classes;
import java.util.TreeSet;

public class FavourtieAddressBook implements AddressBook {

    private TreeSet<Contact> listFavContacts;

    @Override
    public boolean addNewContact(Contact c) {
        return listFavContacts.add(c);
    }

    @Override
    public boolean removeContact(Contact c) {
        return listFavContacts.add(c);
    }

    @Override
    public boolean modifyContact(Contact c) {
        if(listFavContacts.contains(c)){
            Contact modifiedContact = c;
            this.removeContact(c);
            this.addNewContact(modifiedContact);
            return true;
        }
        return false;
    }

    
}
