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

    private int rigaScatola;
    private int colonnaScatola;

    private int rigaDest;
    private int colonnaDest;

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
        this.colonnaScatola = 0;
        this.rigaScatola = 0;
        pallina = new Pallina(dati);
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
        while (dati.isRunning()) {
            dati.waitEseguiPallina();            
            
            pallina.move(rigaScatola, colonnaScatola, dati.getInclinazioneX(),dati.getInclinazioneY());             

            try {
                Thread.sleep(10);                        //provare 5 millisecondi
            } catch (InterruptedException ex) {
                Logger.getLogger(ThPallina.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (dati.getInclinazioneX() >= 15) {

                colonnaDest = colonnaScatola + 1;
                rigaDest = rigaScatola;

                VersoDestra();
            }

            if (dati.getInclinazioneX() <= -15) {
                colonnaDest = colonnaScatola - 1;
                rigaDest = rigaScatola;
                
                VersoSinistra();
            }
            
            if (dati.getInclinazioneY() >=15) {
                colonnaDest = colonnaScatola;
                rigaDest = rigaScatola +1;
                
                VersoBasso();
            }
            
            if (dati.getInclinazioneY() <= -15) {
                colonnaDest = colonnaScatola;
                rigaDest = rigaScatola -1;
                
                VersoAlto();
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
    private void VersoDestra() {
        pallina.IncrementaVelocitàX();      //incremento velocità pallina se presente nella scatola

        if (colonnaDest != dati.getNumScatoleColonne()) {

            if (pallina.getPosX() == (200 + (200 * colonnaScatola)) - (pallina.getRaggio() / 2)) {
                if ((dati.getPalline(rigaScatola,colonnaScatola)) && (dati.isSposta()) && ((dati.getPercentualeSabbiaById(rigaDest,colonnaDest) >= 50) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola) <= 100))) {     //se pallina è presente, se ha raggiunto una velocità sufficente e se tocca il bordo
                    //System.out.println("idDest pallina" + colonnaDest);
                    
                    CambioPallina();        //Resetto ball e ballP della scatola in esecuzione, resetto l'attributo sposta e indico che la scatola successiva ha la pallina

                    pallina = new Pallina(dati, 50+(colonnaDest * 200) + (pallina.getRaggio() / 2), pallina.getPosY());      //Creo nuova pallina in scatola successiva
                }
            }

        }
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
    private void VersoSinistra() {
        pallina.DecrementaVelocitàX();      //decremento velocità pallina se presente nella scatola

        if (colonnaDest != -1) {

            if (pallina.getPosX() == (200 * colonnaScatola) + (pallina.getRaggio() / 2)) {
                if ((dati.getPalline(rigaScatola,colonnaScatola)) && (dati.isSposta()) && ((dati.getPercentualeSabbiaById(rigaDest,colonnaDest) >= 50) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola) <= 100))) {     //se pallina è presente, se ha raggiunto una velocità sufficente e se tocca il bordo
                    //System.out.println("idDest pallina" + colonnaDest);
                    
                    CambioPallina();        //Resetto ball e ballP della scatola in esecuzione, resetto l'attributo sposta e indico che la scatola successiva ha la pallina                
                    
                    pallina = new Pallina(dati, -50 + (200 + (colonnaDest * 200)) - (pallina.getRaggio() / 2), pallina.getPosY());
                }
            }

        }
    }
    
    private void VersoBasso() {
        pallina.IncrementaVelocitàY();      //incremento velocità pallina se presente nella scatola
        
        if (rigaDest != dati.getNumScatoleRighe()) {
            
            if (pallina.getPosY() == (200 + (200 * rigaScatola)) - (pallina.getRaggio() / 2)) {
                if ((dati.getPalline(rigaScatola,colonnaScatola)) && (dati.isSposta()) && ((dati.getPercentualeSabbiaById(rigaDest,colonnaDest) >= 50) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola) <= 100))) {     //se pallina è presente, se ha raggiunto una velocità sufficente e se tocca il bordo
                    //System.out.println("idDest pallina" + colonnaDest);
                    
                    CambioPallina();        //Resetto ball e ballP della scatola in esecuzione, resetto l'attributo sposta e indico che la scatola successiva ha la pallina

                    pallina = new Pallina(dati, pallina.getPosX(), 50+(rigaDest * 200) + (pallina.getRaggio() / 2));      //Creo nuova pallina in scatola successiva
                }
            }

        }
    }
    
    private void VersoAlto() {
        pallina.DecrementaVelocitàY();      //decremento velocità pallina se presente nella scatola

        if (rigaDest != -1) {

            if (pallina.getPosY() == (200 * rigaScatola) + (pallina.getRaggio() / 2)) {
                if ((dati.getPalline(rigaScatola, colonnaScatola)) && (dati.isSposta()) && ((dati.getPercentualeSabbiaById(rigaDest,colonnaDest) >= 50) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola) <= 100))) {     //se pallina è presente, se ha raggiunto una velocità sufficente e se tocca il bordo
                    //System.out.println("idDest pallina" + colonnaDest);
                    
                    CambioPallina();        //Resetto ball e ballP della scatola in esecuzione, resetto l'attributo sposta e indico che la scatola successiva ha la pallina                
                    
                    pallina = new Pallina(dati, pallina.getPosX(), -50 + (200 + (rigaDest * 200)) - (pallina.getRaggio() / 2));
                }
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
        dati.setPalline(rigaScatola,colonnaScatola, false);
        dati.setPalline(rigaDest,colonnaDest, true);
        dati.setSposta(false);
        rigaScatola = rigaDest;
        colonnaScatola = colonnaDest;
    }

    public Pallina getPallina() {
        return pallina;
    }

}
