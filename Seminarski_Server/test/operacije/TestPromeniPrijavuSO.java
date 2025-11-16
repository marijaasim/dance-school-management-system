/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.ArrayList;
import model.PrijavaNaKurs;
import model.StavkaPrijave;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Korisnik
 */
public class TestPromeniPrijavuSO {
    
    private PromeniPrijavuSO so;
    private PrijavaNaKurs pnk;

    @Before
    public void setUp() throws Exception {
        so = new PromeniPrijavuSO();
        pnk = new PrijavaNaKurs();
        pnk.setStavke(new ArrayList<>());
    }

    @After
    public void tearDown() {
        so = null;
        pnk = null;
    }

    @Test
    public void testExecuteOperationSuccessful() {
        try {
            StavkaPrijave sp = new StavkaPrijave();
            pnk.getStavke().add(sp);
            so.izvrsiOperaciju(pnk, null);
            assertNotNull(so.getPnk());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testValidateValidObject() {
        try {
            StavkaPrijave sp = new StavkaPrijave();
            pnk.getStavke().add(sp);
            so.preduslovi(pnk);
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
        so.preduslovi(new PrijavaNaKurs()); // stavke prazne
    }
    
}
