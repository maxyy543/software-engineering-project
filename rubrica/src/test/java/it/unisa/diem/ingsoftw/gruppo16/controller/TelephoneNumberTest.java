package it.unisa.diem.ingsoftw.gruppo16.controller;

import org.junit.jupiter.api.Test;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class TelephoneNumberTest {
    private TelephoneNumberController telNum;

    @BeforeEach
    public void init(){
        telNum = new TelephoneNumberController();
    }
    @Test 
    public void contactIsNullTest(){
        assertFalse(telNum.check(null), "Contact is null!");
    }
    public void contactWithCorrectTelNum(){
        Contact contactWithCorrectTel = new Contact("Test", "Test");
        String[] tel1 = {"1234567890","",""};
        contactWithCorrectTel.setTelephoneNumber(tel1);
        assertTrue(telNum.check(contactWithCorrectTel),"Correct telephone numbert");
    }
    public void contactWithIncorrectTelNum(){
        Contact contactWithInorrectTel = new Contact("Test", "Test");
        String[] tel1 = {"123456789","",""};
        contactWithInorrectTel.setTelephoneNumber(tel1);
        assertFalse(telNum.check(contactWithInorrectTel),"Inorrect telephone numbert");
        String[] tel2 = {"1234567890","1234","1234567890"};
        contactWithInorrectTel.setTelephoneNumber(tel2);
        assertFalse(telNum.check(contactWithInorrectTel),"Inorrect telephone numbert");

    }

}
