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

public class Polaznik implements ApstraktniDomenskiObjekat {
    
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private NivoVestine nivo;

    public Polaznik() {
    }

    public Polaznik(int id, String ime, String prezime, String email, NivoVestine nivo) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.nivo = nivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NivoVestine getNivo() {
        return nivo;
    }

    public void setNivo(NivoVestine nivo) {
        this.nivo = nivo;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Polaznik other = (Polaznik) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "polaznik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while (rs.next()) {
            
            int id = rs.getInt("polaznik.id");
            String ime = rs.getString("polaznik.ime");
            String prezime = rs.getString("polaznik.prezime");
            String email = rs.getString("polaznik.email");
            
            int idNivo = rs.getInt("nivo_vestine.id");
            Nivo nivo = Nivo.valueOf(rs.getString("nivo_vestine.nivo"));
            Ples ples = Ples.valueOf(rs.getString("nivo_vestine.vrsta"));
            
            NivoVestine nv = new NivoVestine(idNivo, nivo, ples);
            
            Polaznik p = new Polaznik(id, ime, prezime, email, nv);
            lista.add(p);
            
        }
        
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,nivo";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + email + "'," + nivo.getId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "polaznik.id=" + id;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', nivo=" + nivo.getId();
    }
    
    
    
}
