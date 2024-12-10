package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

public class SelectedContactController {
    private static SelectedContactController instance;
    private static Contact selectedContact;
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
        SelectedContactController.selectedContact = selectedContact;
    }
    public void resetSelectedContact(){
        SelectedContactController.selectedContact = null;
    }
}
