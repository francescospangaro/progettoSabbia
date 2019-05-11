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
    int numScatole;
    boolean running;
    boolean positivoX;
    
    Sabbia[] sabbie;
    
    Sensore giroscopio;

    public DatiCondivisi(int numScatole) {
        this.numScatole = numScatole;
        running=true;
        positivoX = false;
        
        giroscopio=new Sensore();
        
        sabbie = new Sabbia[numScatole];
        for(int i=0; i<numScatole; i++){  
            //Random rand = new Random();
            //sabbie[i] = new Sabbia(rand.nextInt(101));
            sabbie[i] = new Sabbia();
        }
    }

    public synchronized boolean isPositivoX() {
        return positivoX;
    }

    public synchronized void setPositivoX(boolean positivoX) {
        this.positivoX = positivoX;
    }
    
    public synchronized Sabbia getSabbiaById(int idScatola){
        return sabbie[idScatola];
    }
    
    public synchronized Sabbia[] getSabbie(){
        return sabbie;
    }
    
    public void setScreen(int widthScatola) {
        int xScatola = 0;
        int yScatola = 0;      
        
        for (int i = 0; i < sabbie.length; i++) {
            sabbie[i].setScreen(xScatola, yScatola, widthScatola);
            //xScatola = xScatola + widthScatola;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }    
    
}
