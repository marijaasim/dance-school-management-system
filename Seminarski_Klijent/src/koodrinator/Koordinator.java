/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koodrinator;

import controller.GlavnaFormaController;
import controller.IzabranaPrijavaController;
import controller.IzabraniPolaznikController;
import controller.KreirajPolaznikaController;
import controller.KreirajPrijavuController;
import controller.LoginController;
import controller.ObrisiPolaznikaController;
import controller.PretraziPolaznikaController;
import controller.PretraziPrijavuController;
import controller.PromeniPolaznikaController;
import controller.UbaciSertifikatController;
import forme.GlavnaForma;
import forme.IzabranaPrijavaForma;
import forme.IzabraniPolaznikForma;
import forme.KreirajPolaznikaForma;
import forme.KreirajPrijavuForma;
import forme.LoginForma;
import forme.ObrisiPolaznikaForma;
import forme.PretraziPolaznikaForma;
import forme.PretraziPrijavuForma;
import forme.PromeniPolaznikaForma;
import forme.UbaciSertifikatForma;
import model.Instruktor;
import model.Polaznik;
import model.PrijavaNaKurs;

/**
 *
 * @author Korisnik
 */
public class Koordinator {
    
    private static Koordinator instance;
    
    private Instruktor ulogvani;
    
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PretraziPolaznikaController pretraziPolaznikaController;
    private KreirajPolaznikaController kreirajPolaznikaController;
    private UbaciSertifikatController ubaciSertifikatController;
    private ObrisiPolaznikaController obrisiPolaznikaController;
    private PromeniPolaznikaController promeniPolaznikaController;
    private PretraziPrijavuController pretraziPrijavuController;
    private KreirajPrijavuController kreirajPrijavuController;
    private IzabranaPrijavaController izabranaPrijavaController;
    private IzabraniPolaznikController izabraniPolaznikController;
    
    private Koordinator(){
        
    }
    
    public static Koordinator getInstance(){
        if(instance == null)
            instance = new Koordinator();
        return instance;
    }
    
    public Instruktor getUlogvani() {
        return ulogvani;
    }

    public void setUlogvani(Instruktor ulogvani) {
        this.ulogvani = ulogvani;
    }

    public void otvoriLoginFormu() {
        
        loginController = new LoginController(new LoginForma());
        loginController.prikaziFormu();
        
    }

    public void otvoriGlavnuFormu() {
        
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.prikaziFormu();
        
    }
    
    public void zatvoriGF() {
        
        glavnaFormaController.zatvoriFormu();
        
    }

    public void otvoriPretraziPolaznikaFormu() {
        
        pretraziPolaznikaController = new PretraziPolaznikaController(new PretraziPolaznikaForma());
        pretraziPolaznikaController.pripremiFormuZaPretragu();
        pretraziPolaznikaController.prikaziFormu();
        
    }

    public void otvoriKreirajPolaznikaFormu() {
        
        kreirajPolaznikaController = new KreirajPolaznikaController(new KreirajPolaznikaForma());
        kreirajPolaznikaController.prikaziFormu();
        
    }

    public void otvoriUbaciVrstuSertifikatFormu() {
        
        ubaciSertifikatController = new UbaciSertifikatController(new UbaciSertifikatForma());
        ubaciSertifikatController.prikaziFormu();
        
    }

    public void otvoriObrisiPolaznikaFormu() {
        
        obrisiPolaznikaController = new ObrisiPolaznikaController(new ObrisiPolaznikaForma());
        obrisiPolaznikaController.prikaziFormu();
        
    }

    public void otvoriPretraziPolaznikaFormuZaIzmenu() {
        
        pretraziPolaznikaController = new PretraziPolaznikaController(new PretraziPolaznikaForma());
        pretraziPolaznikaController.pripremiFormuZaIzmenu();
        pretraziPolaznikaController.prikaziFormu();
        
    }

    public void otvoriPromeniPolaznikaFormu(Polaznik selektovani) {
        
        promeniPolaznikaController = new PromeniPolaznikaController(new PromeniPolaznikaForma());
        promeniPolaznikaController.prikaziFormu(selektovani);
        
    }

    public void osveziTabeluPolaznici() {
        
        pretraziPolaznikaController.osveziTabelu();
        
    }

    public void otvoriPretraziPrijavuFormu() {
        
        pretraziPrijavuController = new PretraziPrijavuController(new PretraziPrijavuForma());
        pretraziPrijavuController.pripremiFormuZaPretragu();
        pretraziPrijavuController.prikaziFormu();
        
    }

    public void otvoriKreirajPrijavuFormu() {
        
        kreirajPrijavuController = new KreirajPrijavuController(new KreirajPrijavuForma());
        kreirajPrijavuController.pripremiZaKreiranje();
        kreirajPrijavuController.prikaziFormu();
        
    }

    public void otvoriPretraziPrijavuFormuZaIzmenu() {
        
        pretraziPrijavuController = new PretraziPrijavuController(new PretraziPrijavuForma());
        pretraziPrijavuController.pripremiFormuZaIzmenu();
        pretraziPrijavuController.prikaziFormu();
        
    }

    public void otvoriPromeniPrijavuFormu(PrijavaNaKurs selektovana) {
        
        kreirajPrijavuController = new KreirajPrijavuController(new KreirajPrijavuForma());
        kreirajPrijavuController.pripremiZaIzmenu(selektovana);
        kreirajPrijavuController.prikaziFormu();
        
    }

    public void otvoriIzabranaPrijavaFormu(PrijavaNaKurs selektovana) {
        
        izabranaPrijavaController = new IzabranaPrijavaController(new IzabranaPrijavaForma());
        izabranaPrijavaController.prikaziFormu(selektovana);
        
    }

    public void otvoriIzabraniPolaznikFormu(Polaznik selektovani) {
        
        izabraniPolaznikController = new IzabraniPolaznikController(new IzabraniPolaznikForma());
        izabraniPolaznikController.prikaziFormu(selektovani);
        
    }

    public void osveziTabeluPrijave() {
        
        pretraziPrijavuController.osveziTabelu();
        
    }

}
