/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziPolaznikaForma;
import forme.model.ModelTabelePolaznik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.Polaznik;

/**
 *
 * @author Korisnik
 */
public class PretraziPolaznikaController {
    
    private final PretraziPolaznikaForma ppf;
    
    public PretraziPolaznikaController(PretraziPolaznikaForma ppf) {
        this.ppf = ppf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        ppf.pretragaAddActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Polaznik p = new Polaznik();
                
                if (ppf.getjComboBoxImePrezime().getSelectedIndex() != -1) {
                    
                    String imePrezime[] = String.valueOf(ppf.getjComboBoxImePrezime().getSelectedItem()).split(" ");
                    String ime = imePrezime[0];
                    String prezime = imePrezime[1];
                    
                    p.setIme(ime);
                    p.setPrezime(prezime);
                    
                }
                
                if (ppf.getjComboBoxEmail().getSelectedIndex() != -1) {
                    
                    String email = String.valueOf(ppf.getjComboBoxEmail().getSelectedItem());
                    
                    p.setEmail(email);
                    
                }
                
                if (ppf.getjComboBoxImePrezime().getSelectedIndex() == -1 && ppf.getjComboBoxEmail().getSelectedIndex() == -1) {
                    
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nadje polaznike po zadatim kriterijumima.");
                    return;
                    
                }
                
                Komunikacija.getInstance().konekcija();
                List<Polaznik> filtrirani = Komunikacija.getInstance().pretraziPolaznike(p);
                
                if(filtrirani != null && !filtrirani.isEmpty()){
                    JOptionPane.showMessageDialog(ppf, "Sistem je našao polaznike po zadatim kriterijumima.");
                }else{
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nadje polaznike po zadatim kriterijumima.");
                }
                
                ModelTabelePolaznik mtp = new ModelTabelePolaznik(filtrirani);
                ppf.getjTable1().setModel(mtp);
                
            }
        });
        
        ppf.vratiSeNaGFAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ppf.dispose();
                Koordinator.getInstance().otvoriGlavnuFormu();
                
            }
        });
        
        ppf.izmeniAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ppf.getjTable1().getSelectedRow();
                
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje polaznika.");
                    return;
                }
                
                JOptionPane.showMessageDialog(ppf, "Sistem je nasao polaznika.");
                
                ModelTabelePolaznik mtp = (ModelTabelePolaznik) ppf.getjTable1().getModel();
                List<Polaznik> lista = mtp.getLista();
                Polaznik selektovani = lista.get(red);
                
                Koordinator.getInstance().otvoriPromeniPolaznikaFormu(selektovani);
                
            }
        });
        
        ppf.ponistiPretraguAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pripremi();
                
            }
        });
        
        ppf.detaljanPrikazAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ppf.getjTable1().getSelectedRow();
                
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje polaznika.");
                    return;
                }
                
                JOptionPane.showMessageDialog(ppf, "Sistem je nasao polaznika.");
                
                ModelTabelePolaznik mtp = (ModelTabelePolaznik) ppf.getjTable1().getModel();
                List<Polaznik> lista = mtp.getLista();
                Polaznik selektovani = lista.get(red);
                
                Koordinator.getInstance().otvoriIzabraniPolaznikFormu(selektovani);
                
            }
        });
        
    }
    
    public void prikaziFormu() {
        
        ppf.setVisible(true);
        ppf.setTitle("Forma za pretragu polaznika");
        
    }

    public void pripremiFormuZaPretragu() {
        
        pripremi();
        ppf.getjButtonIzmeni().setVisible(false);
        
    }

    public void pripremiFormuZaIzmenu() {
        
        pripremi();
        ppf.getjButtonIzmeni().setVisible(true);
        
    }

    private void pripremi() {
        
        //tabela
        
        Komunikacija.getInstance().konekcija();
        List<Polaznik> lista = Komunikacija.getInstance().ucitajPolaznike();
        ModelTabelePolaznik mtp = new ModelTabelePolaznik(lista);
        ppf.getjTable1().setModel(mtp);
        
        //combo boxevi
        
        List<Polaznik> sortIme = new ArrayList<>(lista);
        sortIme.sort(Comparator.comparing(Polaznik::getIme, String.CASE_INSENSITIVE_ORDER));
        
        List<Polaznik> sortEmail = new ArrayList<>(lista);
        sortEmail.sort(Comparator.comparing(Polaznik::getEmail, String.CASE_INSENSITIVE_ORDER));
        
        for (Polaznik p : sortIme) {
            ppf.getjComboBoxImePrezime().addItem(p.getIme() + " " + p.getPrezime());
        }
        
        for (Polaznik p : sortEmail) {
            ppf.getjComboBoxEmail().addItem(p.getEmail());
        }
        
        ppf.getjComboBoxImePrezime().setSelectedIndex(-1);
        ppf.getjComboBoxEmail().setSelectedIndex(-1);
        
    }

    public void osveziTabelu() {
        
        Komunikacija.getInstance().konekcija();
        List<Polaznik> lista = Komunikacija.getInstance().ucitajPolaznike();
        ModelTabelePolaznik mtp = new ModelTabelePolaznik(lista);
        ppf.getjTable1().setModel(mtp);
        
    }
    
    
    
}
