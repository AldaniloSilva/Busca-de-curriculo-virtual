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
public class AnalisaIdadeTest {
    
    public AnalisaIdadeTest() {
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
     * Test of AnalisaLinha method, of class AnalisaIdade.
     */
    @Test
    public void testAnalisaLinha() {
        System.out.println("AnalisaLinha");
        String linha = "Fones: (11) 99215-1329 cel. / 4109-3540 res. 49 anos";
        boolean expResult = true;
        boolean result = AnalisaIdade.AnalisaLinha(linha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of CapturaAno method, of class AnalisaIdade.
     */
    @Test
    public void testCapturaAno() {
        System.out.println("CapturaAno");
        String linha = "99";
        boolean expResult = false;
        boolean result = AnalisaIdade.CapturaAno(linha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of CalculaAno method, of class AnalisaIdade.
     */
    @Test
    public void testCalculaAno() {
        System.out.println("CalculaAno");
        String linha = "49";
        boolean expResult = true;
        boolean result = AnalisaIdade.CalculaAno(linha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
