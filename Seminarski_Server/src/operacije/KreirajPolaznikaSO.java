/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Polaznik;

/**
 *
 * @author Korisnik
 */
public class KreirajPolaznikaSO extends ApstraktnaGenerickaOperacija {
    
    private Polaznik polaznik;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof Polaznik)){
            throw new Exception("Sistem ne moze da kreira polaznika.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        polaznik = (Polaznik) param;
        
        try {
            broker.add(polaznik);
        } catch (Exception ex) {
            Logger.getLogger(UcitajPolaznikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }
    
    
    
}
