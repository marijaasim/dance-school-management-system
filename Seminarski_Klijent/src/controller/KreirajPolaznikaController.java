/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KreirajPolaznikaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.Nivo;
import model.NivoVestine;
import model.Ples;
import model.Polaznik;

/**
 *
 * @author Korisnik
 */

public class KreirajPolaznikaController {
    
    private final KreirajPolaznikaForma kpf;

    public KreirajPolaznikaController(KreirajPolaznikaForma kpf) {
        this.kpf = kpf;
        addActionListeners();
    }
    
    private void addActionListeners() {
        
        kpf.pamcenjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ime = kpf.getjTextFieldIme().getText().trim();
                String prezime = kpf.getjTextFieldPrezime().getText().trim();
                String email = kpf.getjTextFieldEmail().getText().trim();
                Nivo nivo = (Nivo) kpf.getjComboBoxNivo().getSelectedItem();
                Ples ples = (Ples) kpf.getjComboBoxPles().getSelectedItem();
                
                // Validacija imena
                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(kpf, "Polaznik mora imati ime.");
                    return;
                }
                if (ime.length() > 50) {
                    JOptionPane.showMessageDialog(kpf, "Ime ne sme imati više od 50 karaktera!");
                    return;
                }
                if (!Character.isUpperCase(ime.charAt(0))) {
                    JOptionPane.showMessageDialog(kpf, "Ime mora početi velikim slovom!");
                    return;
                }

                // Validacija prezimena
                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(kpf, "Prezime ne sme biti prazno!");
                    return;
                }
                if (prezime.length() > 50) {
                    JOptionPane.showMessageDialog(kpf, "Prezime ne sme imati više od 50 karaktera!");
                    return;
                }
                if (!Character.isUpperCase(prezime.charAt(0))) {
                    JOptionPane.showMessageDialog(kpf, "Prezime mora početi velikim slovom!");
                    return;
                }

                // Validacija email adrese
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(kpf, "Email ne sme biti prazan!");
                    return;
                }
                if (!email.equals(email.toLowerCase())) {
                    JOptionPane.showMessageDialog(kpf, "Email mora biti malim slovima!");
                    return;
                }
                if (!email.matches("[a-z0-9._%+-]+@gmail\\.com")) {
                    JOptionPane.showMessageDialog(kpf, "Email ima nevalidan format!");
                    return;
                }
                
                // Validacija nivoa vestine
                if (kpf.getjComboBoxNivo().getSelectedIndex() == -1 && kpf.getjComboBoxPles().getSelectedIndex() != -1) {
                    JOptionPane.showMessageDialog(kpf, "Niste izabrali nivo!");
                    return;
                }
                if (kpf.getjComboBoxPles().getSelectedIndex() == -1 && kpf.getjComboBoxNivo().getSelectedIndex() != -1) {
                    JOptionPane.showMessageDialog(kpf, "Niste izabrali ples!");
                    return;
                }
                if (kpf.getjComboBoxNivo().getSelectedIndex() == -1 && kpf.getjComboBoxPles().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kpf, "Niste izabrali nivo i ples!");
                    return;
                }
                
                NivoVestine nv = new NivoVestine(-1,nivo,ples);

                Polaznik p = new Polaznik(-1, ime, prezime, email, nv);
                
                try {
                    
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().kreirajPolaznika(p);

                    JOptionPane.showMessageDialog(kpf, "Sistem je zapamtio polaznika.");
                    
                } catch (Exception ex) {
                    
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kpf, "Sistem ne moze da zapamti polaznika.");
                    
                }
                
                kpf.getjTextFieldIme().setText("");
                kpf.getjTextFieldPrezime().setText("");
                kpf.getjTextFieldEmail().setText("");
                kpf.getjComboBoxNivo().setSelectedIndex(-1);
                kpf.getjComboBoxPles().setSelectedIndex(-1);
                
            }
        });
        
        kpf.vratiNaGFAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                kpf.dispose();
                Koordinator.getInstance().otvoriGlavnuFormu();
                
            }
        });
        
    }

    public void prikaziFormu() {
        
        pripremiFormu();
        kpf.setVisible(true);
        kpf.setTitle("Forma za kreiranje polaznika");
        
    }

    private void pripremiFormu() {
        kpf.getjComboBoxNivo().setSelectedIndex(-1);
        kpf.getjComboBoxPles().setSelectedIndex(-1);
    }
    
}
