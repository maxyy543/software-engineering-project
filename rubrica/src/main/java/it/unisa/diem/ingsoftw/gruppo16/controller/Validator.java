package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

/**
 * @Class Validator
 * @brief Classe astratta che implementa il pattern chain of responsibility.
 * 
 * Validator definisce i metodi per la validazione dei contatti da inserire in una rubrica.
 */
public abstract class Validator {
    private Validator next; ///< Riferimento del prossimo elemento della catena
    /**
     * Metodo che permette di collegare una sequenza di validator. Ogni Validator è
     * responsabile di un specifico controllo.
     * 
     * @pre devono esistere dei validator per poter creare la catena.
     * @post viene creata una catena di validator.
     * 
     * @param[in] first Il primo validatore nella catena.
     * @param[in] chain La sequenza di oggetti presenti nella catena.
     * @return La testa della catena Validator.
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
     * Metodo astratto da implementare nelle sottoclassi Validator.
     * 
     * @pre esiste un validator che lo implementi.
     * @post risultato della validazione.
     * @param[in] contact Contatto da validare.
     * @return true se la verifica è andata a buon fine, falso altrimenti.
     */
    public abstract boolean check(Contact contact);

    /**
     * Controlla se esiste un prossimo validatore. Se non esistono altri validatori,
     * allora il metodo restituisce true.
     * @param contact Contatto da validare dal prossimo Validatore.
     * @return true se il prossimo Validatore restituisce true oppure se non ci sono altri validatori.
     */
    protected boolean checkNext(Contact contact){
        if(next == null){
            return true;
        }
        return next.check(contact);
    }
}
