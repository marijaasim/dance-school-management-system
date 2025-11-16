/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Instruktor;
import model.KursPlesa;
import model.NivoVestine;
import model.Polaznik;
import model.PrijavaNaKurs;
import model.Sertifikat;
import model.kriterijum.KriterijumPretrage;
import operacije.KreirajPolaznikaSO;
import operacije.KreirajPrijavuNaKursSO;
import operacije.LoginSO;
import operacije.ObrisiPolaznikaSO;
import operacije.PretraziPolaznikaSO;
import operacije.PretraziPrijavuSO;
import operacije.PromeniPolaznikaSO;
import operacije.PromeniPrijavuSO;
import operacije.PronadjiNivoVestineSO;
import operacije.UbaciSertifikatSO;
import operacije.UcitajInstruktoreSO;
import operacije.UcitajKursevePlesaSO;
import operacije.UcitajPolaznikeSO;
import operacije.UcitajPrijaveSO;

/**
 *
 * @author Korisnik
 */
public class Controller {
    
    private static Controller instance;
    
    private Controller(){
        
    }
    
    public static Controller getInstance(){
        if(instance == null)
            instance=new Controller();
        return instance;
    }

    public Instruktor login(Instruktor i) throws Exception {
        
        LoginSO operacija = new LoginSO();
        operacija.izvrsi(i, null);
        System.out.println("Klasa Controller: " + operacija.getI());
        return operacija.getI();
        
    }

    public List<Polaznik> ucitajPolaznike() throws Exception {
        
        UcitajPolaznikeSO operacija = new UcitajPolaznikeSO();
        operacija.izvrsi(new Polaznik(), null);
        System.out.println("Klasa Controller: " + operacija.getPolaznici());
        return operacija.getPolaznici();
        
    }

    public List<Polaznik> pretraziPolaznika(Polaznik p) throws Exception {
        
        PretraziPolaznikaSO operacija = new PretraziPolaznikaSO();
        operacija.izvrsi(p, null);
        System.out.println("Klasa Controller: " + operacija.getPolaznici());
        return operacija.getPolaznici();
        
    }

    public NivoVestine pronadjiNivoVestine(NivoVestine nv) throws Exception {
        
        PronadjiNivoVestineSO operacija = new PronadjiNivoVestineSO();
        operacija.izvrsi(nv, null);
        System.out.println("Klasa Controller: " + operacija.getNivo());
        return operacija.getNivo();
        
    }

    public boolean kreirajPolaznika(Polaznik p) {
        
        try {
        
            KreirajPolaznikaSO operacija = new KreirajPolaznikaSO();
            operacija.izvrsi(p, null);
            System.out.println("Klasa Controller: " + operacija.getPolaznik());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean dodajSertifikat(Sertifikat s) {
        
        try {
        
            UbaciSertifikatSO operacija = new UbaciSertifikatSO();
            operacija.izvrsi(s, null);
            System.out.println("Klasa Controller: " + operacija.getS());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean obrisiPolaznika(Polaznik p2) {
        
        try {
        
            ObrisiPolaznikaSO operacija = new ObrisiPolaznikaSO();
            operacija.izvrsi(p2, null);
            System.out.println("Klasa Controller: " + operacija.getP());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean promeniPolaznika(Polaznik p3) {
        
        try {
        
            PromeniPolaznikaSO operacija = new PromeniPolaznikaSO();
            operacija.izvrsi(p3, null);
            System.out.println("Klasa Controller: " + operacija.getP());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public List<Instruktor> ucitajInstruktore() throws Exception {
        
        UcitajInstruktoreSO operacija = new UcitajInstruktoreSO();
        operacija.izvrsi(new Instruktor(), null);
        System.out.println("Klasa Controller: " + operacija.getInstruktori());
        return operacija.getInstruktori();
        
    }

    public List<PrijavaNaKurs> ucitajPrijave() throws Exception {
        
        UcitajPrijaveSO operacija = new UcitajPrijaveSO();
        operacija.izvrsi(new PrijavaNaKurs(), null);
        System.out.println("Klasa Controller: " + operacija.getPrijave());
        return operacija.getPrijave();
        
    }

    public List<PrijavaNaKurs> pretraziPrijavu(KriterijumPretrage kp) throws Exception {
        
        PretraziPrijavuSO operacija = new PretraziPrijavuSO();
        operacija.izvrsi(new PrijavaNaKurs(), kp);
        System.out.println("Klasa Controller: " + operacija.getPrijave());
        return operacija.getPrijave();
        
    }

    public List<KursPlesa> ucitajKursevePlesa() throws Exception {
        
        UcitajKursevePlesaSO operacija = new UcitajKursevePlesaSO();
        operacija.izvrsi(new KursPlesa(), null);
        System.out.println("Klasa Controller: " + operacija.getKursevi());
        return operacija.getKursevi();
        
    }

    public boolean kreirajPrijavu(PrijavaNaKurs pnk) {
        
        try {
        
            KreirajPrijavuNaKursSO operacija = new KreirajPrijavuNaKursSO();
            operacija.izvrsi(pnk, null);
            System.out.println("Klasa Controller: " + operacija.getPrijava());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean promeniPrijavu(PrijavaNaKurs zaIzmenu) {
        
        try {
        
            PromeniPrijavuSO operacija = new PromeniPrijavuSO();
            operacija.izvrsi(zaIzmenu, null);
            System.out.println("Klasa Controller: " + operacija.getPnk());
            return true;
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    
}
