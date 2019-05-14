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
public class DatiCondivisi {
    //memorizza il numero di colonne
    int numScatoleColonne;
    
    //indica se il gioco e` in corso
    boolean running;
    
    //vettore di oggetti sabbia
    Sabbia[] sabbie;
    
    //sensore per salvare l`inclinazione
    Sensore giroscopio;

    
    public DatiCondivisi(int numScatoleColonne) {
        this.numScatoleColonne = numScatoleColonne;
        running=true;
        
        giroscopio=new Sensore();
        
        //creazione del vettore di sabbia con inizializzazione
        sabbie = new Sabbia[numScatoleColonne];
        for(int i=0; i<numScatoleColonne; i++){  
            sabbie[i] = new Sabbia();
        }
    }
    
    //permette ad ogni Thread scatola di prendere la propria sabbia in base all`id
    public synchronized Sabbia getSabbiaById(int idScatola){
        return sabbie[idScatola];
    }    

    //permette di controllare se si e` in gioco o meno
    public synchronized boolean isRunning() {
        return running;
    }

    //quando viene premuto il pulsante stop dallo SwingGui il gioco viene fermato
    public void stop() {
        this.running = false;
    }    
    
}
