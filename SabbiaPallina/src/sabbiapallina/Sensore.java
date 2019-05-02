/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

/**
 *
 * @author franc
 */
public class Sensore {
    private double inclX;
    private double inclY;
    private DatiCondivisi ptrDati;

    public Sensore(double inclX, double inclY, DatiCondivisi ptrDati) {
        this.inclX = inclX;
        this.inclY = inclY;
        this.ptrDati = ptrDati;
    }

    public Sensore(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    public double getInclX() {
        return ptrDati.getInclinazioneX();
    }

    public void setInclX(double inclX) {
        this.inclX = inclX;
        this.ptrDati.setInclinazioneX(this.inclX);
    }

    public double getInclY() {
        return ptrDati.getInclinazioneY();
    }

    public void setInclY(double inclY) {
        this.inclY = inclY;
        this.ptrDati.setInclinazioneY(this.inclY);
    }
    
    public void incrInclX(){
        this.inclX++;
        this.ptrDati.incrementaInclX();
    }
    
    public void incrInclY(){
        this.inclY++;
        this.ptrDati.incrementaInclY();
    }
    
    public void decrInclX(){
        this.inclX--;
        this.ptrDati.diminuisciInclX();
    }
    
    public void decrInclY(){
        this.inclY--;
        this.ptrDati.diminuisciInclY();
    }
}
