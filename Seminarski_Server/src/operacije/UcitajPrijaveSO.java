/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PrijavaNaKurs;

/**
 *
 * @author Korisnik
 */
public class UcitajPrijaveSO extends ApstraktnaGenerickaOperacija {
    
    private List<PrijavaNaKurs> prijave;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        String uslov = " JOIN instruktor ON prijava_na_kurs.instruktor=instruktor.id JOIN polaznik ON prijava_na_kurs.polaznik=polaznik.id";
        
        try {
            prijave = broker.getAll((PrijavaNaKurs)param, uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajPolaznikeSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<PrijavaNaKurs> getPrijave() {
        return prijave;
    }
    
    
    
}
