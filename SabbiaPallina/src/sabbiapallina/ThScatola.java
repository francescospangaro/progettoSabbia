/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import processing.core.PApplet;

/**
 *
 * @author Galimberti Francesco
 */
public class ThScatola extends Thread {
    
    DatiCondivisi dati;
    int idScatola;

    int lunghezza;
    int larghezza;

    Sabbia sabbia;

    //Pallina pallina;
    public ThScatola(DatiCondivisi dati, int idScatola, int lunghezza, int larghezza) {
        this.dati = dati;
        this.idScatola = idScatola;

        this.lunghezza = lunghezza;
        this.larghezza = larghezza;

        sabbia = dati.getSabbiaById(idScatola);
        //pallina = new Pallina();
    }

    public ThScatola() {
    }

    public void run() {
        try {
            while (dati.isRunning()) {

                Thread.sleep(10);
                simulazioneMovimento();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThScatola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aggiornaInformazioni() {
        sabbia.aggiornaSabbia(dati.getInclinazioneX());
        //aggiornamento pallina
    }

    public void visualizzazioneScatola() {
        sabbia.visualizzazioneSabbia();
        //visualizzazione pallina
    }

    public void simulazioneMovimento() {
        aggiornaInformazioni();
        visualizzazioneScatola();
    }

    public int getIdScatola() {
        return idScatola;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public Sabbia getSabbia() {
        return sabbia;
    }
    /*
    public Pallina getPallina() {
        return pallina;
    }*/

}
