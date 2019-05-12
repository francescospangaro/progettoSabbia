/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.util.Random;

/**
 *
 * @author Galimberti Francesco
 */
public class DatiCondivisi {
    int numScatoleColonne;
    boolean running;
    
    Sabbia[] sabbie;
    
    Sensore giroscopio;

    public DatiCondivisi(int numScatoleColonne) {
        this.numScatoleColonne = numScatoleColonne;
        running=true;
        
        giroscopio=new Sensore();
        
        sabbie = new Sabbia[numScatoleColonne];
        for(int i=0; i<numScatoleColonne; i++){  
            sabbie[i] = new Sabbia();
        }
    }
    
    
    public synchronized Sabbia getSabbiaById(int idScatola){
        return sabbie[idScatola];
    }
    
    public synchronized Sabbia[] getSabbie(){
        return sabbie;
    }
    

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }    
    
}
