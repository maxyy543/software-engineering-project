package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.util.TreeSet;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

/**
 * Classe controller per la gestione della lista dei contatti preferiti.
 * @Class FavouriteListController.
 */
public class FavouriteListController {
    AddressBookModel addrBook; ///< rubrica su cui effettuare una iterazione

    /**
     * @brief Costruttore della classe.
     * Viene assegnato ad addrBook l'istanza di AddressBookModel.
     */
    public FavouriteListController(){
        addrBook = AddressBookModel.getInstance();
    }
    /**
     * Visita l'intera rubrica per selezionare i contatti preferiti
     * e aggiungeri ad una nuova treeSet.
     * @return treeSet con i contatti preferiti.
     */
    public TreeSet<Contact> getTreeWithFavContacts(){
        TreeSet<Contact> tree = new TreeSet<>();
        for(Contact c: addrBook.getTreeSet()){
            if(c.getIsFavourite()){
                tree.add(c);
            }
        }
        return tree;
    }
}
