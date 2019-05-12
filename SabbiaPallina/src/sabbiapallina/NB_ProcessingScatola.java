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

    static DatiCondivisi dati;
    static ThScatola box[];
    private static int WScreen;
    private static int HScreen;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numScatoleRighe = 1;
        int numScatoleColonne = 2;

        dati = new DatiCondivisi(numScatoleColonne);

        //creazione dei ThreadScatola
        box = new ThScatola[numScatoleColonne];
        for (int i = 0; i < dati.numScatoleColonne; i++) {
            if (i == 0) {
                box[i] = new ThScatola(100, dati, i, 200, 200);
            } else {
                box[i] = new ThScatola(0, dati, i, 200, 200);
            }
        }

        WScreen = (numScatoleColonne * 200);
        HScreen = (numScatoleRighe * 200);

        PApplet.main(new String[]{"sabbiapallina.NB_ProcessingScatola"});
        SwingGui swingGui = new SwingGui(dati);
        swingGui.show();
    }

    public void settings() {
        size(WScreen, HScreen);
        
        for (int i = 0; i < dati.numScatoleColonne; i++) {
            box[i].start();
        }
    }

    public void setup() {
        noStroke();
        frameRate(60);
    }

    public void draw() {
        if (!dati.isRunning()) {
            exit();
        }
        background(125, 100, 100);
        for (int i = 0; i < dati.numScatoleColonne; i++) {
            displaySabbia(box[i].sabbia, i);
        }
    }
    
    void displaySabbia(Sabbia s, int id) {
        noStroke();
        PImage b;
        b = loadImage("image/sabbia.png");        
        
        int spostamento = id*200;
        if (dati.giroscopio.getInclinazioneX() >= 0) {
            for (int x = 200 + spostamento; x > (200 + spostamento - s.widthSabbia); x--) {
                image(b, x, 0);
            }
        } else {
            for (int x = spostamento; x < (spostamento + s.widthSabbia); x++) {
                image(b, x, 0);
            }
        }
    }  
    

}
