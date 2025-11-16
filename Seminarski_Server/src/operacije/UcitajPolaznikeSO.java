/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Polaznik;

/**
 *
 * @author Korisnik
 */
public class UcitajPolaznikeSO extends ApstraktnaGenerickaOperacija {
    
    private List<Polaznik> polaznici;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        String uslov = " JOIN nivo_vestine ON polaznik.nivo=nivo_vestine.id";
        
        try {
            polaznici = broker.getAll((Polaznik)param, uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajPolaznikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<Polaznik> getPolaznici() {
        return polaznici;
    }
    
    
    
}
