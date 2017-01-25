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

    

}
