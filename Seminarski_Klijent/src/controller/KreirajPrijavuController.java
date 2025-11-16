/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KreirajPrijavuForma;
import forme.model.ModelTabeleStavkaPrijave;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.Instruktor;
import model.KursPlesa;
import model.Polaznik;
import model.PrijavaNaKurs;
import model.StatusPrijave;
import model.StavkaPrijave;

/**
 *
 * @author Korisnik
 */
public class KreirajPrijavuController {
    
    private final KreirajPrijavuForma kpf;
    private List<StavkaPrijave> stavke = new ArrayList<>();
    private int id = 1;
    private double ukupanIznos = 0;
    private PrijavaNaKurs zaIzmenu;

    public KreirajPrijavuController(KreirajPrijavuForma kpf) {
        this.kpf = kpf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        kpf.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (kpf.getjComboBoxKursPlesa().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kpf, "Niste odabrali kurs plesa!");
                    return;
                }
                
                KursPlesa kp = (KursPlesa) kpf.getjComboBoxKursPlesa().getSelectedItem();
                StavkaPrijave stavka = new StavkaPrijave();
                
                double iznos = kp.getCena() * kp.getTrajanje();
                stavka.setKurs(kp);
                stavka.setIznos(iznos);
                stavka.setRb(id);
                
                if (!stavke.contains(stavka)) {
                    stavke.add(stavka);
                    id++;
                    ukupanIznos += stavka.getIznos();
                } else {
                    JOptionPane.showMessageDialog(kpf, "Odabrani kurs je vec dodat!");
                    return;
                }
                
                ModelTabeleStavkaPrijave mtsp = new ModelTabeleStavkaPrijave(stavke);
                kpf.getjTable1().setModel(mtsp);
                
