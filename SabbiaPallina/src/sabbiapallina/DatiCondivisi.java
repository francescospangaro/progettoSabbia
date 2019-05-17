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
    int numScatoleColonne;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief indica se il gioco e` in corso
    */
    boolean running;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief vettore di oggetti sabbia
    */
    Sabbia[] sabbie;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief sensore per salvare l`inclinazione
    */
    Sensore giroscopio;

    
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
    
}
