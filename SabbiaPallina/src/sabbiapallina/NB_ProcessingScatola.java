/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Galimberti Francesco
 */
public class NB_ProcessingScatola extends PApplet {

    static DatiCondivisi dati; // dati condivisi
    /*static ThScatola scatolaSx;
    static ThScatola scatolaDx;*/
    static ThScatola box[];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numScatole = 2;
        dati = new DatiCondivisi(numScatole);
        
        box=new ThScatola[numScatole];
        /*scatolaSx = new ThScatola(dati, 0);
        scatolaDx = new ThScatola(dati, 1);*/

        PApplet.main(new String[]{"sabbiapallina.NB_ProcessingScatola"});

        SwingGui swingGui = new SwingGui(dati);
        swingGui.show();
    }

    public void settings() {
        size(1000, 1000);
        dati.setScreen(width, height);

        for(int i=0;i<dati.numScatole;i++){
            box[i]=new ThScatola(dati,i);
        }
        
        box[0].sabbia.setPercentuale(100);//sx
        box[1].sabbia.setPercentuale(0);//dx
        /*scatolaSx.sabbia.setPercentuale(100);
        scatolaDx.sabbia.setPercentuale(0);*/
        box[0].sabbia.setDati(dati);//sx
        box[1].sabbia.setDati(dati);//dx
        /*do alla sabbia presente in entrambe 
        le scatole gli stessi dati condivisi
        */
        for(int i=0;i<dati.numScatole;i++){
            box[i].start();
        }
    }

    public void setup() {
        noStroke();
        frameRate(30);
        //ellipseMode(RADIUS);
    }

    public void draw() {
        if (!dati.isRunning()) {
            exit();
        }
        background(0, 0, 0);
        for(int i=0;i<dati.numScatole;i++){
            displaySabbia(box[i].sabbia,i);
        }
    }

    void displaySabbia(Sabbia s, int id) {
        noStroke();
        PImage b;
        b = loadImage("image/sabbia.png");
        
        if ((id == 0)&&((box[0].sabbia.dati.isPositivoX()))) {
            for (int x = 0; x < (s.widthSabbia); x++){
                image(b, x, 0);
            }
        }if ((id != 0)&&((box[0].sabbia.dati.isPositivoX()))){
            for (int x = width/2; x < (s.widthSabbia)+ width/2; x++) {
                image(b, x, 0);
            }
        }if ((id == 0)&&(!(box[0].sabbia.dati.isPositivoX()))){
            for (int x = width/2; x < (s.widthSabbia)+width; x--){
                image(b, x, 0);
            }
        }if ((id != 0)&&(!(box[0].sabbia.dati.isPositivoX()))){
            for (int x = 0; x < (s.widthSabbia); x++) {
                image(b, x, 0);
            }
        }

    }

}
