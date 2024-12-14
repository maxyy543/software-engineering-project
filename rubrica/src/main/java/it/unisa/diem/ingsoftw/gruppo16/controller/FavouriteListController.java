package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.util.TreeSet;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

public class FavouriteListController {
    AddressBookModel addrBook;

    public FavouriteListController(){
        addrBook = AddressBookModel.getInstance();
    }

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
