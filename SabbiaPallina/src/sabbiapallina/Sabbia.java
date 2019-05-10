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

    //dimensione sabbia
    int widthSabbia;
    int heightSabbia;
    
    int posVerticeX;
    int posVerticeY;
    
    DatiCondivisi dati;

    public Sabbia(int percentuale) {
        this.percentuale = percentuale;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
    }
    
    public Sabbia() {
        this.percentuale = 0;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
    }
    
    public void setDati(DatiCondivisi dati){
        this.dati = dati;
    }
    
    public DatiCondivisi getDati(){
        return this.dati;
    }

    public void aggiornaSabbia(int inclinazioneX, int idScatola, int wScatola) {
        if(inclinazioneX > 0){
            this.dati.setPositivoX(true);
            posVerticeX=wScatola;
            
        }else{
            this.dati.setPositivoX(false);
            posVerticeX=0;
            
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
    public void visualizzazioneSabbia(int wS) {
        //se la sabbia esce dal contenitore
        widthSabbia = (wS * percentuale) / 100;
    }

    void setScreen(int xScatola, int yScatola, int wS) {
        posVerticeX = xScatola;
        posVerticeY = yScatola;
        
        widthSabbia = (int) (wS * percentuale) / 100;
    }

    public int getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(int percentuale) {
        this.percentuale = percentuale;
    }

    
}
