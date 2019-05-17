/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

/**
 * @author Galimberti Francesco
 * 
 * @version Prototipo1.0
 */
public class Sensore{
    /**
    * @author Riccardi Francesco
    * 
    * @brief contiene l'inclinazione sull'asse x
    */
    int inclinazioneX;
    
    /**
    * @author Riccardi Francesco
    * 
    * @brief contiene l'inclinazione sull'asse y
    */
    int inclinazioneY;

    /**
    * @author Riccardi Francesco
    * 
    * @brief costruttore che inizializza le inclinazioni a 0
    */
    public Sensore() {
        this.inclinazioneX = 0;
        this.inclinazioneY = 0;
    }

    /**
    * @author Riccardi Francesco
    * 
    * @param inclinazioneX contiene la nuova inclinazione
    * @brief aggiorna l'inclinazione
    */
    public void scriviInclinazioneX(int inclinazioneX) {
        this.inclinazioneX = inclinazioneX;
    }

    /**
    * @author Riccardi Francesco
    * 
    * @param inclinazioneY contiene la nuova inclinazione
    * @brief aggiorna l'inclinazione
    */
    public void scriviInclinazioneY(int inclinazioneY) {
        this.inclinazioneY = inclinazioneY;
    }

    /**
    * @author Riccardi Francesco
    * 
    * @brief ritorna l'inclinazione sull'asse x
    */
    public int getInclinazioneX() {
        return inclinazioneX;
    }

    /**
    * @author Riccardi Francesco
    * 
    * @brief ritorna l'inclinazione sull'asse x
    */
    public int getInclinazioneY() {
        return inclinazioneY;
    }
    
}
