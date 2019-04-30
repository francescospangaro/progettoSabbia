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
    int raggio;          
    float x, y;       
    double velocitax;      
    double velocitay;      

    int movimentox;     
    int movimentoy;     
    int widthScreen;    
    int heightScreen;   

    public Pallina() {
        raggio = 10;
        velocitax = 5;   
        velocitay = 2;   

        if ((int) (Math.random() * 200) % 2 == 0) {
            movimentox = 1;     
        } else {
            movimentox = -1;  
        }
        if ((int) (Math.random() * 200) % 2 == 0) {
            movimentoy = 1;  
        } else {
            movimentoy = -1;  
        }
        x = 0;
        y = 0;
        widthScreen = 0;
        heightScreen = 0;
    }

    public void move() {
        x = x + (float) (velocitax * movimentox);
        y = y + (float) (velocitay * movimentoy);

        if (x > widthScreen - raggio || x < raggio) {
            movimentox *= -1;
        }
        if (y > heightScreen - raggio || y < raggio) {
            movimentoy *= -1;
        }
    }

    void setScreen(int width, int height) {
        widthScreen = width;
        heightScreen = height;
        x = widthScreen / 2 + (int) (Math.random() * 50);;
        y = heightScreen / 2;
    }

    public float getX() {
        return x;
    }

    public int getRaggio() {
        return raggio;
    }

    public float getY() {
        return y;
    }
}
