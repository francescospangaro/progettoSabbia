/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spangaro_francesco
 */
public class ThPallina extends Thread {
    /**
     * @author spangaro_francesco
     *
     * @brief Oggetto di tipo DatiCondivisi che serve per richiamare i metodi
     * della classe DatiCondivisi.
     */
    private DatiCondivisi dati;
    
    private int idScatola;
    private int idTarget;
    
    private Pallina pallina;

    /**
     * @author spangaro_francesco
     *
     * @brief Metodo costruttore con parametri
     *
     * Metodo che inizializza gli attributi ptrDati, array ed imposta la scatola
     * di riferimento.
     *
     * @param ptrDati parametro che serve per richiamare i metodi della classe
     * DatiCondivisi.
     */
    public ThPallina(DatiCondivisi ptrDati) {
        this.dati = ptrDati;
        idScatola=0;
        pallina=new Pallina(dati);
        
        idTarget=idScatola;
        if(dati.getGiroscopio().getInclinazioneX()>10){
            idTarget++;            
        }else if(dati.getGiroscopio().getInclinazioneX()<-10){
            idTarget--;
        }
    }

    /**
     * @author spangaro_francesco
     *
     * @brief Metodo che si occupa di gestire lo spostamento della spallina
     *
     * Metodo che gestisce la pallina attraverso i metodi
     * GestionePallinaVersoDestra(),GestionePallinaVersoSinistra() inoltre
     * utilizza l'oggetto array tramite puntatore id per indicare la scatola di
     * riferimento su cui lavorare.
     */
    @Override
    public void run() {
        while (true) {
            
            if (dati.getPalline(idScatola)) {                  //Se nella scatola è presente la pallina
                pallina.Move(idScatola);             //La pallina viene mossa
            }
            
            try {
                Thread.sleep(10);                        //provare 5 millisecondi
            } catch (InterruptedException ex) {
                Logger.getLogger(ThPallina.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (dati.getGiroscopio().getInclinazioneX() > 10) {
                
                GestionePallinaVersoDestra();
                
            }
            
            if (dati.getGiroscopio().getInclinazioneX() < -10) {
                
                GestionePallinaVersoSinistra();
                
            }
            
        }
    }

    /**
     * @author spangaro_francesco
     *
     * @brief Metodo che si occupa di gestire lo spostamento della pallina.
     *
     * Metodo che prima verifica la presenza della pallina nella scatola, se
     * presente incrementa la velocitá della pallina, in seguito se la pallina
     * tocca il bordo ad una velocitá sufficiente e se la scatola di riferimento
     * é piena avviene lo spostamento nell'altra scatola.
     */
    private void GestionePallinaVersoDestra() {
        
        idTarget = idScatola+1;
        
        if (dati.getPalline(idScatola)) {
            pallina.IncrementaVelocitàX();      //incremento velocità pallina se presente nella scatola
        }
        if ((idTarget % dati.getNumScatoleColonne()) != 0) {
            if ((dati.getPalline(idScatola)) && (dati.isSposta()) && (pallina.getPosX() == (200 + (200 * idScatola)) - (pallina.getRaggio() / 2)) && (dati.getSabbiaById(idScatola).getPercentuale()==100)) {     //se pallina è presente, se ha raggiunto una velocità sufficente e se tocca il bordo
                                
                idScatola++;
                pallina = new Pallina(dati, (idTarget * 200) + (pallina.getRaggio() / 2), 100);      //Creo nuova pallina in scatola successiva

                CambioPallina();        //Resetto ball e ballTF della scatola in esecuzione, resetto l'attributo sposta e indico che la scatola successiva ha la pallina
            }
        }
    }

    /**
     * @author spangaro_francesco
     *
     * @brief Metodo che imposta di volta in volta la presenza della pallina
     * nella scatola.
     *
     * Metodo che notifica dopo lo spostamento della pallina nella scatola
     * adiacente la presenza della stessa nella nuova scatola.
     *
     */
    private void CambioPallina() {
        dati.setPalline(idScatola, false);
        dati.setPalline(idTarget, true);
        dati.setSposta(false);
    }

    /**
     * @author spangaro_francesco
     *
     * @brief Metodo che si occupa di gestire lo spostamento della pallina.
     *
     * Metodo che prima verifica la presenza della pallina nella scatola, se
     * presente decrementa la velocitá della pallina, in seguito se la pallina
     * tocca il bordo ad una velocitá sufficiente e se la scatola di riferimento
     * é piena avviene lo spostamento nell'altra scatola.
     */
    private void GestionePallinaVersoSinistra() {
        
        idTarget=idScatola-1;
        
        if (dati.getPalline(idScatola)) {
            pallina.DecrementaVelocitàX();      //decremento velocità pallina se presente nella scatola
        }
        if ((idScatola % dati.getNumScatoleColonne()) != 0) {
            if ((dati.getPalline(idScatola)) && (dati.isSposta()) && (pallina.getPosX() == ((200 * idScatola)) + (pallina.getRaggio() / 2)) && (dati.getSabbiaById(idScatola).getPercentuale()==100)) {     //se pallina è presente, se ha raggiunto una velocità sufficente e se tocca il bordo
                                
                idScatola--;
                
                if (idTarget == 0) {
                    pallina = new Pallina(dati, (200 + (idTarget * 200)) - (pallina.getRaggio() / 2), 100);       //Creo nuova pallina in scatola precedente(se è la scatola con id=0)
                } else {
                    pallina = new Pallina(dati, (idScatola * 200) - (pallina.getRaggio() / 2), 100);       //Creo nuova pallina in scatola precedente
                }
                
                CambioPallina();        //Resetto ball e ballTF della scatola in esecuzione, resetto l'attributo sposta e indico che la scatola successiva ha la pallina
            }
        }
    }

    public Pallina getPallina() {
        return pallina;
    }
    
    

}
