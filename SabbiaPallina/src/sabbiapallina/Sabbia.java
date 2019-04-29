/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

/**
 *
 * @author Galimberti Francesco
 */
public class Sabbia{

    //indica quanta sabbia è contenuta nella scatola
    int percentuale;
    //permette di calcolare la sabbia che sta uscendo dalla scatola
    int diminuzione;

    //indica la posizione della sabbia sulle ascisse
    float xpos;
    float ypos;
    //indica la velocita della sabbia sulle ascisse
    double xspeed;
    //indica se la sabbia si muove verso destra o sinistra
    int xdirection;

    //dimesione schermo
    int widthScreen;
    int heightScreen;

    //dimensione sabbia
    int widthSabbia;
    int heightSabbia;

    public Sabbia(int percentuale, float xpos,float ypos, double xspeed, int xdirection, int widthScreen, int heightScreen, int widthSabbia, int heightSabbia) {
        this.percentuale = percentuale;
        this.diminuzione = 0;
        this.xpos = xpos;
        this.ypos = ypos;
        this.xspeed = xspeed;
        this.xdirection = xdirection;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        this.widthSabbia = widthSabbia;
        this.heightSabbia = heightSabbia;
    }

    public Sabbia(int percentuale) {
        this.percentuale = percentuale;
        this.diminuzione = 0;
        this.xpos = 0;
        this.ypos = 0;
        this.xspeed = 0;
        this.xdirection = 0;
        this.widthScreen = 0;
        this.heightScreen = 0;
        this.heightSabbia = 0;
        this.widthSabbia = 0;
    }

    public void aggiornaSabbia(int inclinazioneX) {

        switch (xdirection) {
            //ero in equilibrio
            case 0:
                //guardo il valore dell'inclinazione
                if (inclinazioneX==0){
                    xdirection=0;
                }else if(inclinazioneX > 0) {
                    xdirection=1;
                }else {
                    xdirection=-1;
                }                
                xspeed = 0.2 * inclinazioneX;
                break;
                
            //si stava muovendo verso destra
            case 1:
                if (inclinazioneX==0){
                    xdirection=0;
                    decVel();
                }else if (inclinazioneX > 0) {
                    xdirection=1;
                    incVel();
                } else {
                    xdirection=-1;
                    decVel();
                } 
                break;
                
            //si stava muovendo verso sinistra
            case -1:
                if (inclinazioneX==0){
                    xdirection=0;
                    incVel();
                }else if (inclinazioneX > 0) {
                    xdirection=1;
                    decVel();
                } else {
                    xdirection=-1;
                    incVel();
                } 
                break;
        }
        /*
        if (inclinazioneX == 0) {
            xdirection = 0;//fermo
        } else if (inclinazioneX > 0) {
            xdirection = 1;//verso destra
        } else {
            xdirection = -1;//verso sinistra
        }*/        
    }

    public void incVel() {
        xspeed += 0.2;
        //yspeed += 0.2;
    }

    public void decVel() {
        xspeed -= 0.2;
        if (xspeed < 0) {
            xspeed = 0;
            xdirection=0;
        }/*
        yspeed -= 0.2;
        if (yspeed < 0) {
            yspeed = 0;
        }*/
    }

    public void visualizzazioneSabbia() {
        xpos = xpos + (float) (xspeed * xdirection);

        //se la sabbia esce dal contenitore
        if (xpos > widthScreen - widthSabbia || xpos < widthSabbia) {
            //cambia la percentuale di sabbia nella scatola
            diminuzione = (int) ((diminuzione + 1) * xspeed);
            percentuale = percentuale - diminuzione;

            widthSabbia = (widthScreen * percentuale) / 100;
        }
    }

    void setScreen(int width, int height) {
        widthScreen = width / 2;
        heightScreen = height;
        xpos = widthScreen / 2;
        ypos = heightScreen;
        
        widthSabbia = (widthScreen * percentuale) / 100;
    }

}
