package it.unisa.diem.ingsoftw.gruppo16.model;

import java.io.Serializable;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;



/**
 * @class Contact
 * @brief Definizione della classe Contact e suoi metodi.
 * 
 * @version 1.0
 */
public class Contact implements Comparable<Contact>, Serializable{

    private final int MAX_TELEPHONE_NUMBER = 3;
    private final int MAX_EMAIL_NUMBER = 3;
    /**
     * Cognome del Contact
     */
    private String surname;

    /**
     * Nome del Contact
     */
    private String name;

    /**
     * Numero di telefono del Contact
     */
    private String[] telephoneNumber;

    /**
     * Email del Contact
     */

    private String[] email;

    /**
     * Il contatto è tra i preferiti
     */
    private boolean isFavourite;

    /**
     * @brief Costruttore di Contact per inizializzare un nuovo ogetto Contact.
     * L'inizializzazione comporta anche la creazione di un array di stringhe per i numeri di telefono(massimo 3) e per le email(massimo 3).
     *  
     * 
     * @param[in] surname Cognome del Contact
     * @param[in] name Nome del Contact
     * 
     */
    public Contact(String surname, String name) {
        this.surname = surname;
        this.name = name;
        telephoneNumber = new String[MAX_TELEPHONE_NUMBER];
        email = new String[MAX_EMAIL_NUMBER];
        this.isFavourite = false;
    }

    /**
     * 
     * @return Cognome del Contact
     */
    public String getSurname() {
        return surname;
    }


    /**
     * 
     * @return Nome del Contact
     */
    public String getName() {
        return name;
    }


    /**
     * 
     * @return Numeri di telefono del Contact
     *      
     */
    public String[] getTelephoneNumber() {
        return telephoneNumber;
    }


    /**
     * 
     * @return Email del Contact.
     */
    public String[] getEmail() {
        return email;
    }

    /**
     * 
     * @return isFavourite
     */
    public boolean getIsFavourite(){
        return isFavourite;
    }


    /**
     * @pre Il cognome deve rispettare la lunghezza massima di caratteri consentita. 
     * @param[in] surname Setter cognome del contact
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }


    /**
     * @pre Il nome deve rispettare la lunghezza massima di caratteri consentita.
     * @param[in] name Setter nome del contact
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @pre I numeri di telefono devono essere corretti.
     * @param[in] telephoneNumber Setter numeri di telefono del contact
     */
    public void setTelephoneNumber(String[] telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


    /**
     * @pre Le email devono essere corrette.
     * @param[in] email Setter email del contact
     */
    public void setEmail(String[] email) {
        this.email = email;
    }
    /**
     * 
     * @param[in] isFavourite Setter isFavourite
     */
    public void setIsFavourite(boolean isFavourite){
        this.isFavourite = isFavourite;
    }

    public StringProperty getSurnameProperty(){
        return new SimpleStringProperty(surname);
    }
    public StringProperty getNameProperty(){
        return new SimpleStringProperty(name);
    }
    public ListProperty<String> getTelephoneNumberProperty(){
        return new SimpleListProperty<>(FXCollections.observableArrayList(telephoneNumber));
    }
    public ListProperty<String> getEmailProperty(){
        return new SimpleListProperty<>(FXCollections.observableArrayList(email));
    }

    /**
     * @brief Metodo per il confronto tra i contatti nella TreeSet.
     * Per tenere ordinata la TreeSet è necessario definire una relazione d'ordine. Nel nostro caso la relazione d'ordine è il Cognome.
     * Questo metodo stabilisce una relazione d'ordine.
     * @pre Il contatto da confrontare deve essere validato da un Validator.
     * @post Il contatto viene confrontato con altri contatti.
     * @param[in] o Contact
     * @return un numero intero negativo, zero o un numero intero positivo se il contatto ha un cognome più piccolo, uguale o più grande in ordine alfabetico rispetto ad un altro contatto
     */
    @Override
    public int compareTo(Contact o) {
        return this.surname.compareTo(o.surname);
    }
    @Override
    public boolean equals(Object o){
        if(o == null) 
            return false;
        if(!o.getClass().equals(this.getClass())) 
            return false;
        Contact c = (Contact) o;
        return this.surname.equals(c.getSurname()) && this.name.equals(c.getName());
    }
}
