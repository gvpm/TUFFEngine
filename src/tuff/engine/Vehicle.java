/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

/**
 *
 * @author gvpm
 */
public class Vehicle {
    
    Grid grid;
    Core core;
    Profile profile;
    int id;
    int gridXPosition;
    int gridYPosition;
    int oldGridXPosition;
    int oldGridYPosition;
    int newGridXPosition;
    int newGridYPosition;

    public Vehicle(Grid grid, Core core, Profile profile) {
        this.grid = grid;
        this.core = core;
        this.profile = profile;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGridXPosition() {
        return gridXPosition;
    }

    public void setGridXPosition(int gridXPosition) {
        this.gridXPosition = gridXPosition;
    }

    public int getGridYPosition() {
        return gridYPosition;
    }

    public void setGridYPosition(int gridYPosition) {
        this.gridYPosition = gridYPosition;
    }

    public int getOldGridXPosition() {
        return oldGridXPosition;
    }

    public void setOldGridXPosition(int oldGridXPosition) {
        this.oldGridXPosition = oldGridXPosition;
    }

    public int getOldGridYPosition() {
        return oldGridYPosition;
    }

    public void setOldGridYPosition(int oldGridYPosition) {
        this.oldGridYPosition = oldGridYPosition;
    }

    public int getNewGridXPosition() {
        return newGridXPosition;
    }

    public void setNewGridXPosition(int newGridXPosition) {
        this.newGridXPosition = newGridXPosition;
    }

    public int getNewGridYPosition() {
        return newGridYPosition;
    }

    public void setNewGridYPosition(int newGridYPosition) {
        this.newGridYPosition = newGridYPosition;
    }
    
    
    
    
            
            
    
}
