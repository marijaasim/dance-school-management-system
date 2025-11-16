/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */

public class StavkaPrijave implements ApstraktniDomenskiObjekat {
    
    private int rb;
    private double iznos;
    private PrijavaNaKurs prijava;
    private KursPlesa kurs;

    public StavkaPrijave() {
    }

    public StavkaPrijave(int rb, double iznos, PrijavaNaKurs prijava, KursPlesa kurs) {
        this.rb = rb;
        this.iznos = iznos;
        this.prijava = prijava;
        this.kurs = kurs;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public PrijavaNaKurs getPrijava() {
        return prijava;
    }

    public void setPrijava(PrijavaNaKurs prijava) {
        this.prijava = prijava;
    }

    public KursPlesa getKurs() {
        return kurs;
    }

    public void setKurs(KursPlesa kurs) {
        this.kurs = kurs;
    }

    @Override
    public String toString() {
        return "StavkaPrijave{" + "prijava=" + prijava + ", kurs=" + kurs + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaPrijave other = (StavkaPrijave) obj;
        return Objects.equals(this.kurs, other.kurs);
    }


    @Override
    public String vratiNazivTabele() {
        return "stavka_prijave";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while (rs.next()) {
            
            int idS = rs.getInt("stavka_prijave.id");
            double iznosS = rs.getDouble("stavka_prijave.iznos");
            int idPrijava = rs.getInt("prijava_na_kurs.id");
            int idKurs = rs.getInt("kurs_plesa.id");
            
            PrijavaNaKurs pnk = new PrijavaNaKurs();
            pnk.setId(idPrijava);
            
            KursPlesa kp = new KursPlesa();
            kp.setId(idKurs);
            
            StavkaPrijave sp = new StavkaPrijave(idS, iznosS, pnk, kp);
            lista.add(sp);
            
        }
        
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "prijava,iznos,kurs";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return prijava.getId() + ", " + iznos + ", " + kurs.getId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavka_prijave.id=" + rb + " AND stavka_prijave.prijava=" + prijava.getId();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
