package classes;

import java.io.Serializable;

public class Contact implements Comparable<Contact>, Serializable{

    private String surname;

    private String name;

    private String[] telephoneNumber;

    private String[] email;


    
    public Contact(String surname, String name) {
        this.surname = surname;
        this.name = name;
        telephoneNumber = new String[3];
        email = new String[3];
    }



    public String getSurname() {
        return surname;
    }



    public String getName() {
        return name;
    }



    public String[] getTelephoneNumber() {
        return telephoneNumber;
    }



    public String[] getEmail() {
        return email;
    }



    public void setSurname(String surname) {
        this.surname = surname;
    }



    public void setName(String name) {
        this.name = name;
    }



    public void setTelephoneNumber(String[] telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }



    public void setEmail(String[] email) {
        this.email = email;
    }



    @Override
    public int compareTo(Contact o) {
        return this.surname.compareTo(o.surname);
    }
    
}
