/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.ArrayList;
import java.util.Date;
import model.PrijavaNaKurs;
import model.StavkaPrijave;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Korisnik
 */


public class TestKreirajPrijavuNaKursSO {
    
    private KreirajPrijavuNaKursSO so;
    private PrijavaNaKurs prijava;
    
    @Before
    public void setUp() throws Exception {
        so = new KreirajPrijavuNaKursSO();
        prijava = new PrijavaNaKurs();
        prijava.setStavke(new ArrayList<>());
        prijava.setDatum(new Date());
    }
    
    @After
    public void tearDown() {
        so = null;
        prijava = null;
    }
    
    @Test
    public void testExecuteOperationSuccessful() {
        try {
            StavkaPrijave sp = new StavkaPrijave();
            prijava.getStavke().add(sp);
            
            so.izvrsiOperaciju(prijava, null);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testValidateValidObject() {
        try {
            StavkaPrijave sp = new StavkaPrijave();
            prijava.getStavke().add(sp);
            
            so.preduslovi(prijava);
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
    public void testValidateEmptyStavke() throws Exception {
        so.preduslovi(prijava);
    }
    
}
