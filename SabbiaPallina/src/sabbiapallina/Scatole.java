/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sabbiapallina;

/**
 *
 * @author galimberti_francesco
 */
public class Scatole {
    private Scatola array[][];

    public Scatole(int numScatoleRighe,int numScatoleColonne) {
        this.array=new Scatola[numScatoleRighe][numScatoleColonne];
        
        for (int r = 0; r < numScatoleRighe; r++) {
            for (int c = 0; c < numScatoleColonne; c++) {
                array[r][c]=new Scatola(200,200,200, r*200, c*200);
            }
        }
    }

    public Scatola[][] getArray() {
        return array;
    }

    public void setArray(Scatola[][] array) {
        this.array = array;
    }
    
    public Scatola getScatola(int riga, int colonna) {
        return array[riga][colonna];
    }
    
    
}
