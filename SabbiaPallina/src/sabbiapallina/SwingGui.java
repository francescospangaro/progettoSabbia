/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

    /**
    * @author Riccardi Francesco
    * 
    * @param ptrDati contiene i dati condivisi aggiornati
    * @brief crea un pannello che contiene:
    * un pannello su cui aggiungere bottoni
    * uno slider che regola l'inclinazione delle scatole
    * una label per visualizzare il valore dell`inclinazione
    * un bottone "STOP" che quando viene premuto ferma e chiude il gioco
    * un bottone "Aumenta" che quando viene premuto incrementa di 1 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
    * un bottone "Decrementa" che quando viene premuto decrementa di 1 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
    * un bottone "Reset" che quando viene premuto riporta a 0 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
    */
    public SwingGui(DatiCondivisi ptrDati) {
        this.dati = ptrDati;
        
        frame = new JFrame("Controlli");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel controls = new JPanel();  //aggiunge un pannello su cui aggiungere bottoni
        controls.setLayout(new FlowLayout());

        JSlider inclinazione = new JSlider();  //slider che regola l'inclinazione delle scatole
        inclinazione.setValue(0);
        inclinazione.setMinimum(-90);
        inclinazione.setMaximum(90);
        JLabel valInclinazione = new JLabel(String.valueOf(inclinazione.getValue())+"° "); //label per visualizzare il valore dell`inclinazione
        
        inclinazione.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                int val = inclinazione.getValue();                
                dati.getGiroscopio().scriviInclinazioneX(val);
                valInclinazione.setText(String.valueOf(val)+"° ");
            }
        });
        
        /**
         *
         * Creazione del bottone "Aumenta" che quando viene premuto incrementa di 1 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
         *
         */
        JButton AddValue = new JButton("Aumenta");
        AddValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inclinazione.setValue((int) (dati.getGiroscopio().getInclinazioneX() + 1));
            }
        });

        /**
         *
         * Creazione del bottone "Decrementa" che quando viene premuto decrementa di 1 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
         *
         */
        JButton DecValue = new JButton("Decrementa");
        DecValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inclinazione.setValue((int) (dati.getGiroscopio().getInclinazioneX() - 1));
            }
        });

        /**
         *
         * Creazione del bottone "Reset" che quando viene premuto riporta a 0 il valore dell'inclinazione sull'asse delle x e setta con il nuovo valore lo slider.
         *
         */
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inclinazione.setValue(0);
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
        
        
        controls.add(DecValue); 
        controls.add(inclinazione);
        controls.add(valInclinazione);
        controls.add(AddValue);       
        controls.add(reset);
        controls.add(stop);
        
        frame.add(controls);
        frame.setSize(500, 120);
    }
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief il metodo permette di rendere visibile lo SwingGui
    */
    public void show() {
        frame.setVisible(true);
    }

}
