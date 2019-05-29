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
 * @author Galimberti Francesco
 */
public class ThScatola extends Thread {

    //attributo che permette di avere accesso agli elementi condivisi
    private DatiCondivisi dati;

    //attributo che salva la riga e colonna della scatola(coordinate)
    private final int rigaScatola;
    private final int colonnaScatola;

    //salva l'id della sabbia(che corrisponde a quello della scatola in cui e` contenuta) in cui spostare la sabbia persa
    private int rigaDest;
    private int colonnaDest;

    public ThScatola(int percSabbia, DatiCondivisi ptrdati, int rigaScatola, int colonnaScatola, boolean ballP) {
        this.dati = ptrdati;
        this.rigaScatola = rigaScatola;
        this.colonnaScatola = colonnaScatola;
        
        dati.setPercentualeSabbiaById(rigaScatola, colonnaScatola, percSabbia);
        dati.aggiornaWidthHeightSabbiaById(rigaScatola, colonnaScatola);

        this.dati.setPalline(rigaScatola, colonnaScatola, ballP);
    }

    public void run() {
        try {
            while (dati.isRunning()) {

                //viene richiamato il metodo per aggiornare la sabbia "persa" in base all`inclinazioneX
                dati.aggiornaXScatolaById(rigaScatola, colonnaScatola);
                dati.aggiornaYScatolaById(rigaScatola, colonnaScatola);

                //attendo 10ms
                Thread.sleep(10);

                //se inclinazione positiva(verso dx)
                if (dati.getInclinazioneX() >= 15) {
                    //la scatola nel quale devo spostare la sabbia e` quella successiva
                    colonnaDest = colonnaScatola + 1;
                    rigaDest = rigaScatola;
                    //System.out.println("Th"+idScatola+"--> Dest"+idDest);         //->output di controllo

                    //viene richiamato il metodo per gestire lo spostamento della sabbia verso dx
                    versoDestra(dati.getDiminuzioneXSabbiaById(rigaScatola, colonnaScatola));
                }

                //se inclinazione negativa (verso sx)
                if (dati.getInclinazioneX() <= -15) {
                    //la scatola in cui spostare la sabbia e` quella precedente
                    colonnaDest = colonnaScatola - 1;
                    rigaDest = rigaScatola;
                    //System.out.println("Th"+idScatola+"--> Dest"+idDest);         //->output di controllo

                    //viene richiamato il metodo per gestire lo spostamento della sabbia verso dx
                    versoSinistra(dati.getDiminuzioneXSabbiaById(rigaScatola, colonnaScatola));
                }
                
                //se inclinazione positiva(verso basso)
                if (dati.getInclinazioneY() >= 15) {
                    //la scatola nel quale devo spostare la sabbia e` quella successiva
                    colonnaDest = colonnaScatola;
                    rigaDest = rigaScatola + 1;
                    //System.out.println("Th"+idScatola+"--> Dest"+idDest);         //->output di controllo

                    //viene richiamato il metodo per gestire lo spostamento della sabbia verso dx
                    versoBasso(dati.getDiminuzioneYSabbiaById(rigaScatola, colonnaScatola));
                }

                //se inclinazione negativa (verso alto)
                if (dati.getInclinazioneY() <= -15) {
                    //la scatola in cui spostare la sabbia e` quella precedente
                    colonnaDest = colonnaScatola;
                    rigaDest = rigaScatola - 1;
                    //System.out.println("Th"+idScatola+"--> Dest"+idDest);         //->output di controllo

                    //viene richiamato il metodo per gestire lo spostamento della sabbia verso dx
                    versoAlto(dati.getDiminuzioneYSabbiaById(rigaScatola, colonnaScatola));
                }
                
                

                //dopo avere gestito il movimento resetto la sabbia persa
                dati.resetDiminuzioneSabbiaById(rigaScatola, colonnaScatola);

                //System.out.println("r" + rigaScatola + " c" + colonnaScatola + "  percSabbia" + dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola));

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(ThScatola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void versoDestra(float dim) {
        //se la scatola in cui devo spostare la sabbia persa esiste(idDest non puo essere uguale al numero delle colonne avendo id che va da 0 a numColonne-1)
        if (colonnaDest != dati.getNumScatoleColonne()) {
            try {
                //se ho della sabbia persa, la scatola e` piena di sabbia e la scatola precedente non ha piu sabbia, oppure, ho della sabbia persa, la scatola ha della sabbia e il destinatario non e` ancora pieno
                if (((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) == 100) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola - 1) == 0)) || ((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola - 1) == 0) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola) != 0) && (dati.getPercentualeSabbiaById(rigaDest,colonnaDest) <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //se ho della sabbia persa e la scatola e` piena di sabbia
                if (((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) == 100)) || ((dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) != 0) && (dati.getPercentualeSabbiaById(rigaDest,colonnaDest) <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento                }
                }
            }
        }
    }
    
