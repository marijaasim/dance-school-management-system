/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Polaznik;

/**
 *
 * @author Korisnik
 */
public class ModelTabelePolaznik extends AbstractTableModel {
    
    List<Polaznik> lista;
    String[] kolone = {"ID", "Ime i prezime", "Email", "Nivo vestine"};

    public ModelTabelePolaznik(List<Polaznik> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Polaznik p = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getIme() + " " + p.getPrezime();
            case 2:
                return p.getEmail();
            case 3:
                return p.getNivo().getNivo() + " - " + p.getNivo().getVrsta();
            default:
                return "NA";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Polaznik> getLista() {
        return lista;
    }
    
    
    
}
