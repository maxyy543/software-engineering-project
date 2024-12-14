package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

/**
 * @Class Validator
 * @brief Classe astratta che implementa il chain of responsibility.
 * 
 * Validator definisce i metodi per la validazione dei contatti da inserire in una rubrica.
 */
public abstract class Validator {
    private Validator next; ///< Riferimento del prossimo elemento della catena
    /**
     * 
     * @param[in] first Il primo validatore nella catena
     * @param[in] chain La sequenza di oggetti presenti nella catena
     * @return La testa della catena Validator
     */
    public static Validator link(Validator first, Validator... chain){
        Validator head = first;
        for(Validator nextChecker: chain){
            head.next = nextChecker;
            head = nextChecker;
        }
        return first;
    }
    /**
     * 
     * @param[in] contact Contatto da validare
     * @return true se la verifica è andata a buon fine
     */
    public abstract boolean check(Contact contact);

    /**
     * 
     * @param contact Contatto da validare dal prossimo Validatore
     * @return true se il prossimo Validatore restituisce true
     */
    protected boolean checkNext(Contact contact){
        if(next == null){
            return true;
        }
        return next.check(contact);
    }
}