    private void versoBasso(float dim) {
        if (rigaDest != dati.getNumScatoleRighe()) {
            try {
                //se ho della sabbia persa, la scatola e` piena di sabbia e la scatola successiva non ha piu sabbia, oppure, ho della sabbia persa, la scatola ha della sabbia e il destinatario non e` ancora pieno
                if (((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) == 100) && (dati.getPercentualeSabbiaById(rigaScatola-1, colonnaScatola) == 0)) || ((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola -1, colonnaScatola) == 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) != 0) && (dati.getPercentualeSabbiaById(rigaDest, colonnaDest) <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //se ho della sabbia persa e la scatola e` piena di sabbia
                if ((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) == 100) || ((dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) != 0) && (dati.getPercentualeSabbiaById(rigaDest, colonnaDest) <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento

                }
            }
        }
    }

    private void versoSinistra(float dim) {
        //se la scatola in cui devo spostare la sabbia persa esiste(idDest non puo essere uguale a -1 avendo id che va da 0 a numColonne-1)
        if (colonnaDest != -1) {
            try {
                //se ho della sabbia persa, la scatola e` piena di sabbia e la scatola successiva non ha piu sabbia, oppure, ho della sabbia persa, la scatola ha della sabbia e il destinatario non e` ancora pieno
                if (((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) == 100) && ((dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola + 1) == 0))) || ((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola + 1) == 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) != 0) && (dati.getPercentualeSabbiaById(rigaDest,colonnaDest) <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //se ho della sabbia persa e la scatola e` piena di sabbia
                if ((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) == 100) || ((dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) != 0) && (dati.getPercentualeSabbiaById(rigaDest,colonnaDest) <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento

                }
            }
        }
    }
    
    private void versoAlto(float dim) {
        if (rigaDest != -1){
           try {
                //se ho della sabbia persa, la scatola e` piena di sabbia e la scatola precedente non ha piu sabbia, oppure, ho della sabbia persa, la scatola ha della sabbia e il destinatario non e` ancora pieno
                if (((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) == 100) && (dati.getPercentualeSabbiaById(rigaScatola+1,colonnaScatola) == 0)) || ((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola+1,colonnaScatola) == 0) && (dati.getPercentualeSabbiaById(rigaScatola,colonnaScatola) != 0) && (dati.getPercentualeSabbiaById(rigaDest,colonnaDest) <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //se ho della sabbia persa e la scatola e` piena di sabbia
                if (((dim > 0) && (dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) == 100)) || ((dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) != 0) && (dati.getPercentualeSabbiaById(rigaDest, colonnaDest) <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento                }
                }
            } 
        }
    }
    
    

    //metodo che permette di continare a gestire lo spostamento della sabbia, in base alla percentuale di sabbia persa
    private void procedi(float sm) {
        int perc = dati.getPercentualeSabbiaById(rigaScatola, colonnaScatola) - (int) sm;//nuova percentuale
        //se si perde piu sabbia di quella che si ha
        if (perc < 0) {
            dati.setPercentualeSabbiaById(rigaScatola, colonnaScatola, 0);
            dati.aggiornaWidthHeightSabbiaById(rigaScatola, colonnaScatola);                // aggiornamento larghezza sabbia
            
            dati.setPercentualeSabbiaById(rigaDest,colonnaDest, 100);// il destinatario sara pieno di sabbia
            dati.aggiornaWidthHeightSabbiaById(rigaDest,colonnaDest);  // aggiornamento larghezza sabbia destinatario

            //altrimenti
        } else {
            dati.setPercentualeSabbiaById(rigaScatola, colonnaScatola, perc);//cambia la sabbia contenuta nella scatola con la nuova percentuale
            dati.aggiornaWidthHeightSabbiaById(rigaScatola, colonnaScatola);   
            
            dati.aggiungiSabbiaById(rigaDest,colonnaDest, (int) sm);//aggiungo la percentuale di sabbia persa al destinatario
            dati.aggiornaWidthHeightSabbiaById(rigaDest,colonnaDest);// aggiornamento larghezza sabbia destinatario
        }
    }
    
    

    public boolean isBallP() {
        return dati.getPalline(rigaScatola,colonnaScatola);
    }

    public void setBallP(boolean ballP) {
        dati.setPalline(rigaScatola,colonnaScatola, ballP);
    }    
    

}
