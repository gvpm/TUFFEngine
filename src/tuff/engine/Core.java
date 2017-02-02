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
public class Core {
    
    ArrayList<Vehicle> vehicles;
    ArrayList<Profile> profiles;
    
    Grid grid;
    SimulationParameters parameters;
    
    Model model;

    public Core(SimulationParameters parameters) {
        this.parameters = parameters;
        profiles = new ArrayList<>();
        vehicles = new ArrayList<>();
    }
    
    
    public void init(){
        ModelFactory modelFactory = new ModelFactory();
        Model model = modelFactory.fabricate(parameters.getModel());
        
        createGrid();
        setInitialCondition();
        
    }
    //will create the grid with the parameters
    private void createGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //will set the inicial condition according to the inicial density and  will create a number of cars of
    //each profile according to the given occurence.
    private void setInitialCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Profile createProfile(FDPProvider fdpProvider, String name, int size, int velMax, int ahead, int safeDistance, int velIncrement, int percentageOccurrence, float alphaAcc, float betaAcc, float alphaAnt, float betaAnt) {
        Profile newProfile = new Profile(fdpProvider, name, size, velMax, ahead, safeDistance, velIncrement, percentageOccurrence, alphaAcc, betaAcc, alphaAnt, betaAnt);
        profiles.add(newProfile);
        return newProfile;
    }
    
    
    
    
    
}
