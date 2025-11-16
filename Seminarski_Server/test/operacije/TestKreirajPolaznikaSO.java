/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import model.Polaznik;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Korisnik
 */

public class TestKreirajPolaznikaSO {
    
    private KreirajPolaznikaSO so;
    private Polaznik p;
    
    @Before
    public void setUp() throws Exception {
        p = new Polaznik();
        so = new KreirajPolaznikaSO();
    }
    
    @After
    public void tearDown() {
        so = null;
        p = null;
    }
    
    @Test
    public void testExecuteOperationSuccessful() {
        try {
            
            p.setIme("Marija");
            p.setPrezime("Simovic");
            so.izvrsiOperaciju(p, null);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testValidateValidObject() {
        try {
            so.preduslovi(new Polaznik());
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
    
}
