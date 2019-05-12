/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import processing.core.PImage;

/**
 *
 * @author Galimberti Francesco
 */
public class Sabbia {

    //indica quanta sabbia è contenuta nella scatola
    int percentuale;
    float diminuzione;

    //dimensione sabbia
    int widthSabbia;
    int heightSabbia;

    int posVerticeX;
    int posVerticeY;

    public Sabbia(int percentuale) {
        this.percentuale = percentuale;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
    }

    public Sabbia() {
        this.percentuale = 0;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
        this.diminuzione = 0;
    }

    public void aggiornaSabbia(int inclinazioneX) {
        if (inclinazioneX >= 15) {
            diminuzione = (float) (0.05 * inclinazioneX);
        } else if (inclinazioneX <= -15) {
            diminuzione = -1*((float) (0.05 * inclinazioneX));
        }
    }

    public void setDiminuzione(float diminuzione) {
        this.diminuzione = diminuzione;
    }
    
    public void visualizzazioneSabbia(int wScatola) {
        widthSabbia = (int) (percentuale * (((float) wScatola) / 100));
    }

    public int getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(int percentuale) {
        this.percentuale = percentuale;
    }

    public void aggiungiSabbia(int percentuale) {
        this.percentuale += percentuale;
        if (this.percentuale > 100) {
            this.percentuale = 100;
        }
    }

    public void togliSabbia(int percentuale) {
        this.percentuale -= percentuale;
        if (this.percentuale < 0) {
            this.percentuale = 0;
        }
    }

}
