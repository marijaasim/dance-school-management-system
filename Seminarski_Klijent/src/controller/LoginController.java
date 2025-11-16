/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koodrinator.Koordinator;
import model.Instruktor;

/**
 *
 * @author Korisnik
 */
public class LoginController {
    
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        lf.loginAddActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String email = lf.getjTextFieldEmail().getText().trim();
                String sifra = String.valueOf(lf.getjPasswordField1().getPassword());
                
                Instruktor i = new Instruktor(-1, null, null, email, sifra);
                Komunikacija.getInstance().konekcija();
                i = Komunikacija.getInstance().login(i);
                
                if (i.getId() == -1) {
                    
                    JOptionPane.showMessageDialog(lf, "Email i sifra nisu ispravni.");
                    JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni.");
            
                } else {
                    
                    Koordinator.getInstance().setUlogvani(i);
                    JOptionPane.showMessageDialog(lf, "Email i sifra su ispravni.");
                    lf.dispose();
                    
                    try {
                        Koordinator.getInstance().otvoriGlavnuFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni.");
                    }
                    
                }
                
            }
            
        });
        
    }

    public void prikaziFormu() {
        
        lf.setVisible(true);
        lf.setTitle("Login forma");
        
    }
    
    
    
}
