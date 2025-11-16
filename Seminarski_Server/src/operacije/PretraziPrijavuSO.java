/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Instruktor;
import model.Polaznik;
import model.PrijavaNaKurs;
import model.kriterijum.KriterijumPretrage;

/**
 *
 * @author Korisnik
 */
public class PretraziPrijavuSO extends ApstraktnaGenerickaOperacija {
    
    private List<PrijavaNaKurs> prijave;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        
        KriterijumPretrage kp = (KriterijumPretrage) kljuc;
        Instruktor i = kp.getI();
        Polaznik p = kp.getP();
        Date d = kp.getD();
        java.sql.Date sqlDate = null;
        
        if (d != null) {
            sqlDate = new java.sql.Date(d.getTime());
        }
        
        String uslov = " JOIN instruktor ON prijava_na_kurs.instruktor=instruktor.id JOIN polaznik ON prijava_na_kurs.polaznik=polaznik.id";
        
        if (i != null && p == null && d == null) {
            uslov += " WHERE instruktor.id=" + i.getId();
        }
        
        if (i == null && p != null && d == null) {
            uslov += " WHERE polaznik.id=" + p.getId();
        }
        
        if (i == null && p == null && d != null) {
            uslov += " WHERE prijava_na_kurs.datum='" + sqlDate + "'";
        }
        
        if (i != null && p != null && d == null) {
            uslov += " WHERE instruktor.id=" + i.getId() + " AND polaznik.id=" + p.getId();
        }
        
        if (i != null && p == null && d != null) {
            uslov += " WHERE instruktor.id=" + i.getId() + " AND prijava_na_kurs.datum='" + sqlDate + "'";
        }
        
        if (i == null && p != null && d != null) {
            uslov += " WHERE polaznik.id=" + p.getId() + " AND prijava_na_kurs.datum='" + sqlDate + "'";
        }
        
        if (i != null && p != null && d != null) {
            uslov += " WHERE instruktor.id=" + i.getId() + " AND polaznik.id=" + p.getId() + " AND prijava_na_kurs.datum='" + sqlDate + "'";
        }

        
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
