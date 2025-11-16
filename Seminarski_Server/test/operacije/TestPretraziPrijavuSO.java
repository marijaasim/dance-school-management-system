/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.Date;
import java.util.List;
import model.Instruktor;
import model.Polaznik;
import model.PrijavaNaKurs;
import model.kriterijum.KriterijumPretrage;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Korisnik
 */

public class TestPretraziPrijavuSO {
    
    private PretraziPrijavuSO so;
    private PrijavaNaKurs prijava;
    private KriterijumPretrage kp;

    @Before
    public void setUp() throws Exception {
        so = new PretraziPrijavuSO();
        prijava = new PrijavaNaKurs();
        kp = new KriterijumPretrage();
    }

    @After
    public void tearDown() {
        so = null;
        prijava = null;
        kp = null;
    }

    @Test
    public void testExecuteOperationWithInstruktor() {
        try {
            Instruktor i = new Instruktor();
            i.setId(1);
            kp.setI(i);
            so.izvrsiOperaciju(prijava, kp);

            List<PrijavaNaKurs> prijave = so.getPrijave();
            assertNotNull(prijave);

        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testExecuteOperationWithPolaznik() {
        try {
            Polaznik p = new Polaznik();
            p.setId(1);
            kp.setP(p);
            so.izvrsiOperaciju(prijava, kp);

            List<PrijavaNaKurs> prijave = so.getPrijave();
            assertNotNull(prijave);

        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testExecuteOperationWithDate() {
        try {
            kp.setD(new Date());
            so.izvrsiOperaciju(prijava, kp);
            List<PrijavaNaKurs> prijave = so.getPrijave();
            assertNotNull(prijave);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testExecuteOperationWithAllFields() {
        try {
            Instruktor i = new Instruktor();
            i.setId(1);
            Polaznik p = new Polaznik();
            p.setId(1);
            Date d = new Date();
            kp.setI(i);
            kp.setP(p);
            kp.setD(d);
            so.izvrsiOperaciju(prijava, kp);
            List<PrijavaNaKurs> prijave = so.getPrijave();
            assertNotNull(prijave);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
}
