/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.PrijavaNaKurs;

/**
 *
 * @author Korisnik
 */
public class ModelTabelePrijava extends AbstractTableModel {
    
    List<PrijavaNaKurs> lista;
    String[] kolone = {"ID", "Datum", "Status", "Ukupan iznos", "Instruktor", "Polaznik"};

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
        PrijavaNaKurs p = lista.get(rowIndex);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datumFormatiran = sdf.format(p.getDatum());
        
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return datumFormatiran;
            case 2:
                return p.getStatus();
            case 3:
                return p.getUkupanIznos();
            case 4:
                return p.getInstruktor().getIme() + " " + p.getInstruktor().getPrezime();
            case 5:
                return p.getPolaznik().getIme()+ " " + p.getPolaznik().getPrezime();
            default:
                return "N/A";
        }
    }

    public ModelTabelePrijava(List<PrijavaNaKurs> lista) {
        lista.sort(Comparator.comparing(p -> ((PrijavaNaKurs) p).getId()));
        this.lista = lista;
    }

    public List<PrijavaNaKurs> getLista() {
        return lista;
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
}
