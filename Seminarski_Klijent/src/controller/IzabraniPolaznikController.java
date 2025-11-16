/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.IzabraniPolaznikForma;
import model.Polaznik;

/**
 *
 * @author Korisnik
 */
public class IzabraniPolaznikController {
    
    private final IzabraniPolaznikForma ipf;
    
    public IzabraniPolaznikController(IzabraniPolaznikForma ipf) {
        this.ipf = ipf;
    }

    public void prikaziFormu(Polaznik selektovani) {
        pripremiFormu(selektovani);
        ipf.setVisible(true);
        ipf.setTitle("Izabrani polaznik");
    }

    private void pripremiFormu(Polaznik selektovani) {
        
        ipf.getjTextFieldIme().setText(selektovani.getIme());
        ipf.getjTextFieldPrezime().setText(selektovani.getPrezime());
        ipf.getjTextFieldEmail().setText(selektovani.getEmail());
        ipf.getjComboBoxNivo().setSelectedItem(selektovani.getNivo().getNivo());
        ipf.getjComboBoxPles().setSelectedItem(selektovani.getNivo().getVrsta());
        
        ipf.getjTextFieldIme().setEnabled(false);
        ipf.getjTextFieldPrezime().setEnabled(false);
        ipf.getjTextFieldEmail().setEnabled(false);
        ipf.getjComboBoxNivo().setEnabled(false);
        ipf.getjComboBoxPles().setEnabled(false);
        
    }
    
}
