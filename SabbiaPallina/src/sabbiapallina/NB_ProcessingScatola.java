/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Galimberti Francesco
 *
 * @version Prototipo1.0
 * @brief La classe collabora con le classi DatiCondivisi, SwingGui e ThScatola
 */
public class NB_ProcessingScatola extends PApplet {

    /**
     * @author Riccardi Francesco
     *
     * @brief Oggetto di tipo DatiCondivisi per accedere alle elementi condivisi
     * ai Thread
     */
    static DatiCondivisi dati;
    /**
     * @author Riccardi Francesco
     *
     * @brief vettore dei ThreadScatola
     */
    static ThScatola box[];
    
    static ThPallina thPallina;

    /**
     * @author Riccardi Francesco
     *
     * @brief contiene la larghezza della canvas(schermo di gioco)
     */
    //
    private static int WScreen;

    /**
     * @author Riccardi Francesco
     *
     * @brief conteine l'altezza della canvas(schermo di gioco
     */
    private static int HScreen;

    /**
     * @author Riccardi Francesco
     *
     * @brief il Main fa partire il disegno e il gioco
     *
     * Il main inizializza le righe e le colonne, crea i dati condivisi e i
     * thread scatola, modifica le dimensioni della canvas in base a righe e
     * colonne
     */
    public static void main(String[] args) {
        int numScatoleRighe = 1;
        int numScatoleColonne = 2;

        //creazione dei dati condivisi
        dati = new DatiCondivisi(numScatoleColonne);

        //creazione dei ThreadScatola
        box = new ThScatola[numScatoleColonne];
        for (int i = 0; i < dati.getNumScatoleColonne(); i++) {
            if (i == 0) {              //percentuale sabbia, datiCondivisi,larghezza e altezza scatola
                box[i] = new ThScatola(100, dati, i, 200, 200, true);
            } else {
                box[i] = new ThScatola(0, dati, i, 200, 200, false);
            }
        }
        
        thPallina=new ThPallina(dati);

        //le dimensioni della canvas dipendono dal numero di righe e colonne
        WScreen = (numScatoleColonne * 200);
        HScreen = (numScatoleRighe * 200);

        PApplet.main(new String[]{"sabbiapallina.NB_ProcessingScatola"});
        SwingGui swingGui = new SwingGui(dati);
        swingGui.show();
    }

    /**
     * @author Riccardi Francesco
     *
     * @brief setta le dimensioni della canvas e avvia i Thread
     */
    public void settings() {
        size(WScreen, HScreen);

        for (int i = 0; i < dati.getNumScatoleColonne(); i++) {
            box[i].start();
        }
        
        thPallina.start();
    }

    /**
     * @author Riccardi Francesco
     *
     * @brief inizializza la canvas
     */
    public void setup() {
        noStroke();
        frameRate(60);
    }

    /**
     * @author Riccardi Francesco
     *
     * @brief estisce la grafica se il programma è in running
     */
    public void draw() {
        if (!dati.isRunning()) {
            exit();
        }
        background(0, 0, 0);
        for (int i = 0; i < dati.getNumScatoleColonne(); i++) {
            displaySabbia(box[i].getSabbia(), i);

            if (box[i].isBallP()) {
                drawBall();
            }

        }
    }

    /**
     * @author Riccardi Francesco
     *
     * @param s contiene i dati relativi alla sabbia
     * @param id contiene l'id della scatola
     * @brief permette di disegnare lo spostamneto della sabbia in base
     * all`inclinazione
     *
     * sposta , in base alla scatola, i punti in cui disegnare la sabbia, se
     * l'inclunazione è positiva disegna da dx a sx, mentre se è negativa il
     * contrario, cioè da sx a dx
     */
    private void displaySabbia(Sabbia s, int id) {
        noStroke();
        PImage b;
        b = loadImage("image/sabbia.png");

        int spostamento = id * 200;
        if (dati.getGiroscopio().getInclinazioneX() >= 0) {
            for (int x = 200 + spostamento; x > (200 + spostamento - s.getWidthSabbia()); x--) {
                image(b, x, 0);
            }
        } else {
            for (int x = spostamento; x < (spostamento + s.getWidthSabbia()); x++) {
                image(b, x, 0);
            }
        }
    }
    
    /**
     * @author Riccardi Francesco
     *
     * @brief Metodo che si occupa di disegnare la pallina.
     * 
     * In questo metodo la pallina viene disegnata in base al suo raggio e alla posizione.
     */
    public void drawBall() {
        fill(color(255, 0, 0));
        stroke(0, 0, 0);
        ellipse(thPallina.getPallina().getPosX(), thPallina.getPallina().getPosY(), thPallina.getPallina().getRaggio(), thPallina.getPallina().getRaggio());
        noFill();
    }

}
