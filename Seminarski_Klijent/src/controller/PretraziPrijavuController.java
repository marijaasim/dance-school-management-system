/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziPrijavuForma;
import forme.model.ModelTabelePrijava;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.Instruktor;
import model.Polaznik;
import model.PrijavaNaKurs;
import model.kriterijum.KriterijumPretrage;

/**
 *
 * @author Korisnik
 */
public class PretraziPrijavuController {
    
    private final PretraziPrijavuForma ppf;
    
    public PretraziPrijavuController(PretraziPrijavuForma ppf) {
        this.ppf = ppf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        ppf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Instruktor i = null;
                Polaznik p = null;
                Date datum = null;
                
                String datumStr = ppf.getjTextFieldDatum().getText().trim();
                
                if (ppf.getjComboBoxInstruktor().getSelectedIndex() == -1 &&
                    ppf.getjComboBoxPolaznik().getSelectedIndex() == -1 &&
                    datumStr.isEmpty()) {
                    JOptionPane.showMessageDialog(ppf, "Niste odabrali nijedan kriterijum pretrage!");
                    return;
                }
                
                if (ppf.getjComboBoxInstruktor().getSelectedIndex() != -1) {
                    i = (Instruktor) ppf.getjComboBoxInstruktor().getSelectedItem();
                }
                
                if (ppf.getjComboBoxPolaznik().getSelectedIndex() != -1) {
                    p = (Polaznik) ppf.getjComboBoxPolaznik().getSelectedItem();
                }
                
                if (!datumStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                    try {
                        datum = sdf.parse(datumStr);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ppf, "Uneli ste datum u pogresnom formatu!");
                    }

                }
                
                KriterijumPretrage kp = new KriterijumPretrage(i, p, datum);
                
                Komunikacija.getInstance().konekcija();
                List<PrijavaNaKurs> filtrirani = Komunikacija.getInstance().pretraziPrijavu(kp);
                
                if(filtrirani != null && !filtrirani.isEmpty()){
                    JOptionPane.showMessageDialog(ppf, "Sistem je nasao prijave na kurs po zadatim kriterijumima.");
                }else{
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje prijave na kurs po zadatim kriterijumima.");
                }
                
                ModelTabelePrijava mtp = new ModelTabelePrijava(filtrirani);
                ppf.getjTable1().setModel(mtp);
                
            }
        });
        
        ppf.ponistiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pripremiFormu();
                
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
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje prijavu na kurs.");
                    return;
                }
                
                JOptionPane.showMessageDialog(ppf, "Sistem je nasao prijavu na kurs.");
                
                ModelTabelePrijava mtp = (ModelTabelePrijava) ppf.getjTable1().getModel();
                List<PrijavaNaKurs> lista = mtp.getLista();
                PrijavaNaKurs selektovana = lista.get(red);
                
                Koordinator.getInstance().otvoriPromeniPrijavuFormu(selektovana);
                
            }
        });
        
        ppf.detaljanPrikazAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ppf.getjTable1().getSelectedRow();
                
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje prijavu na kurs.");
                    return;
                }
                
                JOptionPane.showMessageDialog(ppf, "Sistem je nasao prijavu na kurs.");
                
                ModelTabelePrijava mtp = (ModelTabelePrijava) ppf.getjTable1().getModel();
                List<PrijavaNaKurs> lista = mtp.getLista();
                PrijavaNaKurs selektovana = lista.get(red);
                
                Koordinator.getInstance().otvoriIzabranaPrijavaFormu(selektovana);
                
            }
        });
        
    }
    
    public void prikaziFormu() {
        ppf.setVisible(true);
        ppf.setTitle("Forma za pretragu prijave");
    }
    
    public void pripremiFormuZaPretragu() {
        pripremiFormu();
        ppf.getjButtonIzmeni().setVisible(false);
    }

    public void pripremiFormuZaIzmenu() {
        pripremiFormu();
        ppf.getjButtonIzmeni().setVisible(true);
    }

    private void pripremiFormu() {
        
        //Combo box Instruktori
        Komunikacija.getInstance().konekcija();
        List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
        for (Instruktor i : instruktori) {
            ppf.getjComboBoxInstruktor().addItem(i);
        }
        ppf.getjComboBoxInstruktor().setSelectedIndex(-1);

        //Combo box Polaznici
        Komunikacija.getInstance().konekcija();
        List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
        for (Polaznik p : polaznici) {
            ppf.getjComboBoxPolaznik().addItem(p);
        }
        ppf.getjComboBoxPolaznik().setSelectedIndex(-1);
        
        //Tabela Prijave na kurs
        Komunikacija.getInstance().konekcija();
        List<PrijavaNaKurs> prijave = Komunikacija.getInstance().ucitajPrijave();
        ModelTabelePrijava mtp = new ModelTabelePrijava(prijave);
        ppf.getjTable1().setModel(mtp);
        
        ppf.getjTextFieldDatum().setText("");
        
    }

    public void osveziTabelu() {
        pripremiFormu();
    }

    
}
