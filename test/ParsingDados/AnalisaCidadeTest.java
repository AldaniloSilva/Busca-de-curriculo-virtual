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
public class AnalisaCidadeTest {

    public AnalisaCidadeTest() {
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


    @Test
    public void testProcuraUf() {
        System.out.println("ProcuraUf");
        //Nesse caso tem que ser false porque em linha não tem nenhuma UF de estado nenhum
        String linha = "São Paulo";
        boolean expResult = false;
        boolean result = AnalisaCidade.ProcuraUf(linha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of ValidaCidadeBanco method, of class AnalisaCidade.
     */
    @Test
    public void testValidaCidadeBanco() throws Exception {
        System.out.println("ValidaCidadeBanco");
        //Verifica se em linha tem um UF valido e retorna o código
        //Caso não encontre 
        String linha = "XA";
        int expResult = 6000;
        int result = AnalisaCidade.ValidaCidadeBanco(linha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