                kpf.getjTextFieldUkupanIznos().setText(ukupanIznos+"");
                
            }
        });
        
        kpf.ukloniStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (kpf.getjTable1().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(kpf, "Odaberite stavku koju zelite da uklonite!");
                    return;
                }
                
                StavkaPrijave selektovana = stavke.get(kpf.getjTable1().getSelectedRow());
                stavke.remove(selektovana);
                id--;
                ukupanIznos = ukupanIznos - selektovana.getIznos();
                
                ModelTabeleStavkaPrijave mtsp = new ModelTabeleStavkaPrijave(stavke);
                kpf.getjTable1().setModel(mtsp);
                
                kpf.getjTextFieldUkupanIznos().setText(ukupanIznos + "");
                
            }
        });
        
        kpf.zapamtiPrijavuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (kpf.getjComboBoxInstruktor().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kpf, "Niste odabrali instruktora!");
                    return;
                }
                
                if (kpf.getjComboBoxPolaznik().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kpf, "Niste odabrali polaznika!");
                    return;
                }
                
                if (kpf.getjComboBoxStatus().getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(kpf, "Niste oznacili status prijave!");
                    return;
                }
                
                Instruktor i = (Instruktor) kpf.getjComboBoxInstruktor().getSelectedItem();
                Polaznik p = (Polaznik) kpf.getjComboBoxPolaznik().getSelectedItem();
                StatusPrijave sp = (StatusPrijave) kpf.getjComboBoxStatus().getSelectedItem();
                
                PrijavaNaKurs pnk = new PrijavaNaKurs(0, new Date(), sp, ukupanIznos, i, p, stavke);
                
                if (stavke == null || stavke.isEmpty()) {
                    JOptionPane.showMessageDialog(kpf, "Sistem ne moze da zapamti prijavu na kurs.");
                    return;
                }
                
                try {
                    
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().kreirajPrijavu(pnk);
                    
                    JOptionPane.showMessageDialog(kpf, "Sistem je zapamtio prijavu na kurs.");
                    
                } catch (Exception ex) {
                    
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kpf, "Sistem ne moze da zapamti prijavu na kurs.");
                    
                }
                
                kpf.getjComboBoxInstruktor().setSelectedIndex(-1);
                kpf.getjComboBoxPolaznik().setSelectedIndex(-1);
                kpf.getjComboBoxStatus().setSelectedIndex(-1);
                ukupanIznos = 0;
                kpf.getjTextFieldUkupanIznos().setText(ukupanIznos+"");
                
                kpf.getjComboBoxKursPlesa().setSelectedIndex(-1);
                
                setStavke(new ArrayList<>());
                
                ModelTabeleStavkaPrijave mtsp = new ModelTabeleStavkaPrijave(stavke);
                kpf.getjTable1().setModel(mtsp);
                
            }
        });
        
        kpf.izmeniPrijavuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Instruktor i = (Instruktor) kpf.getjComboBoxInstruktor().getSelectedItem();
                Polaznik p = (Polaznik) kpf.getjComboBoxPolaznik().getSelectedItem();
                StatusPrijave sp = (StatusPrijave) kpf.getjComboBoxStatus().getSelectedItem();
                
                Date datum = null;
                String datumStr = kpf.getjTextFieldDatum().getText().trim();
                
                if (datumStr.isEmpty()) {
                    JOptionPane.showMessageDialog(kpf, "Polje za datum ne sme ostati prazno!");
                    return;
                }
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                try {
                    datum = sdf.parse(datumStr);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kpf, "Uneli ste datum u pogresnom formatu!");
                }

                zaIzmenu.setDatum(datum);
                zaIzmenu.setInstruktor(i);
                zaIzmenu.setPolaznik(p);
                zaIzmenu.setStatus(sp);
                zaIzmenu.setStavke(stavke);
                zaIzmenu.setUkupanIznos(ukupanIznos);
                System.out.println(stavke);
                
                if (stavke == null || stavke.isEmpty()) {
                    JOptionPane.showMessageDialog(kpf, "Sistem ne moze da zapamti prijavu na kurs.");
                    return;
                }
                
                try {
                    
                    Komunikacija.getInstance().konekcija();
                    Komunikacija.getInstance().promeniPrijavu(zaIzmenu);
                    
                    Koordinator.getInstance().osveziTabeluPrijave();
                    
                    JOptionPane.showMessageDialog(kpf, "Sistem je zapamtio prijavu na kurs.");
                    
                } catch (Exception ex) {
                    
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(kpf, "Sistem ne moze da zapamti prijavu na kurs.");
                    
                }
                
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
        
        kpf.setVisible(true);
        
    }

    private void pripremiFormu() {
        
        kpf.getjTextFieldUkupanIznos().setEnabled(false);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String danasnjiDatum = sdf.format(new Date());
        kpf.getjTextFieldDatum().setText(danasnjiDatum);
        
        //Combo box Instruktori
        Komunikacija.getInstance().konekcija();
        List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
        for (Instruktor i : instruktori) {
            kpf.getjComboBoxInstruktor().addItem(i);
        }

        //Combo box Polaznici
        Komunikacija.getInstance().konekcija();
        List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
        for (Polaznik p : polaznici) {
            kpf.getjComboBoxPolaznik().addItem(p);
        }
        
        //Combo box KursPlesa
        Komunikacija.getInstance().konekcija();
        List<KursPlesa> kursevi = Komunikacija.getInstance().ucitajKursevePlesa();
        for (KursPlesa kp : kursevi) {
            kpf.getjComboBoxKursPlesa().addItem(kp);
        }
        kpf.getjComboBoxKursPlesa().setSelectedIndex(-1);
        
    }

    public void pripremiZaKreiranje() {
        
        kpf.setTitle("Forma za kreiranje prijave");
        
        pripremiFormu();
        kpf.getjButtonPotvrdiIzmene().setVisible(false);
        
        kpf.getjComboBoxInstruktor().setSelectedIndex(-1);
        kpf.getjComboBoxPolaznik().setSelectedIndex(-1);
        kpf.getjComboBoxStatus().setSelectedIndex(-1);
        
        kpf.getjTextFieldDatum().setEnabled(false);
        
    }

    public void pripremiZaIzmenu(PrijavaNaKurs selektovana) {
        
        kpf.setTitle("Forma za izmenu prijave");
        
        setZaIzmenu(selektovana);
        
        kpf.getjButtonVratiSeNaGF().setVisible(false);
        kpf.getjButtonZapamti().setVisible(false);
        
        pripremiFormu();
        kpf.getjButtonPotvrdiIzmene().setVisible(true);
        
        kpf.getjComboBoxInstruktor().setSelectedItem(selektovana.getInstruktor());
        kpf.getjComboBoxStatus().setSelectedItem(selektovana.getStatus());
        kpf.getjComboBoxPolaznik().setSelectedItem(selektovana.getPolaznik());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datum = sdf.format(selektovana.getDatum());
        kpf.getjTextFieldDatum().setText(datum);
        
        kpf.getjTextFieldUkupanIznos().setText(selektovana.getUkupanIznos()+"");
        setUkupanIznos(selektovana.getUkupanIznos());
        
        //Popunjavanje tabele stavkama
        List<StavkaPrijave> sveStavke = selektovana.getStavke();
        setStavke(sveStavke);
        ModelTabeleStavkaPrijave mtsp = new ModelTabeleStavkaPrijave(sveStavke);
        kpf.getjTable1().setModel(mtsp);

    }

    public void setStavke(List<StavkaPrijave> stavke) {
        this.stavke = stavke;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public void setZaIzmenu(PrijavaNaKurs zaIzmenu) {
        this.zaIzmenu = zaIzmenu;
    }
    
    
}
