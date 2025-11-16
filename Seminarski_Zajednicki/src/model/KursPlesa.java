/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */

public class KursPlesa implements ApstraktniDomenskiObjekat {
    
    private int id;
    private String naziv;
    private int trajanje;
    private double cena;
    private Ples vrsta;

    public KursPlesa() {
    }

    public KursPlesa(int id, String naziv, int trajanje, double cena, Ples vrsta) {
        this.id = id;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.cena = cena;
        this.vrsta = vrsta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Ples getVrsta() {
        return vrsta;
    }

    public void setVrsta(Ples vrsta) {
        this.vrsta = vrsta;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final KursPlesa other = (KursPlesa) obj;
        return this.id == other.id;
    }

    @Override
    public String vratiNazivTabele() {
        return "kurs_plesa";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while (rs.next()) {
            
            int id = rs.getInt("kurs_plesa.id");
            String naziv = rs.getString("kurs_plesa.naziv");
            int trajanje = rs.getInt("kurs_plesa.trajanje");
            double cena = rs.getDouble("kurs_plesa.cena");
            String vrsta = rs.getString("kurs_plesa.vrsta");
            
            Ples ples = Ples.valueOf(vrsta);
            
            KursPlesa kp = new KursPlesa(id, naziv, trajanje, cena, ples);
            lista.add(kp);
            
        }
        
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,trajanje,cena,vrsta";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kurs_plesa.id=" + id;
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
