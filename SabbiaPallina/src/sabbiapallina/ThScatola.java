/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;

/**
 *
 * @author Galimberti Francesco
 */
public class ThScatola extends Thread {
    
    DatiCondivisi dati;
    int idScatola;

    //int lunghezzaScatola;
    //int larghezzaScatola;

    Sabbia sabbia;
    //Pallina pallina;
    
    public ThScatola(DatiCondivisi dati, int idScatola) {
        this.dati = dati;
        this.idScatola = idScatola;

        //this.lunghezzaScatola = lunghezza;
        //this.larghezzaScatola = larghezza;

        sabbia = dati.getSabbiaById(idScatola);
        //pallina = new Pallina();
    }

    public ThScatola() {
    }

    public void run() {
        try {
            while (dati.isRunning()) {

                Thread.sleep(10);
                simulazioneMovimento(dati.giroscopio.getInclinazioneX());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThScatola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simulazioneMovimento(int inclinazioneX) {
        aggiornaInformazioni(inclinazioneX);
        visualizzazioneScatola();
    }

    public void aggiornaInformazioni(int inclinazioneX) {
        sabbia.aggiornaSabbia(inclinazioneX,idScatola);
        //aggiornamento pallina
    }

    public void visualizzazioneScatola() {
        sabbia.visualizzazioneSabbia();
        //visualizzazione pallina
    }    

    public int getIdScatola() {
        return idScatola;
    }
    /*
    public int getLunghezza() {
        return lunghezzaScatola;
    }

    public int getLarghezza() {
        return larghezzaScatola;
    }
*/
    public Sabbia getSabbia() {
        return sabbia;
    }
    /*
    public Pallina getPallina() {
        return pallina;
    }*/

}
