/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PromeniPolaznikaForma;
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
public class PromeniPolaznikaController {
    
    private final PromeniPolaznikaForma ppf;
    private int id;
    private Polaznik p;
    
    public PromeniPolaznikaController(PromeniPolaznikaForma ppf) {
        this.ppf = ppf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        ppf.promeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ime = ppf.getjTextFieldIme().getText().trim();
                String prezime = ppf.getjTextFieldPrezime().getText().trim();
                String email = ppf.getjTextFieldEmail().getText().trim();
                Nivo nivo = (Nivo) ppf.getjComboBoxNivo().getSelectedItem();
                Ples ples = (Ples) ppf.getjComboBoxPles().getSelectedItem();
                
                // Validacija imena
                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da zapamti polaznika.");
                    return;
                }
                if (ime.length() > 50) {
                    JOptionPane.showMessageDialog(ppf, "Ime ne sme imati više od 50 karaktera!");
                    return;
                }
                if (!Character.isUpperCase(ime.charAt(0))) {
                    JOptionPane.showMessageDialog(ppf, "Ime mora početi velikim slovom!");
                    return;
                }

                // Validacija prezimena
                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(ppf, "Prezime ne sme biti prazno!");
                    return;
                }
                if (prezime.length() > 50) {
                    JOptionPane.showMessageDialog(ppf, "Prezime ne sme imati više od 50 karaktera!");
                    return;
                }
                if (!Character.isUpperCase(prezime.charAt(0))) {
                    JOptionPane.showMessageDialog(ppf, "Prezime mora početi velikim slovom!");
                    return;
                }

                // Validacija email adrese
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(ppf, "Email ne sme biti prazan!");
                    return;
                }
                if (!email.equals(email.toLowerCase())) {
                    JOptionPane.showMessageDialog(ppf, "Email mora biti malim slovima!");
                    return;
                }
                if (!email.matches("[a-z0-9._%+-]+@gmail\\.com")) {
                    JOptionPane.showMessageDialog(ppf, "Email ima nevalidan format!");
                    return;
                }
                
                // Validacija nivoa vestine
                if (ppf.getjComboBoxNivo().getSelectedIndex() == -1 && ppf.getjComboBoxPles().getSelectedIndex() != -1) {
                    JOptionPane.showMessageDialog(ppf, "Niste izabrali nivo!");
                    return;
                }
                if (ppf.getjComboBoxPles().getSelectedIndex() == -1 && ppf.getjComboBoxNivo().getSelectedIndex() != -1) {
                    JOptionPane.showMessageDialog(ppf, "Niste izabrali ples!");
                    return;
                }
                if (ppf.getjComboBoxNivo().getSelectedIndex() == -1 && ppf.getjComboBoxPles().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ppf, "Niste izabrali nivo i ples!");
                    return;
                }
                
                NivoVestine nv = new NivoVestine(-1,nivo,ples);
                Polaznik pol = new Polaznik(id, ime, prezime, email, nv);
                
                try {
                    
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().promeniPolaznika(pol);

                    JOptionPane.showMessageDialog(ppf, "Sistem je zapamtio polaznika.");
                    Koordinator.getInstance().osveziTabeluPolaznici();
                    
                } catch (Exception ex) {
                    
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da zapamti polaznika.");
                    
                }
                
            }
        });
        
        
    }

    public void prikaziFormu(Polaznik selektovani) {
        
        ppf.setTitle("Forma za izmenu polaznika");
        
        ppf.getjTextFieldId().setText(selektovani.getId() + "");
        ppf.getjTextFieldId().setEnabled(false);
        ppf.getjTextFieldIme().setText(selektovani.getIme());
        ppf.getjTextFieldPrezime().setText(selektovani.getPrezime());
        ppf.getjTextFieldEmail().setText(selektovani.getEmail());
        ppf.getjComboBoxNivo().setSelectedItem(selektovani.getNivo().getNivo());
        ppf.getjComboBoxPles().setSelectedItem(selektovani.getNivo().getVrsta());
        
        ppf.setVisible(true);
        
        id = selektovani.getId();
        p = selektovani;
        
    }

    public int getId() {
        return id;
    }

}
