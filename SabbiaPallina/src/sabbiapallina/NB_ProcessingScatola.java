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

    /**
     * @author Galimberti_Francesco
     *
     * Oggetto di tipo DatiCondivisi per accedere alle elementi condivisi ai Thread
     */
    static DatiCondivisi dati;
    //vettore dei ThreadScatola
    static ThScatola box[];
    
    //larghezza della canvas(schermo di gioco)
    private static int WScreen;
    //altezza della canvas(schermo di gioco)
    private static int HScreen;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numScatoleRighe = 1;
        int numScatoleColonne = 2;
        
        //creazione dei dati condivisi
        dati = new DatiCondivisi(numScatoleColonne);

        //creazione dei ThreadScatola
        box = new ThScatola[numScatoleColonne];
        for (int i = 0; i < dati.numScatoleColonne; i++) {
            if (i == 0) {              //percentuale sabbia, datiCondivisi,larghezza e altezza scatola
                box[i] = new ThScatola(100, dati, i, 200, 200);
            } else {
                box[i] = new ThScatola(0, dati, i, 200, 200);
            }
        }

        //le dimensioni della canvas dipendono dal numero di righe e colonne
        WScreen = (numScatoleColonne * 200);
        HScreen = (numScatoleRighe * 200);

        PApplet.main(new String[]{"sabbiapallina.NB_ProcessingScatola"});
        SwingGui swingGui = new SwingGui(dati);
        swingGui.show();
    }

    //metodo che setta le dimensioni della canvas e avvia i Thread
    public void settings() {
        size(WScreen, HScreen);
        
        for (int i = 0; i < dati.numScatoleColonne; i++) {
            box[i].start();
        }
    }
    
    //metodo che inizializza la canvas
    public void setup() {
        noStroke();
        frameRate(60);
    }

    //metodo che gestisce la grafica
    public void draw() {
        if (!dati.isRunning()) {
            exit();
        }
        background(0, 0, 0);
        for (int i = 0; i < dati.numScatoleColonne; i++) {
            displaySabbia(box[i].sabbia, i);
        }
    }
    
    //metodo che permette di disegnare lo spostamneto della sabbia in base all`inclinazione
    private void displaySabbia(Sabbia s, int id) {
        noStroke();
        PImage b;
        b = loadImage("image/sabbia.png");        
        
        //lo spostamento serve a cambiare, in base alla scatola, i punti in cui disegnare la sabbia
        int spostamento = id*200;
        //se inclinazione positiva(verso dx)
        if (dati.giroscopio.getInclinazioneX() >= 0) {
            //la sabbia deve essere di segnata da dx verso sx, quindi dalla fine della scatola verso l`inizio in base alla larghezza della sabbia contenuta nella scatola
            for (int x = 200 + spostamento; x > (200 + spostamento - s.widthSabbia); x--) {
                image(b, x, 0);
            }
        //se inclinazione negativa(verso sx)
        } else {
        //la sabbia deve essere di segnata da sx verso dx, quindi dall`inizio della scatola verso la fine in base alla larghezza della sabbia contenuta nella scatola
            for (int x = spostamento; x < (spostamento + s.widthSabbia); x++) {
                image(b, x, 0);
            }
        }
    }  
    

}
