/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KursPlesa;

/**
 *
 * @author Korisnik
 */
public class UcitajKursevePlesaSO extends ApstraktnaGenerickaOperacija {

    private List<KursPlesa> kursevi;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
         try {
            kursevi = broker.getAll((KursPlesa)param, null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajPolaznikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<KursPlesa> getKursevi() {
        return kursevi;
    }
    
    
    
}
