/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import model.Polaznik;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Korisnik
 */

public class TestPretraziPolaznikaSO {
    
    private PretraziPolaznikaSO so;
    private Polaznik p;

    @Before
    public void setUp() throws Exception {
        so = new PretraziPolaznikaSO();
        p = new Polaznik();
    }

    @After
    public void tearDown() {
        so = null;
        p = null;
    }

    @Test
    public void testExecuteOperationWithName() {
        try {
            p.setIme("Marija");
            p.setPrezime("Simovic");
            so.izvrsiOperaciju(p, null);

            List<Polaznik> polaznici = so.getPolaznici();
            assertNotNull(polaznici);

        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testExecuteOperationWithEmail() {
        try {
            p.setEmail("marija@gmail.com");
            so.izvrsiOperaciju(p, null);

            List<Polaznik> polaznici = so.getPolaznici();
            assertNotNull(polaznici);

        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testExecuteOperationWithAllFields() {
        try {
            p.setIme("Marija");
            p.setPrezime("Simovic");
            p.setEmail("marija@gmail.com");
            so.izvrsiOperaciju(p, null);

            List<Polaznik> polaznici = so.getPolaznici();
            assertNotNull(polaznici);

        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
}
