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
    int idDest;
    
    Sabbia sabbia;
    //Pallina pallina;

    int widthScatola;
    int heigthScatola;

    public ThScatola(int percSabbia, DatiCondivisi dati, int idScatola, int wS, int hS) {
        this.dati = dati;
        this.idScatola = idScatola;

        this.heigthScatola = hS;
        this.widthScatola = wS;

        sabbia = dati.getSabbiaById(idScatola);
        sabbia.setPercentuale(percSabbia);
        sabbia.visualizzazioneSabbia(widthScatola);

        //pallina = new Pallina();        
    }

    public void run() {
        while (dati.isRunning()) {

            sabbia.aggiornaSabbia(dati.giroscopio.getInclinazioneX());

            if (dati.giroscopio.getInclinazioneX() >= 15) {
                idDest = idScatola + 1;
                System.out.println("Th"+idScatola+"--> Dest"+idDest);
                versoDestra(sabbia.diminuzione);
            }

            if (dati.giroscopio.getInclinazioneX() <= -15) {
                idDest = idScatola - 1;
                System.out.println("Th"+idScatola+"--> Dest"+idDest);
                versoSinistra(sabbia.diminuzione);
            }

            sabbia.setDiminuzione(0);
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThScatola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public void versoDestra(float sm) {
        if (idDest != dati.numScatoleColonne) {
            try {
                if (((sm > 0) && (sabbia.percentuale == 100) && (dati.sabbie[idScatola - 1].percentuale == 0)) || ((sm > 0) && (sabbia.percentuale > 0) && (dati.sabbie[idDest].percentuale < 100))) {
                    procedi(sm);
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                if ((sm > 0) && (sabbia.percentuale == 100)) {
                    procedi(sm);
                }
            }
        }
    }

    public void versoSinistra(float sm) {
        if (idDest != -1) {
            try {
                if (((sm > 0) && (sabbia.percentuale == 100) && (dati.sabbie[idScatola + 1].percentuale == 0)) || ((sm > 0) && (sabbia.percentuale > 0) && (dati.sabbie[idDest].percentuale < 100))) {
                    procedi(sm);
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                if ((sm > 0) && (sabbia.percentuale == 100)) {
                    procedi(sm);
                }
            }
        }
    }

    private void procedi(float sm) {
        int perc = sabbia.percentuale - (int) sm;
        if (perc < 0) {
            sabbia.setPercentuale(0);
            sabbia.visualizzazioneSabbia(widthScatola);
            dati.sabbie[idDest].setPercentuale(100);
            dati.sabbie[idDest].visualizzazioneSabbia(widthScatola);

        } else {
            sabbia.setPercentuale(perc);
            sabbia.visualizzazioneSabbia(widthScatola);
            dati.sabbie[idDest].aggiungiSabbia((int) sm);
            dati.sabbie[idDest].visualizzazioneSabbia(widthScatola);
        }
    }
}
