/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;

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

    //sabbia contenuta nella scatola
    private Sabbia sabbia;
    //Pallina pallina;

    //dimensioni della scatola
    private final int widthScatola;
    private final int heigthScatola;

    public ThScatola(int percSabbia, DatiCondivisi dati, int rigaScatola, int colonnaScatola, int wS, int hS, boolean ballP) {
        this.dati = dati;
        this.rigaScatola = rigaScatola;
        this.colonnaScatola = colonnaScatola;

        this.heigthScatola = hS;
        this.widthScatola = wS;

        //viene inizializzata la sabbia, ogni Thread prende la sabbia corrispondente dai datiCondivisi
        sabbia = dati.getSabbiaById(rigaScatola, colonnaScatola);
        //viene settata la perc di sabbia contenuta nella scatola
        sabbia.setPercentuale(percSabbia);
        //richiama il metodo per aggiornare la larghezza della sabbia 
        sabbia.visualizzazioneSabbia(widthScatola);

        this.dati.setPalline(rigaScatola, colonnaScatola, ballP);
    }

    public void run() {
        try {
            while (dati.isRunning()) {

                //viene richiamato il metodo per aggiornare la sabbia "persa" in base all`inclinazioneX
                sabbia.aggiornaSabbia(dati.getGiroscopio().getInclinazioneX());

                //attendo 10ms
                Thread.sleep(10);

                //se inclinazione positiva(verso dx)
                if (dati.getGiroscopio().getInclinazioneX() >= 15) {
                    //la scatola nel quale devo spostare la sabbia e` quella successiva
                    colonnaDest = colonnaScatola + 1;
                    rigaDest = rigaScatola;
                    //System.out.println("Th"+idScatola+"--> Dest"+idDest);         //->output di controllo

                    //viene richiamato il metodo per gestire lo spostamento della sabbia verso dx
                    versoDestra(sabbia.getDiminuzione());
                }

                //se inclinazione negativa (verso sx)
                if (dati.getGiroscopio().getInclinazioneX() <= -15) {
                    //la scatola in cui spostare la sabbia e` quella precedente
                    colonnaDest = colonnaScatola - 1;
                    rigaDest = rigaScatola;
                    //System.out.println("Th"+idScatola+"--> Dest"+idDest);         //->output di controllo

                    //viene richiamato il metodo per gestire lo spostamento della sabbia verso dx
                    versoSinistra(sabbia.getDiminuzione());
                }

                //dopo avere gestito il movimento resetto la sabbia persa
                sabbia.resetDiminuzione();

                //System.out.println("rigaScatola" + rigaScatola + " colonnaScatola" + colonnaScatola + "  percSabbia" + sabbia.getPercentuale());

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(ThScatola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //gestione spostamento verso dx(inclinazione positiva)
    private void versoDestra(float dim) {
        //se la scatola in cui devo spostare la sabbia persa esiste(idDest non puo essere uguale al numero delle colonne avendo id che va da 0 a numColonne-1)
        if (colonnaDest != dati.getNumScatoleColonne()) {
            try {
                //se ho della sabbia persa, la scatola e` piena di sabbia e la scatola precedente non ha piu sabbia, oppure, ho della sabbia persa, la scatola ha della sabbia e il destinatario non e` ancora pieno
                if (((dim > 0) && (sabbia.getPercentuale() == 100) && (dati.getSabbiaById(rigaScatola,colonnaScatola - 1).getPercentuale() == 0)) || ((dim > 0) && (dati.getSabbiaById(rigaScatola,colonnaScatola - 1).getPercentuale() == 0) && (sabbia.getPercentuale() != 0) && (dati.getSabbiaById(rigaDest,colonnaDest).getPercentuale() <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //se ho della sabbia persa e la scatola e` piena di sabbia
                if (((dim > 0) && (sabbia.getPercentuale() == 100)) || ((sabbia.getPercentuale() != 0) && (dati.getSabbiaById(rigaDest,colonnaDest).getPercentuale() <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento                }
                }
            }
        }
    }
    //gestione spostamento verso sx(inclinazione negativa)

    private void versoSinistra(float dim) {
        //se la scatola in cui devo spostare la sabbia persa esiste(idDest non puo essere uguale a -1 avendo id che va da 0 a numColonne-1)
        if (colonnaDest != -1) {
            try {
                //se ho della sabbia persa, la scatola e` piena di sabbia e la scatola successiva non ha piu sabbia, oppure, ho della sabbia persa, la scatola ha della sabbia e il destinatario non e` ancora pieno
                if (((dim > 0) && (sabbia.getPercentuale() == 100) && (dati.getSabbiaById(rigaScatola,colonnaScatola + 1).getPercentuale() == 0)) || ((dim > 0) && (dati.getSabbiaById(rigaScatola,colonnaScatola + 1).getPercentuale() == 0) && (sabbia.getPercentuale() != 0) && (dati.getSabbiaById(rigaDest,colonnaDest).getPercentuale() <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //se ho della sabbia persa e la scatola e` piena di sabbia
                if ((dim > 0) && (sabbia.getPercentuale() == 100) || ((sabbia.getPercentuale() != 0) && (dati.getSabbiaById(rigaDest,colonnaDest).getPercentuale() <= 100))) {
                    procedi(dim);//si procede alla  gestione dello spostamento

                }
            }
        }
    }

    //metodo che permette di continare a gestire lo spostamento della sabbia, in base alla percentuale di sabbia persa
    private void procedi(float sm) {
        int perc = sabbia.getPercentuale() - (int) sm;//nuova percentuale
        //se si perde piu sabbia di quella che si ha
        if (perc < 0) {
            sabbia.setPercentuale(0);//la scatola e` vuota
            sabbia.visualizzazioneSabbia(widthScatola);                         // aggiornamento larghezza sabbia
            dati.getSabbiaById(rigaDest,colonnaDest).setPercentuale(100);// il destinatario sara pieno di sabbia
            dati.getSabbiaById(rigaDest,colonnaDest).visualizzazioneSabbia(widthScatola);            // aggiornamento larghezza sabbia destinatario

            //altrimenti
        } else {
            sabbia.setPercentuale(perc);//cambia la sabbia contenuta nella scatola con la nuova percentuale
            sabbia.visualizzazioneSabbia(widthScatola);                         // aggiornamento larghezza sabbia
            dati.getSabbiaById(rigaDest,colonnaDest).aggiungiSabbia((int) sm);//aggiungo la percentuale di sabbia persa al destinatario
            dati.getSabbiaById(rigaDest,colonnaDest).visualizzazioneSabbia(widthScatola);            // aggiornamento larghezza sabbia destinatario
        }
    }

    public DatiCondivisi getDati() {
        return dati;
    }

    public Sabbia getSabbia() {
        return sabbia;
    }

    public int getWidthScatola() {
        return widthScatola;
    }

    public int getHeigthScatola() {
        return heigthScatola;
    }

    public boolean isBallP() {
        return dati.getPalline(rigaScatola,colonnaScatola);
    }

    public void setBallP(boolean ballP) {
        dati.setPalline(rigaScatola,colonnaScatola, ballP);
    }

    public int getRigaScatola() {
        return rigaScatola;
    }

    public int getColonnaScatola() {
        return colonnaScatola;
    }

    public int getRigaDest() {
        return rigaDest;
    }

    public int getColonnaDest() {
        return colonnaDest;
    }
    
    

}
