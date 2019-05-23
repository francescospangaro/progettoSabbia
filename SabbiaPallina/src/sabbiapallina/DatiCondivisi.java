/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.util.concurrent.Semaphore;



/**
 * @author Galimberti Francesco
 * 
 * @version Prototipo1.0
 * @brief La classe collabora con le classi Sabbia e Sensore
 */

public class DatiCondivisi {
    /**
    * @author Riccardi Francesco
    * 
    * @brief memorizza il numero di colonne
    */
    private int numScatoleColonne;
    private int numScatoleRighe;
    
    private Semaphore sincroEventoPallina;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief indica se il gioco e` in corso
    */
    private boolean running;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief vettore di oggetti sabbia
    */
    private Sabbia[][] sabbie;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief vettore di valori booleani
    */
    private boolean[][] pallineP;
    
    /**
     * @author Riccardi Francesco
     *
     * @brief Attributo boolean che indica se far avvenire lo spostamento.
     */
    private boolean sposta;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief sensore per salvare l`inclinazione
    */
    private Sensore giroscopio;    
    
    /**
    * @author Riccardi Francesco
    * 
    * @param numScatoleColonne contiene il numero di colonne
    * @brief La classe rende il gioco attivo e crea il vettore di sabbia con inizializzazione
    */
    public DatiCondivisi(int numScatoleRighe, int numScatoleColonne) {
        this.numScatoleRighe = numScatoleRighe;
        this.numScatoleColonne = numScatoleColonne;
        running=true;
        
        giroscopio=new Sensore();
        
        sincroEventoPallina=new Semaphore(0);
        
        sabbie = new Sabbia[numScatoleRighe][numScatoleColonne];
        for (int r = 0; r < numScatoleRighe; r++) {
            for (int c = 0; c < numScatoleColonne; c++) {
                sabbie[r][c] = new Sabbia();
            }
        }
        
        pallineP = new boolean[numScatoleRighe][numScatoleColonne];
    }
    
  
    /**
    * @author Riccardi Francesco
    * 
    * @param riga contiene la riga della scatola selezionata
    * @param colonna contiene la colonna della scatola selezionata
    * @brief permette ad ogni Thread scatola di prendere la propria sabbia in base all`id
    */
    public synchronized Sabbia getSabbiaById(int riga, int colonna){
        return sabbie[riga][colonna];
    } 

    /**
    * @author Riccardi Francesco
    * 
    * @brief permette di controllare se si e` in gioco o meno
    */
    public synchronized boolean isRunning() {
        return running;
    }

    /**
    * @author Riccardi Francesco
    * 
    * @brief quando viene premuto il pulsante stop dallo SwingGui il gioco viene fermato
    */
    public void stop() {
        this.running = false;
    }    

    public int getNumScatoleColonne() {
        return numScatoleColonne;
    }

    public Sabbia[][] getSabbie() {
        return sabbie;
    }

    public Sensore getGiroscopio() {
        return giroscopio;
    }

    public boolean isSposta() {
        return sposta;
    }

    public void setSposta(boolean sposta) {
        this.sposta = sposta;
    }

    public boolean getPalline(int riga,int colonna) {
        return pallineP[riga][colonna];
    }
    
    public synchronized boolean setPalline(int riga,int colonna, boolean v) {
        return pallineP[riga][colonna]=v;
    }

    public int getNumScatoleRighe() {
        return numScatoleRighe;
    }
    
    public void waitEseguiPallina() throws InterruptedException {
        sincroEventoPallina.acquire();
    }
    
    public void signalEseguiPallina() {
        sincroEventoPallina.release();
    }

    public synchronized void setNumScatoleColonne(int numScatoleColonne) {
        this.numScatoleColonne = numScatoleColonne;
    }

    public synchronized void setNumScatoleRighe(int numScatoleRighe) {
        this.numScatoleRighe = numScatoleRighe;
    }
    
    
    
    
}
