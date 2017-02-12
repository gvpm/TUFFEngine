/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

import java.util.ArrayList;

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

    public int getFromPosition(int i) {
        return grid[i];
    }

    public int getNextXPosition(int i) {
        if (i == grid.length - 1) {
            return 0;
        } else {
            return i + 1;
        }
    }

    public int getPreviousXPosition(int i) {
        if (i == 0) {
            return grid.length - 1;
        } else {
            return i - 1;
        }
    }

    public void clear() {

        init();
    }

    public void updateVehiclesOnGrid(ArrayList<Vehicle> vehicles) {
        clear();
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            Vehicle v = vehicles.get(i);
            int position = v.getNewGridXPosition();

            for (int j = 0; j < v.getSize(); j++) {

                if (getFromPosition(position) != -1) {
                    throw new UnsupportedOperationException("Conflict between car " + getFromPosition(position) + " and " + v.getId());

                } else {
                    grid[position] = v.getId();
                    position = getPreviousXPosition(position);
                    //case of conflict
                }

            }

        }

    }

    public void placeVehiclesOnGrid(ArrayList<Vehicle> vehicles) {

        int xPosition = grid.length - 1;

        for (int i = vehicles.size() - 1; i >= 0; i--) {
            Vehicle v = vehicles.get(i);
            v.setGridXPosition(xPosition);
            for (int j = 0; j < v.getProfile().getSize(); j++) {
                grid[xPosition] = v.getId();
                xPosition--;

            }

        }

    }

    public int[] getGrid() {
        return grid;
    }

    public int getNewXPostition(int x, int vel) {

        int newXPosition = x;
        for (int i = 0; i < vel; i++) {

            newXPosition = getNextXPosition(newXPosition);

        }

        return newXPosition;

    }

    public void printGrid() {
        System.out.println("Grid");
        for (int i = grid.length - 1; i >= 0; i--) {

            System.out.print(grid[i] + " ");

        }
        System.out.println("");

    }

}
