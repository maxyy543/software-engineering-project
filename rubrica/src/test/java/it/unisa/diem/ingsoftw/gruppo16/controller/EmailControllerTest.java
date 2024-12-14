package it.unisa.diem.ingsoftw.gruppo16.controller;
import org.junit.jupiter.api.Test;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class EmailControllerTest {
    
    private EmailController emailController;
    
    @BeforeEach
    public void init(){
        emailController = new EmailController();
    }
    @Test 
    public void contactIsNullTest(){
        assertFalse(emailController.check(null), "Contact is not null!");
    }
    @Test
    public void contactWithCorrectEmailTest(){
        Contact contactWithCorrectEmail = new Contact("ContactWithCorrectEmail", "ContactWithCorrectEmail");
        String[] emails = {"test@gmail.com","",""};
        contactWithCorrectEmail.setEmail(emails);
        assertTrue(emailController.check(contactWithCorrectEmail), "Incorrect email");
    }
    @Test
    public void contactWithIncorrectEmailTest(){
        Contact contactWithIncorrectEmail = new Contact("ContactWithCorrectEmail", "ContactWithCorrectEmail");
        String[] emails = {"test","",""};
        contactWithIncorrectEmail.setEmail(emails);
        assertFalse(emailController.check(contactWithIncorrectEmail), "Correct email");

        String[] emails2 = {"test@gmail.com","test@",""};
        contactWithIncorrectEmail.setEmail(emails2);
        assertFalse(emailController.check(contactWithIncorrectEmail), "Correct email");

        String[] emails3 = {"test","test@gmail.com","test"};
        contactWithIncorrectEmail.setEmail(emails3);
        assertFalse(emailController.check(contactWithIncorrectEmail), "Correct email");

        String[] emails4 = {"test@","test@gmail.com","test@"};
        contactWithIncorrectEmail.setEmail(emails4);
        assertFalse(emailController.check(contactWithIncorrectEmail), "Correct email");

        String[] emails5 = {"no_domain@", "another@domain.com", ""};
        contactWithIncorrectEmail.setEmail(emails5);
        assertFalse(emailController.check(contactWithIncorrectEmail), "Correct email");

        String[] emails6 = {"missing@domain", "test@domain.com", ""};
        contactWithIncorrectEmail.setEmail(emails6);
        assertFalse(emailController.check(contactWithIncorrectEmail), "Correct email");

    }


}
