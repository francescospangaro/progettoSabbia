/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

/**
 *
 * @author Spangaro Francesco
 */
public class DatiCondivisi {
    private Sabbia[] sabbie;
    private boolean gioco;
    private int running;
    private double inclinazioneX;
    private double inclinazioneY;
    private float red, green, blue;
    
    /* methods */

    public DatiCondivisi() {
    }

    public DatiCondivisi(Sabbia[] sabbie, int running, double inclinazioneX, double inclinazioneY, float red, float green, float blue) {
        this.sabbie = sabbie;
        this.running = running;
        this.inclinazioneX = inclinazioneX;
        this.inclinazioneY = inclinazioneY;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.gioco = false;
    }

    public Sabbia[] getSabbie() {
        return sabbie;
    }

    public void setSabbie(Sabbia[] sabbie) {
        this.sabbie = sabbie;
    }

    public int getRunning() {
        return running;
    }

    public void setRunning(int running) {
        this.running = running;
    }

    public double getInclinazioneX() {
        return inclinazioneX;
    }

    public void setInclinazioneX(double inclinazioneX) {
        this.inclinazioneX = inclinazioneX;
    }

    public double getInclinazioneY() {
        return inclinazioneY;
    }

    public void setInclinazioneY(double inclinazioneY) {
        this.inclinazioneY = inclinazioneY;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }
    
    public void incrementaInclX(){
        this.inclinazioneX++;
    }
    
    public void incrementaInclY(){
        this.inclinazioneY++;
    }
    
    public void diminuisciInclX(){
        this.inclinazioneX--;
    }
    
    public void diminuisciInclY(){
        this.inclinazioneY--;
    }
    
    public void setGioco(boolean gioco){
        this.gioco = gioco;
    }
    
    public boolean getGioco(){
        return this.gioco;
    }
}
