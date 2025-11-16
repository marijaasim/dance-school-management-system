/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Korisnik
 */

public class PrijavaNaKurs implements ApstraktniDomenskiObjekat {
    
    private int id;
    private Date datum;
    private StatusPrijave status;
    private double ukupanIznos;
    private Instruktor instruktor;
    private Polaznik polaznik;
    private List<StavkaPrijave> stavke = new ArrayList<>();

    public PrijavaNaKurs() {
    }

    public PrijavaNaKurs(int id, Date datum, StatusPrijave status, double ukupanIznos,
                         Instruktor instruktor, Polaznik polaznik, List<StavkaPrijave> stavke) {
        this.id = id;
        this.datum = datum;
        this.status = status;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
        this.stavke = stavke;
    }
    
    public PrijavaNaKurs(int id, Date datum, StatusPrijave status, double ukupanIznos, Instruktor instruktor, Polaznik polaznik) {
        this.id = id;
        this.datum = datum;
        this.status = status;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
    }

    public List<StavkaPrijave> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPrijave> stavke) {
        this.stavke = stavke;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public StatusPrijave getStatus() {
        return status;
    }

    public void setStatus(StatusPrijave status) {
        this.status = status;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    @Override
    public String toString() {
        return "PrijavaNaKurs{" + "datum=" + datum + ", status=" + status + ", ukupanIznos=" + ukupanIznos + ", instruktor=" + instruktor + ", polaznik=" + polaznik + '}';
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
        final PrijavaNaKurs other = (PrijavaNaKurs) obj;
        return this.id == other.id;
    }

    @Override
    public String vratiNazivTabele() {
        return "prijava_na_kurs";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            
            //Instruktor
            int idIns = rs.getInt("instruktor.id");
            String imeIns = rs.getString("instruktor.ime");
            String prezimeIns = rs.getString("instruktor.prezime");
            String emailIns = rs.getString("instruktor.email");
            String sifraIns = rs.getString("instruktor.sifra");
            
            Instruktor i = new Instruktor(idIns, imeIns, prezimeIns, emailIns, sifraIns);
            
            //Polaznik
            int idPol = rs.getInt("polaznik.id");
            String imePol = rs.getString("polaznik.ime");
            String prezimePol = rs.getString("polaznik.prezime");
            String emailPol = rs.getString("polaznik.email");
            
            Polaznik p = new Polaznik(idPol, imePol, prezimePol, emailPol, null);
            
            //PrijavaNaKurs
            int idPnk = rs.getInt("prijava_na_kurs.id");
            java.sql.Date datumSQL = rs.getDate("prijava_na_kurs.datum");
            String statusPnk = rs.getString("prijava_na_kurs.status");
            double ukupanIznosPnk = rs.getDouble("prijava_na_kurs.ukupanIznos");
            StatusPrijave sp = StatusPrijave.valueOf(statusPnk);
            
            Date datumPnk = null;
            if (datumSQL != null) {
                datumPnk = new Date(datumSQL.getTime());
            }
            
            PrijavaNaKurs prijava = new PrijavaNaKurs(idPnk, datumPnk, sp, ukupanIznosPnk, i, p, null);
            
            //Ucitavanje stavki
            String upitStavke = "SELECT * FROM stavka_prijave JOIN kurs_plesa ON stavka_prijave.kurs=kurs_plesa.id WHERE prijava=" + idPnk;
            List<StavkaPrijave> sveStavke = new ArrayList<>();

            try (Statement st = rs.getStatement().getConnection().createStatement();
                 ResultSet rsStavke = st.executeQuery(upitStavke)) {

                while (rsStavke.next()) {
                    
                    int idStavka = rsStavke.getInt("stavka_prijave.id");
                    double iznos = rsStavke.getDouble("stavka_prijave.iznos");

                    int kursId = rsStavke.getInt("kurs_plesa.id");
                    String naziv = rsStavke.getString("kurs_plesa.naziv");
                    int trajanje = rsStavke.getInt("kurs_plesa.trajanje");
                    double cena = rsStavke.getDouble("kurs_plesa.cena");
                    String vrsta = rsStavke.getString("kurs_plesa.vrsta");

                    KursPlesa kurs = new KursPlesa(kursId, naziv, trajanje, cena, Ples.valueOf(vrsta));
                    StavkaPrijave stavka = new StavkaPrijave(idStavka, iznos, prijava, kurs);

                    sveStavke.add(stavka);
                    
                }
            }

            prijava.setStavke(sveStavke);
            
            lista.add(prijava);
            
        }
        
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum,status,ukupanIznos,instruktor,polaznik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datum + "','" + status + "'," + ukupanIznos + "," + instruktor.getId() + "," + polaznik.getId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prijava_na_kurs.id=" + id;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='" + datum + "', status='" + status + "', ukupanIznos=" + ukupanIznos +
                ", instruktor=" + instruktor.getId() + ", polaznik=" + polaznik.getId();
    }
    
    
    
}
