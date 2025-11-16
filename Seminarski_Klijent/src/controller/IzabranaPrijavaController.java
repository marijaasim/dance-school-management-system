/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.IzabranaPrijavaForma;
import forme.model.ModelTabeleStavkaPrijave;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import komunikacija.Komunikacija;
import model.Instruktor;
import model.Polaznik;
import model.PrijavaNaKurs;
import model.StavkaPrijave;

/**
 *
 * @author Korisnik
 */
public class IzabranaPrijavaController {
    
    private final IzabranaPrijavaForma ipf;
    
    public IzabranaPrijavaController(IzabranaPrijavaForma ipf) {
        this.ipf = ipf;
    }
    
    public void prikaziFormu(PrijavaNaKurs selektovana) {
        pripremiFormu(selektovana);
        ipf.setVisible(true);
        ipf.setTitle("Izabrana prijava");
    }

    private void pripremiFormu(PrijavaNaKurs selektovana) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datum = sdf.format(selektovana.getDatum());
        
        //Combo box Instruktori
        Komunikacija.getInstance().konekcija();
        List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
        for (Instruktor i : instruktori) {
            ipf.getjComboBoxInstruktor().addItem(i);
        }

        //Combo box Polaznici
        Komunikacija.getInstance().konekcija();
        List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
        for (Polaznik p : polaznici) {
            ipf.getjComboBoxPolaznik().addItem(p);
        }
        
        ipf.getjTextFieldDatum().setText(datum);
        ipf.getjComboBoxInstruktor().setSelectedItem(selektovana.getInstruktor());
        ipf.getjComboBoxPolaznik().setSelectedItem(selektovana.getPolaznik());
        ipf.getjComboBoxStatus().setSelectedItem(selektovana.getStatus());
        ipf.getjTextFieldUkupanIznos().setText(selektovana.getUkupanIznos()+"");
        
        //Popunjavanje tabele stavkama
        List<StavkaPrijave> sveStavke = selektovana.getStavke();
        ModelTabeleStavkaPrijave mtsp = new ModelTabeleStavkaPrijave(sveStavke);
        ipf.getjTable1().setModel(mtsp);
        
        ipf.getjTextFieldUkupanIznos().setEnabled(false);
        ipf.getjTextFieldDatum().setEnabled(false);
        ipf.getjComboBoxInstruktor().setEnabled(false);
        ipf.getjComboBoxPolaznik().setEnabled(false);
        ipf.getjComboBoxStatus().setEnabled(false);
        
    }
    
}
