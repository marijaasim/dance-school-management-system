/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Instruktor;
import model.KursPlesa;
import model.NivoVestine;
import model.Polaznik;
import model.PrijavaNaKurs;
import model.Sertifikat;
import model.kriterijum.KriterijumPretrage;

/**
 *
 * @author Korisnik
 */
public class Komunikacija {
    
    private Socket socket;
    private static Komunikacija instance;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() {
        
    }

    public static Komunikacija getInstance() {
        if (instance == null)
            instance = new Komunikacija();
        return instance;
    }
    
    public void konekcija() {
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Greska u klasi Komunikacija, fja konekcija");
        }
    }

    public Instruktor login(Instruktor i) {
        
        Zahtev z = new Zahtev(Operacija.LOGIN, i);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        i = (Instruktor) o.getOdgovor();
        
        return i;
        
    }

    public List<Polaznik> ucitajPolaznike() {
        
        List<Polaznik> lista = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_POLAZNIKE, null);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<Polaznik>) o.getOdgovor();
        
        return lista;
        
    }

    public List<Polaznik> pretraziPolaznike(Polaznik p) {
        
        List<Polaznik> filtrirani = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.PRETRAZI_POLAZNIKA, p);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        filtrirani = (List<Polaznik>) o.getOdgovor();
        
        return filtrirani;
        
    }
    
    public NivoVestine pronadjiNivoVestine(NivoVestine nv) {
        
        Zahtev z = new Zahtev(Operacija.PRONADJI_NIVO_VESTINE, nv);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        nv = (NivoVestine) o.getOdgovor();
        
        return nv;
        
    }

    public void kreirajPolaznika(Polaznik p) {
        
        NivoVestine nv = pronadjiNivoVestine(p.getNivo());
        p.setNivo(nv);
        
        List<Polaznik> lista = ucitajPolaznike();
        
        Zahtev z = new Zahtev(Operacija.KREIRAJ_POLAZNIKA, p);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void dodajSertifikat(Sertifikat s) {
        
        Zahtev z = new Zahtev(Operacija.UBACI_SERTIFIKAT, s);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void obrisiPolaznika(Polaznik selektovani) {
        
        Zahtev z = new Zahtev(Operacija.OBRISI_POLAZNIKA, selektovani);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void promeniPolaznika(Polaznik pol) {
        
        NivoVestine nv = pronadjiNivoVestine(pol.getNivo());
        pol.setNivo(nv);
        
        Zahtev z = new Zahtev(Operacija.PROMENI_POLAZNIKA, pol);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public List<Instruktor> ucitajInstruktore() {
        
        List<Instruktor> lista = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_INSTRUKTORE, null);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<Instruktor>) o.getOdgovor();
        
        return lista;
        
    }

    public List<PrijavaNaKurs> ucitajPrijave() {
        
        List<PrijavaNaKurs> lista = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_PRIJAVE, null);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<PrijavaNaKurs>) o.getOdgovor();
        
        return lista;
        
    }

    public List<PrijavaNaKurs> pretraziPrijavu(KriterijumPretrage kp) {
        
        List<PrijavaNaKurs> filtrirani = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.PRETRAZI_PRIJAVU, kp);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        filtrirani = (List<PrijavaNaKurs>) o.getOdgovor();
        
        return filtrirani;
        
    }

    public List<KursPlesa> ucitajKursevePlesa() {
        
        List<KursPlesa> lista = new ArrayList<>();
        
        Zahtev z = new Zahtev(Operacija.UCITAJ_KURSEVE_PLESA, null);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        lista = (List<KursPlesa>) o.getOdgovor();
        
        return lista;
        
    }

    public void kreirajPrijavu(PrijavaNaKurs pnk) {
        
        Zahtev z = new Zahtev(Operacija.KREIRAJ_PRIJAVU, pnk);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

    public void promeniPrijavu(PrijavaNaKurs zaIzmenu) {
        
        Zahtev z = new Zahtev(Operacija.PROMENI_PRIJAVU, zaIzmenu);
        posiljalac.posalji(z);
        
        Odgovor o = (Odgovor) primalac.primi();
        boolean uspeh = (boolean) o.getOdgovor();
        
        if (uspeh)
            System.out.println("Uspeh");
        else
            System.out.println("Neuspeh");
        
    }

   

}
