/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

import static sabbiapallina.NB_ProcessingScatola.dati;

/**
 *
 * @author Galimberti Francesco
 */
public class Pallina {

    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo float che rappresenta la posizione della pallina
     * sull'asse delle x.
     */
    private float posX;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo float che rappresenta la posizione della pallina
     * sull'asse delle y.
     */
    private float posY;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo double che rappresenta la velocità della pallina
     * sull'asse delle x.
     */
    private double velX;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo double che rappresenta la velocità della pallina
     * sull'asse delle y.
     */
    private double velY;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo intero che rappresenta il raggio di grandezza
     * della pallina.
     */
    private int Raggio;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo intero che rappresenta la direzione della
     * pallina sull'asse delle x.
     */
    private int direzioneX;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo intero che rappresenta la direzione della
     * pallina sull'asse delle y.
     */
    private int direzioneY;
    /**
     * @author Galimberti Francesco
     *
     * @brief Oggetto di tipo DatiCondivisi che serve per richiamare i metodi
     * della classe DatiCondivisi.
     */

    private int contX = 0;
    private int contY = 0;

    /**
     * @author Galimberti Francesco
     *
     * @brief Costruttore senza parametri che all'inizio dell'esecuzione del
     * programma posiziona la pallina al centro della scatola e imposta le sue
     * velocità a 0.
     */
    public Pallina() {
        this.Raggio = 20;
        this.posX = 100;          //Cambiare in base a lunghezza della scatola
        this.posY = 100;          //Cambiare in base a larghezza della scatola
        this.velX = 0;
        this.velY = 0;
        /*Genero un numero random tra 0 e 2 (0,1), poi calcolo il resto della divisione con il numero 2 facendo il modulo(%),
          in questo modo ho il 50% di possibilità che la direzione sia 1(da sinistra a destra) o -1(da destra a sinistra)
         */
        if (dati.getInclinazioneX() < 0) {
            direzioneX = -1;       //destra --> sinistra
        } else {
            direzioneX = 1;      //sinistra --> destra
        }

        if (dati.getInclinazioneY() < 0) {
            direzioneY = -1;       //basso --> alto
        } else {
            direzioneY = 1;      //alto --> basso
        }
        /*Uguale a sopra*/
 /*if((int) ((Math.random()*2)%2)==0) {
            direzioneY=1;       //sopra --> sotto
        }
        else {                                              //PER FUTURA INCLINAZIONE SULL'ASSE DELLE Y
            direzioneX=-1;      //sotto --> sopra
        }
        
        WIDTH_SCREEN=900;
        HEIGHT_SCREEN=900;
         */
    }

    public Pallina(DatiCondivisi ptrdati, float posX, float posY) {
        this.Raggio = 20;
        this.posX = posX;          //Cambiare in base a lunghezza della scatola
        this.posY = posY;          //Cambiare in base a larghezza della scatola
        this.velX = 0;
        this.velY = 0;

        if (dati.getInclinazioneX() < 0) {
            direzioneX = -1;       //sinistra --> destra
        } else {
            direzioneX = 1;      //destra --> sinistra
        }

        if (dati.getInclinazioneY() < 0) {
            direzioneY = -1;       //basso --> alto
        } else {
            direzioneY = 1;      //alto --> basso
        }
        /*Uguale a sopra*/
 /*if((int) ((Math.random()*2)%2)==0) {
            direzioneY=1;       //sopra --> sotto
        }
        else {                                              //PER FUTURA INCLINAZIONE SULL'ASSE DELLE Y
            direzioneX=-1;      //sotto --> sopra
        }
        
        WIDTH_SCREEN=900;
        HEIGHT_SCREEN=900;
         */
    }

    /**
     * @author Galimberti Francesco
     * @param rigaScatola Parametro che indica la riga identificativa della
     * scatola in cui la pallina si muove.
     * @param colonnaScatola Parametro che indica la colonna identificativa
     * della scatola in cui la pallina si muove.
     *
     * @brief Metodo che si occupa di muovere la pallina.
     *
     * In questo metodo in base all'inclinazione viene cambiata la direzione
     * della pallina. Inoltre la posizione della pallina viene aggiornata in
     * base alla velocitò di quest'ultima. Più la scatola è inclinata più la
     * pallina si sposterà velocemente. Quando la pallina tocca il bordo della
     * scatola, se ha una velocità sufficiente, passerà nell'altra scatola.
     */
    
    
    
