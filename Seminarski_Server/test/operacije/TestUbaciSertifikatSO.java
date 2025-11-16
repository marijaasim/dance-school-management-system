/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import model.Ples;
import model.Sertifikat;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Korisnik
 */

public class TestUbaciSertifikatSO {
    
    private UbaciSertifikatSO so;
    private Sertifikat s;

    @Before
    public void setUp() throws Exception {
        s = new Sertifikat();
        so = new UbaciSertifikatSO();
    }

    @After
    public void tearDown() {
        so = null;
        s = null;
    }

    @Test
    public void testExecuteOperationSuccessful() {
        try {
            Ples ples = Ples.balet;
            s.setPles(ples);
            s.setOrganizacija("Dance Academy");
            so.izvrsiOperaciju(s, null);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testValidateValidObject() {
        try {
            so.preduslovi(new Sertifikat());
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
