package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

public class SelectedContactController {
    private static SelectedContactController instance;
    private Contact selectedContact;
    private SelectedContactController(){}
    public static SelectedContactController getInstance(){
        if(instance == null){
            instance = new SelectedContactController();
        }
        return instance;
    }
    public Contact getSelectedContact() {
        return selectedContact;
    }
    public void setSelectedContact(Contact selectedContact) {
        this.selectedContact = selectedContact;
    }
    public void resetSelectedContact(){
        this.selectedContact = null;
    }
}
