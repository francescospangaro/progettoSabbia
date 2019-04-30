/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.awt.FlowLayout;
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
 *
 * @author Galimberti Francesco
 */
public class SwingGui {
    private DatiCondivisi ptrDati;
    private JFrame frame;

    public SwingGui(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
        
        frame = new JFrame("Controlli");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel controls= new JPanel();
        controls.setLayout(new FlowLayout());
        
        JSlider inclinazione = new JSlider();
        inclinazione.setValue(0);
        inclinazione.setMaximum(90);
        inclinazione.setMinimum(-90);
        JLabel valIncl = new JLabel(String.valueOf(inclinazione.getValue()));
        
        inclinazione.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent ce){
                int valore = inclinazione.getValue();
                ptrDati.setInclinazioneX(valore);
                valIncl.setText(String.valueOf(valore)+"° ");
            }
        });
        
        //crea un bottone stop, che ha il compito, tramite una variabile condivisa,
        //di fermare il gioco e chiudere la canvas
        JButton btnStop = new JButton("STOP");
        btnStop.addActionListener(new ActionListener() {
            
           @Override
           public void actionPerformed(ActionEvent e){
               ptrDati.setGioco(false);
           }
            
        });
        
        //crea un bottone start che avvia il canvas con il gioco
        //e aggiorna una variabile in dati condivisi
        JButton btnStart = new JButton("START");
        btnStop.addActionListener(new ActionListener() {
            
           @Override
           public void actionPerformed(ActionEvent e){
               ptrDati.setGioco(true);
           }
            
        });
        
        controls.add(inclinazione);
        controls.add(valIncl);
        controls.add(btnStart);
        controls.add(btnStop);
        
        frame.add(controls);
        frame.setSize(400, 80);
        
    }
    
    public void show(){
        this.frame.setVisible(true);
    }
    
    
}
