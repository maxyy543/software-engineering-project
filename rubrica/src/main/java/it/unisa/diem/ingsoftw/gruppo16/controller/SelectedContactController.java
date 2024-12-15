package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
/**
 * Questa classe permette si aggiungere, rimuovere e ottenere un contatto
 * selezionato durante l'interazione con la listView. E' una classe che implementa
 * il pattern Singleton. 
 * @Class SelectedContactController
 */
public class SelectedContactController {
    /**
     * istanza del Singleton selectedContact.
     */
    private static SelectedContactController instance;
    /**
     * contatto selezionato.
     */
    private static Contact selectedContact;

    /**
     * Costruttore privato che evita la possibilità di creare nuove istanze
     * di un contatto selezionato.
     */
    private SelectedContactController(){}
    /**
     * restituisce l'istanza di SelectedContactController.
     * @return l'istanza del singleton contatto selezionato.
     */
    public static SelectedContactController getInstance(){
        if(instance == null){
            instance = new SelectedContactController();
        }
        return instance;
    }
    /**
     * Restituisce l'ultimo contatto selezionato.
     * @return {@link Contact}.
     */
    public Contact getSelectedContact() {
        return selectedContact;
    }
    /**
     * Imposta l'ultimo contatto selezionato.
     * @param[in] selectedContact
     */
    public void setSelectedContact(Contact selectedContact) {
        SelectedContactController.selectedContact = selectedContact;
    }
    /**
     * Imposta il selectedContact ad un valore null.
     */
    public void resetSelectedContact(){
        SelectedContactController.selectedContact = null;
    }
}
