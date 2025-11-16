/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import model.Instruktor;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Korisnik
 */

public class TestUcitajInstruktoreSO {
    
    private UcitajInstruktoreSO so;
    private Instruktor i;

    @Before
    public void setUp() throws Exception {
        i = new Instruktor();
        so = new UcitajInstruktoreSO();
    }

    @After
    public void tearDown() {
        so = null;
        i = null;
    }

    @Test
    public void testExecuteOperationSuccessful() {
        try {
            so.izvrsiOperaciju(i, null);
            List<Instruktor> instruktori = so.getInstruktori();
            if (instruktori == null) {
                fail("Instruktori nisu učitani");
            }
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
}
