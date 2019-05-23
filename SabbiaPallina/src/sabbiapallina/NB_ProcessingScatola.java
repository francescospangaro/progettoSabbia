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
        int numScatoleRighe = 3;
        int numScatoleColonne = 5;

        //creazione dei dati condivisi
        dati = new DatiCondivisi(numScatoleRighe, numScatoleColonne);

        //creazione dei ThreadScatola
        box = new ThScatola[numScatoleRighe][numScatoleColonne];

        for (int r = 0; r < numScatoleRighe; r++) {
            for (int c = 0; c < numScatoleColonne; c++) {
                if (r == 0 && c == 0) {    //percentuale sabbia, datiCondivisi,larghezza e altezza scatola,presenza pallina
                    box[r][c] = new ThScatola(100, dati, r, c, 200, 200, true);
                } else {
                    box[r][c] = new ThScatola(0, dati, r, c, 200, 200, false);
                }
            }
        }

        thPallina = new ThPallina(dati);

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
                //drawSabbia(box[r][c].getSabbia(), r, c);
                if (box[r][c].isBallP()) {
                    drawBall();
                }

                if (dati.getGiroscopio().getInclinazioneX() > 15 || dati.getGiroscopio().getInclinazioneX() < -15) {
                    dati.signalEseguiPallina();
                }
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
    private void drawSabbia(Sabbia s, int r, int c) {
        noStroke();
        PImage b;
        b = loadImage("image/sabbia.png");

        int spostamentoY = r * 200;
        int spostamentoX = c * 200;
        if (dati.getGiroscopio().getInclinazioneX() >= 0) {
            for (int x = 200 + spostamentoX; x > (200 + spostamentoX - s.getWidthSabbia()); x--) {
                image(b, x, spostamentoY);
            }
        } else {
            for (int x = spostamentoX; x < (spostamentoX + s.getWidthSabbia()); x++) {
                image(b, x, spostamentoY);
            }
        }
    }

    public void drawSabbiaPixel(int r, int c, int riga, int colonna) {
        stroke(0, 0, 0);

        rect(colonna, riga, 200, 200);

        if (dati.getGiroscopio().getInclinazioneX() >= 0) {
            colonna = colonna + 200 - 2;
            for (int i = 0; i < dati.getSabbiaById(r, c).getWidthSabbia(); i++) {
                noStroke();
                fill(color(202, 188, 145));
                rect(colonna, riga, 1, 199);
                colonna--;
            }
        }

        if (dati.getGiroscopio().getInclinazioneX() < 0) {
            for (int i = 0; i < dati.getSabbiaById(r, c).getWidthSabbia(); i++) {
                noStroke();
                fill(color(202, 188, 145));
                rect(colonna, riga, 1, 199);
                colonna++;
            }

        }
        stroke(0, 0, 0);
        noFill();
    }

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
