/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

import java.util.ArrayList;
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
            grid[i] = -1;

        }

    }

    public void clear() {

        init();
    }

    public void placeVehiclesOnGrid(ArrayList<Vehicle> vehicles) {
        System.out.println(grid.length);
        int xPosition = grid.length - 1;
        System.out.println(xPosition);
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            Vehicle v = vehicles.get(i);
            v.setGridXPosition(xPosition);
            for (int j = 0; j < v.getProfile().getSize(); j++) {
                grid[xPosition] = v.getId();
                xPosition--;

            }

        }

    }

    public void printGrid() {
        System.out.println("Grid");
        for (int i = grid.length-1; i >=0; i--) {

            System.out.print(grid[i] + " ");

        }
        System.out.println("");

    }

}
