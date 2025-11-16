/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import model.Instruktor;
import model.Polaznik;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Korisnik
 */

public class TestObrisiPolaznikaSO {
    
    private ObrisiPolaznikaSO so;
    private Polaznik p;
    
    @Before
    public void setUp() throws Exception {
        so = new ObrisiPolaznikaSO();
        p = new Polaznik();
        p.setId(0);
    }
    
    @After
    public void tearDown() {
        so = null;
        p = null;
    }
    
    @Test
    public void testExecuteOperationSuccessful() {
        try {
            so.izvrsiOperaciju(p, null);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testValidateValidObject() {
        try {
            so.preduslovi(p);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test(expected = Exception.class)
    public void testValidateNullObject() throws Exception {
        so.preduslovi(null);
    }
    
    @Test(expected = Exception.class)
    public void testValidateInvalidObject() throws Exception {
        so.preduslovi(new Object());
    }
    
    @Test(expected = Exception.class)
    public void testValidateWithExistingPrijave() throws Exception {
        Polaznik pWithPrijave = new Polaznik();
        pWithPrijave.setId(2);

        so.preduslovi(pWithPrijave);
    }
    
}
