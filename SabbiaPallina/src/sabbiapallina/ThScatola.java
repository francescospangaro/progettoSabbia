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

    //attributo che permette di avere accesso agli elementi condivisi
    DatiCondivisi dati;

    //attributo che salva l`id assegnato alla scatola
    int idScatola;
    //salva l'id della sabbia(che corrisponde a quello della scatola in cui e` contenuta) in cui spostare la sabbia persa
    int idDest;

    //sabbia contenuta nella scatola
    Sabbia sabbia;
    //Pallina pallina;

    //dimensioni della scatola
    int widthScatola;
    int heigthScatola;

    //costruttore che inizializza gli attributi del Thread
    public ThScatola(int percSabbia, DatiCondivisi dati, int idScatola, int wS, int hS) {
        this.dati = dati;
        this.idScatola = idScatola;

        this.heigthScatola = hS;
        this.widthScatola = wS;

        //viene inizializzata la sabbia, ogni Thread prende la sabbia corrispondente dai datiCondivisi
        sabbia = dati.getSabbiaById(idScatola);
        //viene settata la perc di sabbia contenuta nella scatola
        sabbia.setPercentuale(percSabbia);
        //richiama il metodo per aggiornare la larghezza della sabbia 
        sabbia.visualizzazioneSabbia(widthScatola);

        //pallina = new Pallina();        
    }

    public void run() {
        try {
            while (dati.isRunning()) {

                //viene richiamato il metodo per aggiornare la sabbia "persa" in base all`inclinazioneX
                sabbia.aggiornaSabbia(dati.giroscopio.getInclinazioneX());

                //se inclinazione positiva(verso dx)
                if (dati.giroscopio.getInclinazioneX() >= 15) {
                    //la scatola nel quale devo spostare la sabbia e` quella successiva
                    idDest = idScatola + 1;
                    //System.out.println("Th"+idScatola+"--> Dest"+idDest);         //->output di controllo

                    //viene richiamato il metodo per gestire lo spostamento della sabbia verso dx
                    versoDestra(sabbia.diminuzione);
                }

                //se inclinazione negativa (verso sx)
                if (dati.giroscopio.getInclinazioneX() <= -15) {
                    //la scatola in cui spostare la sabbia e` quella precedente
                    idDest = idScatola - 1;
                    //System.out.println("Th"+idScatola+"--> Dest"+idDest);         //->output di controllo

                    //viene richiamato il metodo per gestire lo spostamento della sabbia verso dx
                    versoSinistra(sabbia.diminuzione);
                }

                //dopo avere gestito il movimento resetto la sabbia persa
                sabbia.resetDiminuzione();

                //attendo 10ms
                Thread.sleep(10);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(ThScatola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //gestione spostamento verso dx(inclinazione positiva)
    private void versoDestra(float dim) {
        //se la scatola in cui devo spostare la sabbia persa esiste(idDest non puo essere uguale al numero delle colonne avendo id che va da 0 a numColonne-1)
        if (idDest != dati.numScatoleColonne) {
            try {
                //se ho della sabbia persa, la scatola e` piena di sabbia e la scatola precedente non ha piu sabbia, oppure, ho della sabbia persa, la scatola ha della sabbia e il destinatario non e` ancora pieno
                if (((dim > 0) && (sabbia.percentuale == 100) && (dati.sabbie[idScatola - 1].percentuale == 0)) || ((dim > 0) && (sabbia.percentuale > 0) && (dati.sabbie[idDest].percentuale < 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //se ho della sabbia persa e la scatola e` piena di sabbia
                if ((dim > 0) && (sabbia.percentuale == 100)) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            }
        }
    }
    
    //gestione spostamento verso sx(inclinazione negativa)
    private void versoSinistra(float dim) {
        //se la scatola in cui devo spostare la sabbia persa esiste(idDest non puo essere uguale a -1 avendo id che va da 0 a numColonne-1)
        if (idDest != -1) {
            try {
                //se ho della sabbia persa, la scatola e` piena di sabbia e la scatola successiva non ha piu sabbia, oppure, ho della sabbia persa, la scatola ha della sabbia e il destinatario non e` ancora pieno
                if (((dim > 0) && (sabbia.percentuale == 100) && (dati.sabbie[idScatola + 1].percentuale == 0)) || ((dim > 0) && (sabbia.percentuale > 0) && (dati.sabbie[idDest].percentuale < 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            } catch (ArrayIndexOutOfBoundsException ex) {       
                //se ho della sabbia persa e la scatola e` piena di sabbia
                if ((dim > 0) && (sabbia.percentuale == 100)) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            }
        }
    }

    //metodo che permette di continare a gestire lo spostamento della sabbia, in base alla percentuale di sabbia persa
    private void procedi(float sm) { 
        int perc = sabbia.percentuale - (int) sm;//nuova percentuale
        //se si perde piu sabbia di quella che si ha
        if (perc <= 0) {
            sabbia.setPercentuale(0);//la scatola e` vuota
            sabbia.visualizzazioneSabbia(widthScatola);                         // aggiornamento larghezza sabbia
            dati.sabbie[idDest].setPercentuale(100);// il destinatario sara pieno di sabbia
            dati.sabbie[idDest].visualizzazioneSabbia(widthScatola);            // aggiornamento larghezza sabbia destinatario

        //altrimenti
        } else {
            sabbia.setPercentuale(perc);//cambia la sabbia contenuta nella scatola con la nuova percentuale
            sabbia.visualizzazioneSabbia(widthScatola);                         // aggiornamento larghezza sabbia
            dati.sabbie[idDest].aggiungiSabbia((int) sm);//aggiungo la percentuale di sabbia persa al destinatario
            dati.sabbie[idDest].visualizzazioneSabbia(widthScatola);            // aggiornamento larghezza sabbia destinatario
        }
    }
}
