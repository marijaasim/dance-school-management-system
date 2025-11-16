/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.PrijavaNaKurs;
import model.StavkaPrijave;

/**
 *
 * @author Korisnik
 */
public class KreirajPrijavuNaKursSO extends ApstraktnaGenerickaOperacija {
    
    private PrijavaNaKurs prijava;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof PrijavaNaKurs)){
            throw new Exception("Sistem ne moze da kreira prijavu na kurs.");
        }
        
        prijava = (PrijavaNaKurs) param;
        if (prijava.getStavke()== null || prijava.getStavke().isEmpty()) {
            throw new Exception("Sistem ne moze da kreira prijavu bez stavki.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        prijava = (PrijavaNaKurs) param;
        
        java.sql.Date sqlDatum = new java.sql.Date(prijava.getDatum().getTime());
        prijava.setDatum(sqlDatum);
        
        try {
            
            int noviID = broker.add(prijava);
            prijava.setId(noviID);
            
            for (StavkaPrijave sp : prijava.getStavke()) {
                
                sp.setPrijava(prijava);
                broker.add(sp);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(UcitajPolaznikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public PrijavaNaKurs getPrijava() {
        return prijava;
    }
    
}
