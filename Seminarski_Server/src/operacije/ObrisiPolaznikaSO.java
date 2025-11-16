/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Polaznik;
import model.PrijavaNaKurs;

/**
 *
 * @author Korisnik
 */
public class ObrisiPolaznikaSO extends ApstraktnaGenerickaOperacija {
    
    private Polaznik p;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof Polaznik)){
            throw new Exception("Sistem ne moze da obrise polaznika.");
        }
        
        p = (Polaznik) param;
        PrijavaNaKurs pnk = new PrijavaNaKurs();
        pnk.setPolaznik(p);

        List<PrijavaNaKurs> prijave = broker.getAll(pnk, " WHERE polaznik= " + p.getId());

        if (!prijave.isEmpty()) {
            throw new Exception("Sistem ne moze da obrise clana jer postoje njegove prijave.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        p = (Polaznik) param;
        
        try {
            broker.delete(p,null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajPolaznikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Polaznik getP() {
        return p;
    }

    
    
}
