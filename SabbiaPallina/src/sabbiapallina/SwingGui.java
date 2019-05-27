/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * @author Galimberti Francesco
 * 
 * @version Prototipo1.0
 * @brief la classe collabora con la classe DatiCondivisi
 */
public class SwingGui {
    /**
    * @author Riccardi Francesco
    * 
    * @brief crea i daticondivisi
    */
    private DatiCondivisi dati; 
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief crea un frame che conterrà i componenti
    */
    private JFrame frame;
    
    private int valoreX;
    
    private int valoreY;

    private boolean controllo;
    /**
    * @author Riccardi Francesco
    * @brief crea un pannello che contiene:
    * un pannello su cui aggiungere bottoni
    * uno slider che regola l'inclinazione delle scatole
    * una label per visualizzare il valore dell`inclinazione
    * un bottone "STOP" che quando viene premuto ferma e chiude il gioco
    * un bottone "Aumenta" che quando viene premuto incrementa di 1 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
    * un bottone "Decrementa" che quando viene premuto decrementa di 1 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
    * un bottone "Reset" che quando viene premuto riporta a 0 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
    */
    public DatiCondivisi getDati(){
        return dati;
    }     
    
    public SwingGui(DatiCondivisi ptrdati) {
        
        /**
         * controllo viene settato a false,
         * variabile utilizzata in caso gli slider vengano modificati prima
         * della conferma del numero delle scatole
         *
         */
        this.controllo = false;
        this.dati = ptrdati;
        frame = new JFrame("Controlli");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel controls = new JPanel();  //aggiunge un pannello su cui aggiungere bottoni
        controls.setLayout(new FlowLayout());

        JSlider inclinazionex = new JSlider();  //slider che regola l'inclinazione delle scatole
        inclinazionex.setValue(0);
        inclinazionex.setMinimum(-90);
        inclinazionex.setMaximum(90);
        JLabel valInclinazionex = new JLabel(String.valueOf(inclinazionex.getValue())+"° "); //label per visualizzare il valore dell`inclinazione
        
        
        JSlider inclinazioney = new JSlider();  //slider che regola l'inclinazione delle scatole
        inclinazioney.setValue(0);
        inclinazioney.setMinimum(-90);
        inclinazioney.setMaximum(90);
        JLabel valInclinazioney = new JLabel(String.valueOf(inclinazioney.getValue())+"° "); //label per visualizzare il valore dell`inclinazioney
        
        inclinazionex.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                if(controllo){
                   valoreY = 0;
                    inclinazioney.setValue(0);
                    valInclinazioney.setText(String.valueOf(valoreY)+"° ");                
                
                  valoreX = (int) inclinazionex.getValue();                   
                  valInclinazionex.setText(String.valueOf(valoreX)+"° ");
                }else{
                }
            }
        });
        
        inclinazioney.addChangeListener(new ChangeListener() {
            @Override 
            public void stateChanged(ChangeEvent ce) {
                if(controllo){
                    valoreX = 0;
                    inclinazionex.setValue(0);
                    valInclinazionex.setText(String.valueOf(valoreX)+"° ");
                
                    valoreY = (int) inclinazioney.getValue();    
                    valInclinazioney.setText(String.valueOf(valoreY)+"° ");
                }else{
                }
            }
        });
        
        /**
         *
         * Creazione del bottone "Aumenta" che quando viene premuto incrementa di 1 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
         *
         */
        JButton AddValuex = new JButton("Aumenta X");
        AddValuex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inclinazionex.setValue((int) (valoreX + 1));
            }
        });

        JButton AddValuey = new JButton("Aumenta Y");
        AddValuey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inclinazioney.setValue((int) (valoreY + 1));
            }
        });
        /**
         *
         * Creazione del bottone "Decrementa" che quando viene premuto decrementa di 1 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
         *
         */
        JButton DecValuex = new JButton("Decrementa X");
        DecValuex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inclinazionex.setValue((int) (valoreX - 1));
            }
        });
        
        JButton DecValuey = new JButton("Decrementa Y");
        DecValuey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inclinazioney.setValue((int) (valoreY - 1));
            }
        });

        /**
         *
         * Creazione del bottone "Reset" che quando viene premuto riporta a 0 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
         *
         */
        JButton reset = new JButton("Reset Inclinazione");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valoreX = 0;
                valoreY = 0;
                inclinazionex.setValue((int) (valoreX));
                inclinazioney.setValue((int) (valoreY));
            }
        });
        
        
        /**
         *
         * Creazione del bottone "STOP" che quando viene premuto ferma e chiude il gioco
         *
         */
        JButton stop = new JButton("STOP");
        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               dati.stop();
            }
        });
        /**
         * 
         * Inserisce il numero di scatole sull'asse delle x
         * 
         */
        JLabel colonneLabel = new JLabel("colonne: ");
        TextField colonne = new TextField("2");
        /**
         * 
         * Inserisce il numero di scatole sull'asse delle y
         * 
         */
        JLabel righeLabel = new JLabel("righe: ");
        TextField righe = new TextField("2");
        /** 
         * 
         * Conferma il numero delle scatole inserito dall'utente
         * 
         */
        JButton conf = new JButton("CONFERMA SCATOLE");
        conf.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {   
               dati.set(Integer.parseInt(righe.getText()),Integer.parseInt(colonne.getText()));
               controllo = true;
               dati.signalsincroGuiMain();
            }
        });
        
        controls.add(colonneLabel);
        controls.add(colonne);
        controls.add(righeLabel);
        controls.add(righe);
        controls.add(conf);
        
        controls.add(DecValuex); 
        controls.add(inclinazionex);
        controls.add(valInclinazionex);
        controls.add(AddValuex); 
        
        controls.add(DecValuey); 
        controls.add(inclinazioney);
        controls.add(valInclinazioney);
        controls.add(AddValuey); 
        
        controls.add(stop);      
        controls.add(reset);
        
        frame.add(controls);
        frame.setSize(462, 200);
    }
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief il metodo permette di rendere visibile lo SwingGui
    */
    public void show() {
        frame.setVisible(true);
    }

    public int getValoreX() {
        return valoreX;
    }

    public int getValoreY() {
        return valoreY;
    }
    
    

    
    
}
