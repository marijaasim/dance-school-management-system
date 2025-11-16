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
public class PromeniPrijavuSO extends ApstraktnaGenerickaOperacija {
    
    private PrijavaNaKurs pnk;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof PrijavaNaKurs)){
            throw new Exception("Sistem ne moze da kreira prijavu na kurs.");
        }
        
        pnk = (PrijavaNaKurs) param;
        if (pnk.getStavke()== null || pnk.getStavke().isEmpty()) {
            throw new Exception("Prijava mora imati barem jednu stavku.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        pnk = (PrijavaNaKurs) param;
        
        java.sql.Date sqlDatum = new java.sql.Date(pnk.getDatum().getTime());
        pnk.setDatum(sqlDatum);
        
        try {
            
            broker.edit(pnk);
            
            String uslov = " WHERE prijava=" + pnk.getId();
            broker.delete(new StavkaPrijave(), uslov);
            
            for (StavkaPrijave sp : pnk.getStavke()) {
                sp.setPrijava(pnk);
                broker.add(sp);
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(PromeniPrijavuSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public PrijavaNaKurs getPnk() {
        return pnk;
    }
     
    
}
