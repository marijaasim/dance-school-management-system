/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import komunikacija.Odgovor;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import komunikacija.Posiljalac;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ObradaKlijentskihZahteva extends Thread {
    
    Socket socket;
    Primalac receiver;
    Posiljalac sender;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        
        this.socket = socket;
        receiver = new Primalac(socket);
        sender = new Posiljalac(socket);
        
    }

    @Override
    public void run() {
        
        while (!kraj) {
            
            try {
                Zahtev zahtev = (Zahtev) receiver.primi();
                Odgovor odgovor = new Odgovor();
                
                switch (zahtev.getOperacija()) {
                    
                    case LOGIN:
                        
                        Instruktor i = (Instruktor) zahtev.getParametar();
                        i = Controller.getInstance().login(i);
                        odgovor.setOdgovor(i);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_POLAZNIKE:
                        
                        List<Polaznik> lista = Controller.getInstance().ucitajPolaznike();
                        odgovor.setOdgovor(lista);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case PRETRAZI_POLAZNIKA:
                        
                        Polaznik p = (Polaznik) zahtev.getParametar();
                        List<Polaznik> filtrirani = Controller.getInstance().pretraziPolaznika(p);
                        odgovor.setOdgovor(filtrirani);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case PRONADJI_NIVO_VESTINE:
                        
                        NivoVestine nv = (NivoVestine) zahtev.getParametar();
                        nv = Controller.getInstance().pronadjiNivoVestine(nv);
                        odgovor.setOdgovor(nv);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case KREIRAJ_POLAZNIKA:
                        
                        Polaznik p1 = (Polaznik) zahtev.getParametar();
                        boolean uspeh1 = Controller.getInstance().kreirajPolaznika(p1);
                        odgovor.setOdgovor(uspeh1);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UBACI_SERTIFIKAT:
                        
                        Sertifikat s = (Sertifikat) zahtev.getParametar();
                        boolean uspeh2 = Controller.getInstance().dodajSertifikat(s);
                        odgovor.setOdgovor(uspeh2);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case OBRISI_POLAZNIKA:
                        
                        Polaznik p2 = (Polaznik) zahtev.getParametar();
                        boolean uspeh3 = Controller.getInstance().obrisiPolaznika(p2);
                        odgovor.setOdgovor(uspeh3);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case PROMENI_POLAZNIKA:
                        
                        Polaznik p3 = (Polaznik) zahtev.getParametar();
                        boolean uspeh4 =Controller.getInstance().promeniPolaznika(p3);
                        odgovor.setOdgovor(uspeh4);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_INSTRUKTORE:
                        
                        List<Instruktor> instruktori = Controller.getInstance().ucitajInstruktore();
                        odgovor.setOdgovor(instruktori);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_PRIJAVE:
                        
                        List<PrijavaNaKurs> prijave = Controller.getInstance().ucitajPrijave();
                        odgovor.setOdgovor(prijave);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case PRETRAZI_PRIJAVU:
                        
                        KriterijumPretrage kp = (KriterijumPretrage) zahtev.getParametar();
                        List<PrijavaNaKurs> filtrirane = Controller.getInstance().pretraziPrijavu(kp);
                        odgovor.setOdgovor(filtrirane);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case UCITAJ_KURSEVE_PLESA:
                        
                        List<KursPlesa> kursevi = Controller.getInstance().ucitajKursevePlesa();
                        odgovor.setOdgovor(kursevi);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case KREIRAJ_PRIJAVU:
                        
                        PrijavaNaKurs pnk = (PrijavaNaKurs) zahtev.getParametar();
                        boolean uspeh5 = Controller.getInstance().kreirajPrijavu(pnk);
                        odgovor.setOdgovor(uspeh5);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    case PROMENI_PRIJAVU:
                        
                        PrijavaNaKurs zaIzmenu = (PrijavaNaKurs) zahtev.getParametar();
                        boolean uspeh6 = Controller.getInstance().promeniPrijavu(zaIzmenu);
                        odgovor.setOdgovor(uspeh6);
                        sender.posalji(odgovor);
                        
                        break;
                        
                    default:
                        System.out.println("Greska, ta operacija ne postoji");
                }
                
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public void prekiniNit() throws IOException {
        
        kraj = true;
        socket.close();
        interrupt();
        
    }
    
}
