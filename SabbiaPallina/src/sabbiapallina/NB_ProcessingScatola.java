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
    static ThScatola box[][];

    static ThPallina thPallina;

    static ThSensore thSensore;

    static SwingGui swingGui;

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
        //creazione dei dati condivisi
        dati = new DatiCondivisi();

        swingGui = new SwingGui(dati);
        swingGui.show();

        dati.waitsincroGuiMain();
        dati = swingGui.getDati();

        int numScatoleColonne = dati.getNumScatoleColonne();
        int numScatoleRighe = dati.getNumScatoleRighe();
        //creazione dei ThreadScatola
        box = new ThScatola[numScatoleRighe][numScatoleColonne];

        for (int r = 0; r < numScatoleRighe; r++) {
            for (int c = 0; c < numScatoleColonne; c++) {
                if (r == 0 && c == 0) {    //percentuale sabbia, datiCondivisi,larghezza e altezza scatola,presenza pallina
                    box[r][c] = new ThScatola(100, dati, r, c, true);
                } else {
                    box[r][c] = new ThScatola(0, dati, r, c, false);
                }
            }
        }

        thSensore = new ThSensore(dati, swingGui);
        thPallina = new ThPallina(dati);

        //le dimensioni della canvas dipendono dal numero di righe e colonne
        WScreen = (numScatoleColonne * 200);
        HScreen = (numScatoleRighe * 200);

        PApplet.main(new String[]{"sabbiapallina.NB_ProcessingScatola"});

    }

    /**
     * @author Riccardi Francesco
     *
     * @brief setta le dimensioni della canvas e avvia i Thread
     */
    public void settings() {
        size(WScreen, HScreen);

        thSensore.start();

        for (int r = 0; r < dati.getNumScatoleRighe(); r++) {
            for (int c = 0; c < dati.getNumScatoleColonne(); c++) {
                box[r][c].start();
            }
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

        background(100, 100, 100);

        for (int r = 0; r < dati.getNumScatoleRighe(); r++) {
            for (int c = 0; c < dati.getNumScatoleColonne(); c++) {
                drawSabbiaPixel(r, c, r * 200, c * 200);

                if (box[r][c].isBallP()) {
                    drawBall();
                }

                if (dati.getInclinazioneX() > 15 || dati.getInclinazioneX() < -15 || dati.getInclinazioneY() > 15 || dati.getInclinazioneY() <- 15) {
                    dati.signalEseguiPallina();
                }
            }
        }

    }

    public void drawSabbiaPixel(int r, int c, int riga, int colonna) {
        stroke(0, 0, 0);

        rect(colonna, riga, 200, 200);

        if (dati.getInclinazioneX() >= 0 && dati.getInclinazioneY() == 0) {
            colonna = colonna + 200 - 2;
            for (int i = 0; i < dati.getWidthSabbiaById(r, c); i++) {
                noStroke();
                fill(color(202, 188, 145));
                rect(colonna, riga, 1, 199);
                colonna--;
            }
        } else if (dati.getInclinazioneX() < 0 && dati.getInclinazioneY() == 0) {
            for (int i = 0; i < dati.getWidthSabbiaById(r, c); i++) {
                noStroke();
                fill(color(202, 188, 145));
                rect(colonna, riga, 1, 199);
                colonna++;
            }
        } else if (dati.getInclinazioneX() == 0 && dati.getInclinazioneY() >= 0) {
            riga = riga + 200 - 2;
            for (int i = 0; i < dati.getHeightSabbiaById(r, c); i++) {
                noStroke();
                fill(color(202, 188, 145));
                rect(colonna, riga, 199, 1);
                riga--;
            }
        } else if (dati.getInclinazioneX() == 0 && dati.getInclinazioneY() < 0) {
            for (int i = 0; i < dati.getHeightSabbiaById(r, c); i++) {
                noStroke();
                fill(color(202, 188, 145));
                rect(colonna, riga, 199, 1);
                riga++;
            }
        }

        stroke(0, 0, 0);
        noFill();
    }

    /*
    public void drawSabbiaPixel(int r, int c, int riga, int colonna) {
        stroke(0, 0, 0);

        rect(colonna, riga, 200, 200);

        if (dati.getInclinazioneX() >= 0) {
            colonna = colonna + 200 - 2;
            for (int i = 0; i < dati.getWidthSabbiaById(r, c); i++) {
                noStroke();
                fill(color(202, 188, 145));
                rect(colonna, riga, 1, 199);
                colonna--;
            }
        }

        if (dati.getInclinazioneX() < 0) {
            for (int i = 0; i < dati.getWidthSabbiaById(r, c); i++) {
                noStroke();
                fill(color(202, 188, 145));
                rect(colonna, riga, 1, 199);
                colonna++;
            }

        }
        stroke(0, 0, 0);
        noFill();
    }*/
    /**
     * @author Riccardi Francesco
     *
     * @brief Metodo che si occupa di disegnare la pallina.
     *
     * In questo metodo la pallina viene disegnata in base al suo raggio e alla
     * posizione.
     */
    public void drawBall() {
        fill(color(255, 0, 0));
        stroke(0, 0, 0);
        ellipse(thPallina.getPallina().getPosX(), thPallina.getPallina().getPosY(), thPallina.getPallina().getRaggio(), thPallina.getPallina().getRaggio());
        noFill();
    }
}
