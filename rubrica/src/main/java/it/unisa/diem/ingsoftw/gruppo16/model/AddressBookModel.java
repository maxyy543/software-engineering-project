package it.unisa.diem.ingsoftw.gruppo16.model;
import java.io.Serializable;
import java.util.TreeSet;

/**
 * @class AddressBookModel
 * @brief Definizione della classe AddressBookModel che implementa AddressBook.
 * 
 * La classe AddressBookModel gestisce i contatti utilizzando una TreeSet come collezione.
 * @version 1.0
 */
public class AddressBookModel implements AddressBook, Cloneable, Serializable{
    
    private static TreeSet<Contact> listContacts; ///< lista di contatti gestita con una TreeSet.
    private static AddressBookModel addrBookInstance;

    /**
     * @brief Costruttore che inizializza un AddressBookModel.
     * Inizializza una lista di contatti come una struttura TreeSet.
     */
    private AddressBookModel(){
        listContacts = new TreeSet<>();
    }

    public static AddressBookModel getInstance(){
        if(addrBookInstance == null){
            addrBookInstance = new AddressBookModel();
        }
        return addrBookInstance;
    }
    public TreeSet<Contact> getTreeSet(){
        return listContacts;
    }
    /**
     * 
     * @param c Contatto da trovare
     * @return restituisce true se il contatto cercato è presente nella TreeSet.
     */
    public boolean contains(Contact c){
        return listContacts.contains(c);
    }

    public boolean isEmpty(){
        return listContacts.isEmpty();
    }

    public void clearAddressBook(){
        listContacts.clear();
    }
    /**
     * Aggiunge un nuovo contatto nella rubrica.
     * @pre Il contatto deve possedere i campi necessari(nome e cognome) per poter essere inserito nella rubrica.
     * @post Il contatto viene aggiunto nella rubrica.
     * @param[in] c Contatto da aggiungere nella rubrica.
     * @return true se questo contatto non è già presente nella rubrica.
     */
    @Override
    public boolean addNewContact(Contact c) {
        return listContacts.add(c);
    }
    /**
     * Rimuove un contatto dalla rubrica.
     * @pre Il contatto da eliminare deve esistere nella rubrica.
     * @post Il contatto viene rimosso dalla rubrica e da tutte le sottorubriche.
     * @param[in] c Contatto da rimuovere dalla rubrica.
     * @return true se il contatto eliminato era presente nella rubrica.
     */
    @Override
    public boolean removeContact(Contact c) {
        return listContacts.remove(c);
    }
    /**
     * Modifica un contatto esistente nella rubrica.
     * @pre Il contatto da modificare deve esistere nella rubrica.
     * @post Il contatto viene modificato nella rubrica e in tutte le sottorubriche.
     * @param[in] cOld Contatto da modificare nella rubrica.
     * @param[in] cNew Contatto da salvare nella rubrica
     * @return true se la modifica del contatto nella rubrica è andata a buon fine.
     */
    @Override
    public boolean modifyContact(Contact cOld, Contact cNew) {
        if(listContacts.contains(cOld)){
            listContacts.remove(cOld);
            listContacts.add(cNew);
            return true;
        }
        return false;
    }
}
