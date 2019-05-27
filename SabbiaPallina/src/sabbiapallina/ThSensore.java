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
public class ThSensore extends Thread{
    
    private DatiCondivisi dati;
    
    private SwingGui swingGui;
    
    public ThSensore(DatiCondivisi dati, SwingGui swing){
        this.dati=dati;
        this.swingGui=swing;
    }
    
    @Override
    public void run() {
        while (dati.isRunning()) {
            
            dati.setInclinazioneX(swingGui.getValoreX());
            dati.setInclinazioneY(swingGui.getValoreY());

        }
    }
}
