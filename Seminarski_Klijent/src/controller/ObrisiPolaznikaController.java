/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.ObrisiPolaznikaForma;
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
import model.PrijavaNaKurs;

/**
 *
 * @author Korisnik
 */
public class ObrisiPolaznikaController {
    
    private final ObrisiPolaznikaForma opf;
    
    public ObrisiPolaznikaController(ObrisiPolaznikaForma opf) {
        this.opf = opf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        opf.vratiSeNaGFAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                opf.dispose();
                Koordinator.getInstance().otvoriGlavnuFormu();
                
            }
        });
        
        opf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = opf.getjTable1().getSelectedRow();
                
                if (red == -1) {
                    JOptionPane.showMessageDialog(opf, "Sistem ne moze da nadje polaznika.");
                    return;
                }
                
                JOptionPane.showMessageDialog(opf, "Sistem je nasao polaznika.");
                
                ModelTabelePolaznik mtp = (ModelTabelePolaznik) opf.getjTable1().getModel();
                List<Polaznik> lista = mtp.getLista();
                Polaznik selektovani = lista.get(red);
                
                Komunikacija.getInstance().konekcija();
                List<PrijavaNaKurs> svePrijave = Komunikacija.getInstance().ucitajPrijave();
                
                for (PrijavaNaKurs p : svePrijave) {
                    if (p.getPolaznik().getId() == selektovani.getId()) {
                        JOptionPane.showMessageDialog(opf, "Sistem ne moze da obrise polaznika.");
                        return;
                    }
                }
                
                try {
                    
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().obrisiPolaznika(selektovani);
                    
                    pripremiFormu();

                    JOptionPane.showMessageDialog(opf, "Sistem je obrisao polaznika.");
                    
                    
                } catch (Exception ex) {
                    
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(opf, "Sistem ne moze da obrise polaznika.");
                    
                }
            }

        });
        
        opf.pretragaAddActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Polaznik p = new Polaznik();
                
                if (opf.getjComboBoxImePrezime().getSelectedIndex() != -1) {
                    
                    String imePrezime[] = String.valueOf(opf.getjComboBoxImePrezime().getSelectedItem()).split(" ");
                    String ime = imePrezime[0];
                    String prezime = imePrezime[1];
                    
                    p.setIme(ime);
                    p.setPrezime(prezime);
                    
                }
                
                if (opf.getjComboBoxEmail().getSelectedIndex() != -1) {
                    
                    String email = String.valueOf(opf.getjComboBoxEmail().getSelectedItem());
                    
                    p.setEmail(email);
                    
                }
                
                if (opf.getjComboBoxImePrezime().getSelectedIndex() == -1 && opf.getjComboBoxEmail().getSelectedIndex() == -1) {
                    
                    JOptionPane.showMessageDialog(opf, "Sistem ne može da nadje polaznike po zadatim kriterijumima.");
                    return;
                    
                }
                
                Komunikacija.getInstance().konekcija();
                List<Polaznik> filtrirani = Komunikacija.getInstance().pretraziPolaznike(p);
                
                if(filtrirani != null && !filtrirani.isEmpty()){
                    JOptionPane.showMessageDialog(opf, "Sistem je našao polaznike po zadatim kriterijumima.");
                }else{
                    JOptionPane.showMessageDialog(opf, "Sistem ne može da nadje polaznike po zadatim kriterijumima.");
                }
                
                ModelTabelePolaznik mtp = new ModelTabelePolaznik(filtrirani);
                opf.getjTable1().setModel(mtp);
                
            }
        });
        
        opf.ponistiPretraguAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                opf.getjComboBoxEmail().setSelectedIndex(-1);
                opf.getjComboBoxImePrezime().setSelectedIndex(-1);
                pripremiFormu();
                
            }
        });
        
        
    }
    
    public void prikaziFormu() {
        
        pripremiFormu();
        opf.setVisible(true);
        opf.setTitle("Forma za brisanje polaznika");
        
    }

    private void pripremiFormu() {
        
        Komunikacija.getInstance().konekcija();
        List<Polaznik> lista = Komunikacija.getInstance().ucitajPolaznike();
        ModelTabelePolaznik mtp = new ModelTabelePolaznik(lista);
        opf.getjTable1().setModel(mtp);
        
        List<Polaznik> sortIme = new ArrayList<>(lista);
        sortIme.sort(Comparator.comparing(Polaznik::getIme, String.CASE_INSENSITIVE_ORDER));
        
        List<Polaznik> sortEmail = new ArrayList<>(lista);
        sortEmail.sort(Comparator.comparing(Polaznik::getEmail, String.CASE_INSENSITIVE_ORDER));
        
        for (Polaznik p : sortIme) {
            opf.getjComboBoxImePrezime().addItem(p.getIme() + " " + p.getPrezime());
        }
        
        for (Polaznik p : sortEmail) {
            opf.getjComboBoxEmail().addItem(p.getEmail());
        }
        
        opf.getjComboBoxImePrezime().setSelectedIndex(-1);
        opf.getjComboBoxEmail().setSelectedIndex(-1);
        
    }
    
}
