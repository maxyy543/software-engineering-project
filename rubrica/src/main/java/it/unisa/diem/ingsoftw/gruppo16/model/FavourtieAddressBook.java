package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;

/**
 * @class FavouriteAddressBook
 * @brief Definizione della classe FavouriteAddressBook che implementa AddressBook.
 * 
 * La classe FavouriteAddressBook gestisce i contatti utilizzando una TreeSet come collezione.
 * 
 */
public class FavourtieAddressBook implements AddressBook {

    private static TreeSet<Contact> listFavContacts;
    /**
     * @brief Costruttore che inizializza un FavAddressBook.
     * Inizializza una lista di contatti come una struttura TreeSet.
     */
    public FavourtieAddressBook(){
        listFavContacts = new TreeSet<>();
    }
    /**
     * Aggiunge un nuovo contatto nella lista favoriti.
     * @pre Il contatto deve essere presente nella rubrica principale.
     * @post Il contatto viene aggiunto nella lista favoriti.
     * @param[in] c Contatto da aggiungere nella lista favoriti.
     * @return true se questo contatto non è già presente nella lista favoriti.
     */
    @Override
    public boolean addNewContact(Contact c) {
        //
        return true;
    }
    /**
     * Rimuove un contatto dalla lista favoriti.
     * @pre Il contatto da eliminare deve essere presente nella lista favoriti.
     * @post Il contatto viene rimosso dalla lista favoriti.
     * @param[in] c Contatto da rimuovere dalla lista favoriti.
     * @return true se il contatto eliminato era presente nella rubrica principale e nella lista favoriti.
     */
    @Override
    public boolean removeContact(Contact c) {
        //
        return true;
    }
    /**
     * Modifica un contatto esistente nella lista favoriti.
     * @pre Il contatto da modificare deve essere presente nella lista favoriti.
     * @post Il contatto viene modificato nella lista favoriti.
     * @param[in] cOld Contatto da modificare nella lista favoriti.
     * @param[in] cNew Contatto da salvare nella lista favoriti.
     * @return true se la modifica del contatto nella lista favoriti è andata a buon fine.
     */
    @Override
    public boolean modifyContact(Contact cOld, Contact cNew) {
        //
        return true;
    }
    
}
