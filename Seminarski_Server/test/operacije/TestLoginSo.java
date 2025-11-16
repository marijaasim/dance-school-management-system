/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package operacije;


import static org.junit.Assert.fail;
import model.Instruktor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Korisnik
 */

public class TestLoginSo {
    
    private LoginSO so;
    private Instruktor i;
    
    @Before
    public void setUp() throws Exception {
        i = new Instruktor();
        so = new LoginSO();
    }
    
    @After
    public void tearDown() {
        so = null;
        i = null;
    }
    
    @Test
    public void testExecuteOperationSuccessful() {
        try {
            
            i.setEmail("marija@gmail.com");
            i.setSifra("marija");
            so.izvrsiOperaciju(i, null);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testExecuteOperationUnsuccessful() throws Exception {
        
        i.setEmail("marija@gmail.com");
        i.setSifra("marija1");
        so.izvrsiOperaciju(i, null);
        
    }
    
    @Test
    public void testValidateValidObject() {
        try {
            
            so.preduslovi(new Instruktor());
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testValidateInvalidObject() throws Exception {
        so.preduslovi(new Object());
    }
    
}





