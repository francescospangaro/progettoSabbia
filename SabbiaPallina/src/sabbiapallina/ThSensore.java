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
public class ThSensore extends Thread{
    private DatiCondivisi ptrDati;
    private SwingGui gui;
    
    public ThSensore(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }
    
    
    @Override
    public void run(){
        while(ptrDati.getGioco()){
            
            
        }
    }
    
    public void incX(){
        ptrDati.incrementaInclX();
    }
    
    public void incY(){
        ptrDati.incrementaInclY();
    }
     
    public void dimX(){
        ptrDati.diminuisciInclX();
    }
     
    public void dimY(){
        ptrDati.diminuisciInclY();
    }
}
