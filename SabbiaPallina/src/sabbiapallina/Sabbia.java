/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import processing.core.PImage;

/**
 * @author Galimberti Francesco
 * 
 * @version Prototipo1.0
 */
public class Sabbia {

    /**
    * @author Riccardi Francesco
    * 
    * @brief indica quanta sabbia è contenuta nella scatola
    */
    private int percentuale;
    /**
    * @author Riccardi Francesco
    * 
    * @brief permette di calacola la percentuale di sabbia persa in base all`inclinazione
    */
    private float diminuzione;

    /**
    * @author Riccardi Francesco
    * 
    * @brief contiene la larghezza della sabbia
    */
    private int widthSabbia;
    /**
    * @author Riccardi Francesco
    * 
    * @brief contiene l'altezza della sabbia
    */
    private int heightSabbia;

    /**
    * @author Riccardi Francesco
    * 
    * @param percentuale contiene la percentuale aggiornata di sabbia
    * @briefil costruttore che inizializza gli attributi, in particolare la percentuale
    * 
    */
    public Sabbia(int percentuale) {
        this.percentuale = percentuale;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
    }

    /**
    * @author Riccardi Francesco
    * 
    * @brief costruttore che inizializza gli attributi a 0
    */
    public Sabbia() {
        this.percentuale = 0;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
        this.diminuzione = 0;
    }

    /**
    * @author Riccardi Francesco
    * 
    * @param inclunazioneX contiene l'inclinazione dell'asse X
    * @brief metodo che gestisce il movimento della sabbia in base all`inclinazione
    */
    //
    public void aggiornaSabbia(int inclinazioneX) {
        if (inclinazioneX >= 15) {
            diminuzione = (float) (0.05 * inclinazioneX);
        } else if (inclinazioneX <= -15) {
            diminuzione = -1*((float) (0.05 * inclinazioneX));//moltiplico per -1 altrimenti diminuzione sara` negativa
        }
    }

    
    /**
    * @author Riccardi Francesco
    * 
    * @brief metodo che setta la percentuale di sabbia persa a 0
    */
    public void resetDiminuzione() {
        this.diminuzione = 0;
    }
    
    
    /**
    * @author Riccardi Francesco
    * 
    * @param wScatola contiene la larghezza della scatola
    * @brief metodo per aggiornare la larghezza della sabbia    larghezzaSabbia(x) : larghezzaScatola = percentualeSabbia : 100
    */
    public void visualizzazioneSabbia(int wScatola) {
        widthSabbia = (int) (percentuale * (((float) wScatola) / 100));
    }

    
    /**
    * @author Riccardi Francesco
    * 
    * @param percentuale contiene la percentuale di sabbia
    * @brief setta la percentuale
    */
    public void setPercentuale(int percentuale) {
        this.percentuale = percentuale;
    }

    
    /**
    * @author Riccardi Francesco
    * 
    * @param percentuale contiene la percentuale di sabbia
    * @brief aggiunge alla sabbia una certa percentuale
    */
    public void aggiungiSabbia(int percentuale) {
        this.percentuale += percentuale;
        //in caso la percentuale sia maggiore di 100(impossibile) viene riportata a 100
        if (this.percentuale > 100) {
            this.percentuale = 100;
        }
    }

    public int getPercentuale() {
        return percentuale;
    }

    public float getDiminuzione() {
        return diminuzione;
    }

    public int getWidthSabbia() {
        return widthSabbia;
    }

    public int getHeightSabbia() {
        return heightSabbia;
    }

    
    
    
}
