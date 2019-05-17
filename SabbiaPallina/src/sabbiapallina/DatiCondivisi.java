/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;



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
    private Sabbia[] sabbie;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief vettore di valori booleani
    */
    private boolean[] pallineP;
    
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
    public DatiCondivisi(int numScatoleColonne) {
        this.numScatoleColonne = numScatoleColonne;
        running=true;
        
        giroscopio=new Sensore();
        
        
        sabbie = new Sabbia[numScatoleColonne];
        for(int i=0; i<numScatoleColonne; i++){  
            sabbie[i] = new Sabbia();
        }
        
        pallineP = new boolean[numScatoleColonne];
    }
    
  
    /**
    * @author Riccardi Francesco
    * 
    * @param idScatola contiene l'id della scatola selezionata
    * @brief permette ad ogni Thread scatola di prendere la propria sabbia in base all`id
    */
    public synchronized Sabbia getSabbiaById(int idScatola){
        return sabbie[idScatola];
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

    public Sabbia[] getSabbie() {
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

    public boolean getPalline(int p) {
        return pallineP[p];
    }
    
    public boolean setPalline(int pos, boolean v) {
        return pallineP[pos]=v;
    }
    
    
    
}
