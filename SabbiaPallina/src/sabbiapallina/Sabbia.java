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
public class Sabbia{

    //indica quanta sabbia è contenuta nella scatola
    int percentuale;
    //permette di calcolare la sabbia che sta uscendo dalla scatola
    int diminuzione;

    //dimesione schermo
    int widthScreen;
    int heightScreen;

    //dimensione sabbia
    int widthSabbia;
    int heightSabbia;
    DatiCondivisi dati;

    public Sabbia(int percentuale,int xdirection, int widthScreen, int heightScreen, int widthSabbia, int heightSabbia) {
        this.percentuale = percentuale;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        this.widthSabbia = widthSabbia;
        this.heightSabbia = heightSabbia;
    }

    public Sabbia(int percentuale) {
        this.percentuale = percentuale;
        this.widthScreen = 0;
        this.heightScreen = 0;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
    }
    
    public Sabbia() {
        this.percentuale = 0;
        this.widthScreen = 0;
        this.heightScreen = 0;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
    }
    
    public void setDati(DatiCondivisi dati){
        this.dati = dati;
    }
    
    public DatiCondivisi getDati(){
        return this.dati;
    }

    public void aggiornaSabbia(int inclinazioneX, int idScatola) {
        if(inclinazioneX > 0){
            this.dati.setPositivoX(true);
        }else{
            this.dati.setPositivoX(false);
        }
        /*Setta true in caso il piano si stia inclinando  
        nel primo quadrante, false se nel secondo*/
            if (inclinazioneX > 20) {
                switch(idScatola){
                    case 0:
                        percentuale = percentuale - (int) (0.05 * inclinazioneX);
                        break;
                    case 1:
                        percentuale = percentuale + (int) (0.05 * inclinazioneX);
                        break;
                }
                
            }else if(inclinazioneX < -20) {
                switch(idScatola){
                    case 0:
                        percentuale = percentuale + (int) (0.05 * inclinazioneX);
                        break;
                    case 1:
                        percentuale = percentuale - (int) (0.05 * inclinazioneX);
                        break;
                }
            }                    
        }
    
    //draw
    public void visualizzazioneSabbia() {
        //se la sabbia esce dal contenitore
        widthSabbia = (widthScreen * percentuale) / 100;
    }

    void setScreen(int width, int height) {
        widthScreen = width / 2;
        heightScreen = height;
        
        widthSabbia = (int) (widthScreen * percentuale) / 100;
    }

    public int getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(int percentuale) {
        this.percentuale = percentuale;
    }

    
}
