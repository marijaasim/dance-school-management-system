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

public class NivoVestine implements ApstraktniDomenskiObjekat {
    
    private int id;
    private Nivo nivo;
    private Ples vrsta;

    public NivoVestine() {
    }

    public NivoVestine(int id, Nivo nivo, Ples vrsta) {
        this.id = id;
        this.nivo = nivo;
        this.vrsta = vrsta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nivo getNivo() {
        return nivo;
    }

    public void setNivo(Nivo nivo) {
        this.nivo = nivo;
    }

    public Ples getVrsta() {
        return vrsta;
    }

    public void setVrsta(Ples vrsta) {
        this.vrsta = vrsta;
    }

    @Override
    public String toString() {
        return "NivoVestine{" + "nivo=" + nivo + ", vrsta=" + vrsta + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final NivoVestine other = (NivoVestine) obj;
        return this.id == other.id;
    }

    @Override
    public String vratiNazivTabele() {
        return "nivo_vestine";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while (rs.next()) {
            
            int id = rs.getInt("nivo_vestine.id");
            Nivo nivo = Nivo.valueOf(rs.getString("nivo_vestine.nivo"));
            Ples ples = Ples.valueOf(rs.getString("nivo_vestine.vrsta"));
            
            NivoVestine nv = new NivoVestine(id, nivo, ples);
            lista.add(nv);
            
        }
        
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nivo,vrsta";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nivo_vestine.id=" + id;
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
