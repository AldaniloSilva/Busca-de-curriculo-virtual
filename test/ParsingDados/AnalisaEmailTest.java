/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingDados;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alipio
 */
public class AnalisaEmailTest {
    
    public AnalisaEmailTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ParsingEmail method, of class AnalisaEmail.
     */
    @Test
    public void testParsingEmail() {
        System.out.println("ParsingEmail");
        String texto = "Meu email eh 0810002@ftt.cefsa.edu.br";
        String expResult = "0810002@ftt.cefsa.edu.br";
        String result = AnalisaEmail.ParsingEmail(texto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
