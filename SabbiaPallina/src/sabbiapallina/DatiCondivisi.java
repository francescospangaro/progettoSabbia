/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;



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
    private int valoreZ;
    
    private Semaphore sincroEventoPallina;
    private Semaphore sincroGuiMain;
    private Semaphore sincroSalta;
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
   // private Sabbia[][] sabbie;
    
    private Scatole scatole;
    
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
    private Pallina pallina;
    
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
        this.pallina = new Pallina();
        
        giroscopio=new Sensore();
        
        sincroEventoPallina=new Semaphore(0);
        sincroSalta = new Semaphore(0);
        
        
        /*
        sabbie = new Sabbia[numScatoleRighe][numScatoleColonne];
        for (int r = 0; r < numScatoleRighe; r++) {
            for (int c = 0; c < numScatoleColonne; c++) {
                sabbie[r][c] = new Sabbia();
            }
        }*/
        
        scatole=new Scatole(numScatoleRighe,numScatoleColonne);
        
        pallineP = new boolean[numScatoleRighe][numScatoleColonne];
    }
    
    public DatiCondivisi(){        
        sincroGuiMain=new Semaphore(0);
    }    

    public Pallina getPallina() {
        return pallina;
    }

    public void setPallina(Pallina pallina) {
        this.pallina = pallina;
    }
    
    public void setInclinazioneX(int inclinazione){
        giroscopio.scriviInclinazioneX(inclinazione);
    }
    
    public void setInclinazioneY(int inclinazione){
        giroscopio.scriviInclinazioneY(inclinazione);
    }
  
    
    public synchronized void aggiornaXScatolaById(int riga, int colonna){
        scatole.getScatola(riga, colonna).aggiornaXSabbia(giroscopio.getInclinazioneX());
    }
    
    public synchronized void aggiornaYScatolaById(int riga, int colonna){
        scatole.getScatola(riga, colonna).aggiornaYSabbia(giroscopio.getInclinazioneY());
    }
    
    public synchronized float getDiminuzioneXSabbiaById(int riga, int colonna){
        return scatole.getScatola(riga, colonna).getDiminuzioneX();
    }
    
    public synchronized float getDiminuzioneYSabbiaById(int riga, int colonna){
        return scatole.getScatola(riga, colonna).getDiminuzioneY();
    }
    
    public synchronized void resetDiminuzioneSabbiaById(int riga, int colonna){
        //scatole.getScatola(riga, colonna).getSabbia().resetDiminuzione();
        scatole.getScatola(riga, colonna).resetDiminuzione();
    }
    
    public synchronized int getPercentualeSabbiaById(int riga, int colonna){
        //return scatole.getScatola(riga, colonna).getSabbia().getPercentuale();
        return scatole.getScatola(riga, colonna).getPercentuale();
    }
    
    public synchronized void setPercentualeSabbiaById(int riga, int colonna,int valore){
        scatole.getScatola(riga, colonna).setPercentuale(valore);
    }
    
    public synchronized void aggiornaWidthHeightSabbiaById(int riga, int colonna){
        scatole.getScatola(riga, colonna).aggiornamentoWidthHeightSabbia();
    }
    
    public synchronized void aggiungiSabbiaById(int riga, int colonna,int perc){
        scatole.getScatola(riga, colonna).aggiungiSabbia(perc);
    }
    
    public synchronized int getWidthSabbiaById(int riga, int colonna){
        return scatole.getScatola(riga, colonna).getWidthSabbia();
    }
    
    public synchronized int getHeightSabbiaById(int riga, int colonna){
        return scatole.getScatola(riga, colonna).getHeightSabbia();
    }
    
    public synchronized int getInclinazioneX(){
        return giroscopio.getInclinazioneX();
    }
    
    public synchronized int getInclinazioneY(){
        return giroscopio.getInclinazioneY();
    }
    
    public synchronized boolean isRunning() {
        return running;
    }
    
    public void stop() {
        this.running = false;
    }    

    public int getNumScatoleColonne() {
        return numScatoleColonne;
    }

    public int getNumScatoleRighe() {
        return numScatoleRighe;
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
    
    public void waitEseguiPallina(){
        try {
            sincroEventoPallina.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    
    public void waitsincroGuiMain(){
        try {
            sincroGuiMain.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void signalsincroGuiMain() {
        sincroGuiMain.release();
    }
    
    public int getZ(){
        return valoreZ;
    }
    
    public void setValoreZ(int z){
        this.valoreZ = z;
    }
    
    public void set(int righe,int colonne){
        this.numScatoleRighe = righe;
        this.numScatoleColonne = colonne;
        running=true;
        
        giroscopio=new Sensore();
        
        sincroEventoPallina=new Semaphore(0);
        sincroSalta = new Semaphore(0);        
        
        scatole=new Scatole(numScatoleRighe,numScatoleColonne);
        
        this.pallina = new Pallina();
        pallineP = new boolean[numScatoleRighe][numScatoleColonne];
    }
    
}