    public void move(int rigaScatola, int colonnaScatola, int inclinazioneX, int inclinazioneY) {
        /**
         * Movimento sulle X
         */
        if (inclinazioneX >= 15) {
            direzioneX = 1;
        } else if (inclinazioneX <= -15) {
            direzioneX = -1;
        }

        posX = posX + (float) ((velX * direzioneX) * (inclinazioneX / 10));
        posY = posY + (float) (velY * direzioneY);
        if (posX >= (200 + ((200 * colonnaScatola) - (Raggio / 2)))) {

            if ((velX * (inclinazioneX / 10)) > 1) {
                dati.setSposta(true);
            }

            posX = 200 + (200 * colonnaScatola) - (Raggio / 2);
            velX = 0;
        }

        if ((posX <= (0 + (200 * colonnaScatola) + (Raggio / 2)))) {

            if (((velX * (inclinazioneX / 10)) * -1) < -1) {
                dati.setSposta(true);
            }
            posX = 0 + (200 * colonnaScatola) + (Raggio / 2);
            velX = 0;
        }

        /**
         * Movimento sulle Y
         */
        if (inclinazioneY >= 15) {
            direzioneY = 1;
        }else if (inclinazioneY <= -15) {
            direzioneY = -1;
        }

        posY = posY + (float) ((velY * direzioneY) * (inclinazioneY / 10));
        posX = posX + (float) (velX * direzioneX);
        if (posY >= (200 + ((200 * rigaScatola) - (Raggio / 2)))) {

            if ((velY * (inclinazioneY / 10)) > 1) {
                dati.setSposta(true);
            }

            posY = 200 + (200 * rigaScatola) - (Raggio / 2);
            velY = 0;
        }

        if ((posY <= (0 + (200 * rigaScatola) + (Raggio / 2)))) {

            if (((velY * (inclinazioneY / 10)) * -1) < -1) {
                dati.setSposta(true);
            }
            posY = 0 + (200 * rigaScatola) + (Raggio / 2);
            velY = 0;
        }

        /*if(posY>=HEIGHT_SCREEN-Raggio) {
            posY=HEIGHT_SCREEN-Raggio;              //FUTURO
        }       
         */
    }

    public void incRaggio() {
        this.Raggio +=1;
    }
    
    public void decRaggio() {
        this.Raggio -=1;
    }

    public int getRaggio() {
        return Raggio;
    }
    
    
    
    public void jumpUP(){
        this.Raggio +=1;
    }
    
    public void jumpDOWN(){
        this.Raggio-=1;
    }


    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che incrementa la velocità della pallina sull'asse delle x.
     *
     * In questo metodo se la variabile contatore è pari a 10 la velocità
     * sull'asse delle x viene incrementata di 0.02. Infine se la velocità
     * sull'asse delle x è maggiore di 0.2, quest'ultima viene impostata a 0.2
     * (velocità massima verso destra).
     */
    public void IncrementaVelocitàX() {
        if (contX == 10) {                          //CONTATORE CHE SERVE PER INCREMENTARE LA VELOCITA DELLA PALLINA SOLO 1 VOLTA OGNI 20 RICHIAMI DEL METODO
            velX += 0.05;
            if (velX > 0.6) {
                velX = 0.6;
            }
            contX = 0;
        } else {
            contX++;
        }

    }

    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che incrementa la velocità della pallina sull'asse delle y.
     */
    public void IncrementaVelocitàY() {
        if (contY == 10) {                          //CONTATORE CHE SERVE PER INCREMENTARE LA VELOCITA DELLA PALLINA SOLO 1 VOLTA OGNI 20 RICHIAMI DEL METODO
            velY += 0.05;
            if (velY > 0.6) {
                velY = 0.6;
            }
            contY = 0;
        } else {
            contY++;
        }
    }

    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che decrementa la velocità della pallina sull'asse delle x.
     *
     * In questo metodo se la variabile contatore è pari a 10 la velocità
     * sull'asse delle x viene decrementata di 0.02. Infine se la velocità
     * sull'asse delle x è minore di -0.2, quest'ultima viene impostata a -0.2
     * (velocità massima verso sinistra).
     */
    public void DecrementaVelocitàX() {
        if (contX == 10) {                          //CONTATORE CHE SERVE PER DECREMENTARE LA VELOCITA DELLA PALLINA SOLO 1 VOLTA OGNI 20 RICHIAMI DEL METODO
            velX -= 0.05;                        //DecrementaVelocitàX(), IN QUESTO MODO SI EVITA CHE LA VELOCITA NON DIMINUISCA TROPPO VELOCEMENTE
            if (velX < -0.6) {
                velX = -0.6;
            }
            contX = 0;
        } else {
            contX++;
        }
    }

    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che decrementa la velocità della pallina sull'asse delle y.
     */
    public void DecrementaVelocitàY() {
        if (contY == 10) {                          //CONTATORE CHE SERVE PER DECREMENTARE LA VELOCITA DELLA PALLINA SOLO 1 VOLTA OGNI 20 RICHIAMI DEL METODO
            velY -= 0.05;                        //DecrementaVelocitàX(), IN QUESTO MODO SI EVITA CHE LA VELOCITA NON DIMINUISCA TROPPO VELOCEMENTE
            if (velY < -0.6) {
                velY = -0.6;
            }
            contY = 0;
        } else {
            contY++;
        }
    }

    public String VisualizzaInfo() {
        return "PosizioneX: " + String.valueOf(posX) + "/PosizioneY:" + String.valueOf(posY) + "/VelocitàX:" + String.valueOf(velX) + "/VelocitàY:" + String.valueOf(velY);
    }

}
