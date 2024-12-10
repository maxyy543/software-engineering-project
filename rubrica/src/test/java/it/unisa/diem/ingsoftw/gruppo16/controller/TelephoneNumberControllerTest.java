/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import junit.framework.TestCase;

/**
 *
 * @author marco
 */
public class TelephoneNumberControllerTest extends TestCase {

    public TelephoneNumberControllerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of check method, of class TelephoneNumberController.
     */
    public void testCheck() {
        System.out.println("check");

        TelephoneNumberController instance = new TelephoneNumberController();

        // Test caso 1: Contact nullo
        Contact contact = null;
        boolean result = instance.check(contact);
        assertFalse("Expected false when contact is null", result);

        // Test caso 2: Array di numeri di telefono nullo
        contact = new Contact("Rossi", "Mario");
        contact.setTelephoneNumber(null);
        result = instance.check(contact);
        assertFalse("Expected false when telephoneNumber array is null", result);

        // Test caso 3: Array di numeri di telefono vuoto
        contact.setTelephoneNumber(new String[]{null, null, null});
        result = instance.check(contact);
        assertFalse("Expected false when all telephone numbers are null", result);

        // Test caso 4: Numero di telefono valido
        contact.setTelephoneNumber(new String[]{"+39 081 1234567", null, null});
        result = instance.check(contact);
        assertTrue("Expected true when at least one telephone number is valid", result);

        // Test caso 5: Numero di telefono non valido
        contact.setTelephoneNumber(new String[]{"invalid-number", null, null});
        result = instance.check(contact);
        assertFalse("Expected false when all telephone numbers are invalid", result);

        // Test caso 6: Mix di numeri validi e non validi
        contact.setTelephoneNumber(new String[]{"invalid-number", "+1 (800) 555-1234", null});
        result = instance.check(contact);
        assertTrue("Expected true when there is at least one valid telephone number", result);

        // Test caso 7: Numeri di telefono validi multipli
        contact.setTelephoneNumber(new String[]{"+39 081 1234567", "+1 800-555-1234", null});
        result = instance.check(contact);
        assertTrue("Expected true when all provided numbers are valid", result);
    }
}