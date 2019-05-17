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
public class Pallina {

    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo float che rappresenta la posizione della
     * pallina sull'asse delle x.
     */
    private float posX;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo float che rappresenta la posizione della
     * pallina sull'asse delle y.
     */
    private float posY;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo double che rappresenta la velocità della
     * pallina sull'asse delle x.
     */
    private double velX;
    /**
     * @author Galimberti Francesco
     *
     * @brief Attributo di tipo double che rappresenta la velocità della
     * pallina sull'asse delle y.
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
    private DatiCondivisi dati;

    private int cont = 0;

    /**
     * @author Galimberti Francesco
     *
     * @brief Costruttore senza parametri che all'inizio dell'esecuzione del programma posiziona la pallina al centro 
     * della scatola e imposta le sue velocità a 0.
     */
    public Pallina(DatiCondivisi dati) {
        this.dati = dati;
        this.Raggio = 20;
        this.posX = 100;          //Cambiare in base a lunghezza della scatola
        this.posY = 100;          //Cambiare in base a larghezza della scatola
        this.velX = 0;
        this.velY = 0;
        /*Genero un numero random tra 0 e 2 (0,1), poi calcolo il resto della divisione con il numero 2 facendo il modulo(%),
          in questo modo ho il 50% di possibilità che la direzione sia 1(da sinistra a destra) o -1(da destra a sinistra)
         */
        if (this.dati.getGiroscopio().getInclinazioneX() < 0) {
            direzioneX = -1;       //sinistra --> destra
        } else {
            direzioneX = 1;      //destra --> sinistra
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
    
    public Pallina(DatiCondivisi dati,int posX, int posY) {
        this.dati = dati;
        this.Raggio = 20;
        this.posX = posX;          //Cambiare in base a lunghezza della scatola
        this.posY = posY;          //Cambiare in base a larghezza della scatola
        this.velX = 0;
        this.velY = 0;
        
        if (this.dati.getGiroscopio().getInclinazioneX() < 0) {
            direzioneX = -1;       //sinistra --> destra
        } else {
            direzioneX = 1;      //destra --> sinistra
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
     *
     * @brief Metodo get che fa ritornare l'oggetto di tipo DatiCondivisi.
     */
    public DatiCondivisi getDati() {
        return dati;
    }

    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore dell'oggetto di tipo DatiCondivisi.
     */
    public void setDati(DatiCondivisi ptrDati) {
        this.dati = ptrDati;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo get che fa ritornare il valore della variabile cont.
     */
    public int getCont() {
        return cont;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore della variabile cont.
     * 
     */
    public void setCont(int cont) {
        this.cont = cont;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo get che fa ritornare il valore della direzione della pallina sull'asse delle x.
     * 
     * @return direzioneX attributo che rappresenta la direzione della pallina sull'asse delle x.
     */
    public int getDirezioneX() {
        return direzioneX;
    }

    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore della direzione della pallina sull'asse delle x.
     * 
     * @param direzioneX Parametro che indica la direzione della pallina sull'asse delle x.
     */
    public void setDirezioneX(int direzioneX) {
        this.direzioneX = direzioneX;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo get che fa ritornare il valore della direzione della pallina sull'asse delle y.
     * 
     * @return direzioneY attributo che rappresenta la direzione della pallina sull'asse delle y.
     */
    public int getDirezioneY() {
        return direzioneY;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore della direzione della pallina sull'asse delle y.
     * 
     * @param direzioneY Parametro che indica la direzione della pallina sull'asse delle y.
     */
    public void setDirezioneY(int direzioneY) {
        this.direzioneY = direzioneY;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo get che fa ritornare il valore del raggio della pallina.
     * 
     * @return Raggio attributo che rappresenta il raggio di grandezza della pallina.
     */
    public int getRaggio() {
        return Raggio;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore del raggio della pallina.
     * 
     * @param Raggio Parametro che indica il raggio di grandezza della pallina.
     */
    public void setRaggio(int Raggio) {
        this.Raggio = Raggio;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo get che fa ritornare il valore della posizione della pallina sull'asse delle x.
     * 
     * @return posX attributo che rappresenta la posizione della pallina sull'asse delle x.
     */
    public float getPosX() {
        return posX;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore della posizione della pallina sull'asse delle x.
     * 
     * @param posX Parametro che indica la posizione della pallina sull'asse delle x.
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo get che fa ritornare il valore della posizione della pallina sull'asse delle y.
     * 
     * @return posY attributo che rappresenta la posizione della pallina sull'asse delle x.
     */
    public float getPosY() {
        return posY;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore della posizione della pallina sull'asse delle y.
     * 
     * @param posY Parametro che indica la posizione della pallina sull'asse delle y.
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo get che fa ritornare il valore della velocità della pallina sull'asse delle x.
     * 
     * @return velX attributo che rappresenta la velocità della pallina sull'asse delle x.
     */
    public double getVelX() {
        return velX;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore della velocità della pallina sull'asse delle x.
     * 
     * @param velX Parametro che indica la velocità della pallina sull'asse delle x.
     */
    public void setVelX(double velX) {
        this.velX = velX;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo get che fa ritornare il valore della velocità della pallina sull'asse delle y.
     * 
     * @return velY attributo che rappresenta la velocità della pallina sull'asse delle x.
     */
    public double getVelY() {
        return velY;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo set che imposta il valore della velocità della pallina sull'asse delle y.
     * 
     * @param velY Parametro che indica la velocità della pallina sull'asse delle y.
     */
    public void setVelY(double velY) {
        this.velY = velY;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che si occupa di muovere la pallina.
     * 
     * In questo metodo in base all'inclinazione viene cambiata la direzione della pallina.
     * Inoltre la posizione della pallina viene aggiornata in base alla velocitò di quest'ultima.
     * Più la scatola è inclinata più la pallina si sposterà velocemente.
     * Quando la pallina tocca il bordo della scatola, se ha una velocità sufficiente, passerà nell'altra scatola.
     * 
     * @param idBox Parametro che indica il codice identificativo della scatola in cui la pallina si muove.
     */
    public void Move(int idBox, int inclinazioneX) {
        if (inclinazioneX >= 15) {
            direzioneX = 1;
        }
        if (inclinazioneX <= -15) {
            direzioneX = -1;
        }

        posX = posX + (float) ((velX * direzioneX) * (inclinazioneX / 10));      
        posY = posY + (float) (velY * direzioneY);                                        
        if (posX >= (200 + ((200 * idBox) - (Raggio / 2)))) {             

            if ((velX * (inclinazioneX / 10)) > 1) {
                dati.setSposta(true);
            }

            posX = 200 + (200 * idBox) - (Raggio / 2);
            velX = 0;
        }


        if ((posX <= (0 + (200 * idBox) + (Raggio / 2)))) {

            if (((velX * (inclinazioneX / 10))* -1) < -1) {
                dati.setSposta(true);
            }
            posX = 0 + (200 * idBox) + (Raggio / 2);
            velX = 0;
        }

        /*if(posY>=HEIGHT_SCREEN-Raggio) {
            posY=HEIGHT_SCREEN-Raggio;              //FUTURO
        }       
         */
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che incrementa la velocità della pallina sull'asse delle x.
     * 
     * In questo metodo se la variabile contatore è pari a 10 la velocità sull'asse delle x viene 
     * incrementata di 0.02.
     * Infine se la velocità sull'asse delle x è maggiore di 0.2, quest'ultima viene impostata a 0.2 (velocità massima verso destra).
     */
    public void IncrementaVelocitàX() {
        if (cont == 10) {                          //CONTATORE CHE SERVE PER INCREMENTARE LA VELOCITA DELLA PALLINA SOLO 1 VOLTA OGNI 20 RICHIAMI DEL METODO
            velX += 0.05;
            if (velX > 0.5) {
                velX = 0.5;                       
            }
            cont = 0;
        } else {
            cont++;
        }

    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che incrementa la velocità della pallina sull'asse delle y.
     */
    public void IncrementaVelocitàY() {
        velY += 0.3;
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che decrementa la velocità della pallina sull'asse delle x.
     * 
     * In questo metodo se la variabile contatore è pari a 10 la velocità sull'asse delle x viene 
     * decrementata di 0.02.
     * Infine se la velocità sull'asse delle x è minore di -0.2, quest'ultima viene impostata a -0.2 (velocità massima verso sinistra). 
     */
    public void DecrementaVelocitàX() {
        if (cont == 10) {                          //CONTATORE CHE SERVE PER DECREMENTARE LA VELOCITA DELLA PALLINA SOLO 1 VOLTA OGNI 20 RICHIAMI DEL METODO
            velX -= 0.05;                        //DecrementaVelocitàX(), IN QUESTO MODO SI EVITA CHE LA VELOCITA NON DIMINUISCA TROPPO VELOCEMENTE
            if (velX < -0.5) {
                velX = -0.5;                      
            }
            cont = 0;
        } else {
            cont++;
        }
    }
    
    /**
     * @author Galimberti Francesco
     *
     * @brief Metodo che decrementa la velocità della pallina sull'asse delle y.
     */
    public void DecrementaVelocitàY() {
        velY -= 0.3;
        if (velY < 0) {
            velY = 0;
        }
    }

    public String VisualizzaInfo() {
        return "PosizioneX: " + String.valueOf(posX) + "/PosizioneY:" + String.valueOf(posY) + "/VelocitàX:" + String.valueOf(velX) + "/VelocitàY:" + String.valueOf(velY);
    }
    
}