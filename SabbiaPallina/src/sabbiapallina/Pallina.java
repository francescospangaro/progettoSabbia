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
    private int rad;
    private int velX;
    private int velY;
    private int dirX;
    private int dirY;

    public Pallina(int rad, int velX, int velY, int dirX, int dirY) {
        this.rad = rad;
        this.velX = velX;
        this.velY = velY;
        this.dirX = dirX;
        this.dirY = dirY;
    }

    public Pallina() {
    }

    public int getRad() {
        return rad;
    }

    public void setRad(int rad) {
        this.rad = rad;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }
    
    
}
