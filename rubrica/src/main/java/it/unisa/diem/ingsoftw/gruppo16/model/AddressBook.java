package it.unisa.diem.ingsoftw.gruppo16.model;

import java.util.TreeSet;



/**
 * @interface AddressBook
 * @brief L'interfaccia AddressBook definisce i metodi per la gestione dei contatti di una rubrica.
 * 
 * 
 * @version 1.0
 */

public interface AddressBook {

    /**
     * Aggiunge un nuovo contatto nella rubrica.
     * @param[in] c Contatto da aggiungere nella rubrica.
     * @return true se questo contatto non è già presente nella rubrica.
     */
    public boolean addNewContact(Contact c);
    /**
     * Rimuove un contatto dalla rubrica.
     * @param[in] c Contatto da rimuovere dalla rubrica.
     * @return true se il contatto eliminato era presente nella rubrica.
     */
    public boolean removeContact(Contact c);
    /**
     * Modifica un contatto esistente nella rubrica.
     * @param[in] c Contatto da modificare nella rubrica.
     * @return true se la modifica del contatto nella rubrica è andata a buon fine.
     */
    public boolean modifyContact(Contact cOld, Contact cNew);
    /**
     * @brief Restituisce la rubrica.
     * @return una lista di contatti.
     */
    public TreeSet<Contact> getTreeSet();
}
