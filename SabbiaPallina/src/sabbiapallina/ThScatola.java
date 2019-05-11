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
    //salva l'id della sabbia in cui spostare la sabbia persa
    int idTarget;

    //int lunghezzaScatola;
    //int larghezzaScatola;
    Sabbia sabbia;
    //Pallina pallina;

    int widthScatola;
    int heigthScatola;

    public ThScatola(DatiCondivisi dati, int idScatola, int wS, int hS) {
        this.dati = dati;
        this.idScatola = idScatola;

        this.heigthScatola = hS;
        this.widthScatola = wS;

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
                
                if (dati.giroscopio.getInclinazioneX() > 20) {
                    idTarget = idScatola + 1;  
                    if(idScatola==0 && sabbia.percentuale>0){
                        sabbia.versoDestra(idTarget);                        
                    }
                    
                } else if (dati.giroscopio.getInclinazioneX() < -20) {
                    idTarget = idScatola - 1;
                    if(idScatola==1 && sabbia.percentuale>0){
                        sabbia.versoSinistra(idTarget);                        
                    }
                }               
                
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
        sabbia.aggiornaSabbia(inclinazioneX, widthScatola);
        //aggiornamento pallina
    }

    public void visualizzazioneScatola() {
        sabbia.visualizzazioneSabbia(this.widthScatola);
        //visualizzazione pallina
    }

    public int getIdScatola() {
        return idScatola;
    }

    public Sabbia getSabbia() {
        return sabbia;
    }
    /*
    public Pallina getPallina() {
        return pallina;
    }*/

}
