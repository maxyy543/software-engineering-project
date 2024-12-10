package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailControllerTest {

    public EmailControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Inizio test EmailController");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Fine test EmailController");
    }

    @Before
    public void setUp() {
        System.out.println("Set up per il test");
    }

    @After
    public void tearDown() {
        System.out.println("Pulizia post-test");
    }

    /**
     * Test of check method, of class EmailController.
     */
    @Test
    public void testCheck() {
        System.out.println("Eseguendo testCheck");
        EmailController instance = new EmailController();

        // Test caso 1: Contatto nullo
        Contact contact = null;
        boolean result = instance.check(contact);
        assertFalse("Expected false when contact is null", result);

        // Test caso 2: Array email nullo
        contact = new Contact("Rossi", "Mario");
        contact.setEmail(null);
        result = instance.check(contact);
        assertFalse("Expected false when email array is null", result);

        // Test caso 3: Array email vuoto
        contact.setEmail(new String[]{null, null, null});
        result = instance.check(contact);
        assertFalse("Expected false when all emails are null", result);

        // Test caso 4: Email valida presente
        contact.setEmail(new String[]{"example@example.com", null, null});
        result = instance.check(contact);
        assertTrue("Expected true when at least one email is valid", result);

        // Test caso 5: Tutte email non valide
        contact.setEmail(new String[]{"invalid-email", "another-invalid", null});
        result = instance.check(contact);
        assertFalse("Expected false when all emails are invalid", result);

        // Test caso 6: Mix di email valide e non valide
        contact.setEmail(new String[]{"invalid-email", "valid@example.com", null});
        result = instance.check(contact);
        assertTrue("Expected true when there is at least one valid email", result);

        // Test caso 7: Tutte email valide
        contact.setEmail(new String[]{"valid@example.com", "another.valid@domain.org", null});
        result = instance.check(contact);
        assertTrue("Expected true when all provided emails are valid", result);
    }
}
