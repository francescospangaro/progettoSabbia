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
public class Sensore{
    int inclinazioneX;
    int inclinazioneY;

    public Sensore() {
        this.inclinazioneX = 0;
        this.inclinazioneY = 0;
    }

    public void scriviInclinazioneX(int inclinazioneX) {
        this.inclinazioneX = inclinazioneX;
    }

    public void scriviInclinazioneY(int inclinazioneY) {
        this.inclinazioneY = inclinazioneY;
    }

    public int getInclinazioneX() {
        return inclinazioneX;
    }

    public int getInclinazioneY() {
        return inclinazioneY;
    }
    
}
