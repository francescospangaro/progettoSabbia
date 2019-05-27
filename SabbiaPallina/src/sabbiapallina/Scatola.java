/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

/**
 *
 * @author galimberti_francesco
 */
public class Scatola {
    private int altezzaScatola;
    private int lunghezzaScatola;
    private int larghezzaScatola;
    
    private int posX;
    private int posY;
    
    private Sabbia sabbia;

    public Scatola(int altezzaScatola, int lunghezzaScatola, int larghezzaScatola, int posX, int posY) {
        this.altezzaScatola = altezzaScatola;
        this.lunghezzaScatola = lunghezzaScatola;
        this.larghezzaScatola = larghezzaScatola;
        this.posX = posX;
        this.posY = posY;
        this.sabbia = new Sabbia();
    } 

    public int getLarghezzaScatola() {
        return larghezzaScatola;
    }
    
    
    public float getDiminuzioneX(){
        return sabbia.getDiminuzioneX();
    }
    
    public float getDiminuzioneY(){
        return sabbia.getDiminuzioneY();
    }
    
    public int getPercentuale(){
        return sabbia.getPercentuale();
    }
    
    public void setPercentuale(int val){
        sabbia.setPercentuale(val);
    }
    
    public int getWidthSabbia(){
        return sabbia.getWidthSabbia();
    }
    
    public int getHeightSabbia(){
        return sabbia.getHeightSabbia();
    }
    
    public void aggiornaXSabbia(int width){
        sabbia.aggiornaXSabbia(width);
    }
    
    public void aggiornaYSabbia(int height){
        sabbia.aggiornaYSabbia(height);
    }
    
    public void resetDiminuzione(){
        sabbia.resetDiminuzione();
    }
    
    public void aggiornamentoWidthHeightSabbia(){
        sabbia.aggiornamentoWidthHeightSabbia(larghezzaScatola,altezzaScatola);
    }
    
    public void aggiungiSabbia(int p){
        sabbia.aggiungiSabbia(p);
    }
    
}
