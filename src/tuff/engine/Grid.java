/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

import java.util.Random;

/**
 *
 * @author gvpm
 */
public class Grid {

    int[] grid;

    public Grid(int size) {
        grid = new int[size];

    }
    //puts 0 in all positions of the grid
    public void init() {
        for (int i = 0; i < grid.length; i++) {
            grid[i] = 0;

        }

    }

    public void clear() {

        init();
    }

    //provides a random position with "size-1"cells free in the back
    //chooses a random position and cheks it all the "size" cells are free, looping "size"times and
    //checking the positions, dels with the case that the random number is in the begining of the vector
    public int getRandomFreePosition(int size) {
        boolean found = false;
        int count = 0;
        int i=-1;
        while (!found) {
            Random r = new Random();
            i = r.nextInt(grid.length);
            if(grid[i]==0){
                int aux;
                //case when i = 0 will look the end of the array
                if(i==0){
                    aux = grid.length;
                }else{
                    aux = i;
                }
                
                for (int j = 1; j < size; j++) {
                    count+=grid[aux-j];
                    
                }
                
                if(count == 0){
                    found = true;
                }else{
                    count = 0;
                }
                
            }
        }

        return i;
    }

}
