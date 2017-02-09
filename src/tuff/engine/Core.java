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

    public void init() {
        ModelFactory modelFactory = new ModelFactory();
        model = modelFactory.fabricate(parameters.getModel());

        createGrid();

    }

    public void simulateAllDensities() {
        float density = parameters.getInitialDensity();
        float deltaDensity = parameters.getDeltaDensity();
        float finalDensity = parameters.getFinalDensity();
        while (density < finalDensity) {

            simulateDensity(density);

            density = density + deltaDensity;
        }

    }

    public void simulateDensity(float d) {
        setInitialCondition(d);
        int simulationTime = parameters.getSimulationTime();
        System.out.println("|---------|");
        System.out.print("|");
        for (int i = 0; i < simulationTime; i++) {
            if ((i == (simulationTime / 10)) || (i == (simulationTime / 20)) || (i == (simulationTime / 30))||(i == (simulationTime / 40)) || (i == (simulationTime / 50)) || (i == (simulationTime / 60))||(i == (simulationTime / 70)) || (i == (simulationTime / 80)) || (i == (simulationTime / 90))) {
                System.out.print("-");
            }
            
            iterate();
        }
        System.out.print("|");
        System.out.println("");

    }

    public void iterate() {
//        try {
//            Thread.sleep(1);
//        } catch (Exception e) {
//            
//        }
       
        for (int i = 0; i < vehicles.size(); i++) {
            //System.out.print(i);
//            if(i==vehicles.size()-1&&(vehicles.get(0).getDistanceToFront()!=0)){
//                System.out.print(" "+vehicles.get(0).getDistanceToFront());
//            }
            model.apply(vehicles.get(i));

        }
//        System.out.println("");
//        for (int i = 0; i < grid.getGrid().length; i++) {
//            if(grid.getGrid()[i]==-1){
//               System.out.print(" "); 
//            }else{
//            System.out.print("o");
//            }
//            
//        }
        update();

    }

    public void update() {
        grid.updateVehiclesOnGrid(vehicles);
        for (int i = 0; i < vehicles.size(); i++) {
            vehicles.get(i).updateInfo();
            
        }

    }

    //will create the grid with the parameters
    private void createGrid() {
        grid = new Grid(parameters.getCellsInX());
        grid.init();

    }

    //will set the inicial condition according to the inicial density and  will create a number of cars of
    //each profile according to the given occurence.
    private void setInitialCondition(float d) {
        //clears the vehicles array
        vehicles.clear();
        //clears the gris positions
        grid.init();
        //number of cells that will be occupied in this density
        int occupiedCells = (int) (parameters.getCellsInX() * d);
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("Density: " + d + " Occupied Cells: " + occupiedCells + " out of " + parameters.getCellsInX());
        //will store here the numberof cars in this density or each profile
        int[] nOfProfileCars = new int[profiles.size()];
        int totalCarsToInit = 0;
        //will loop in each profile type
        for (int i = 0; i < profiles.size(); i++) {
            //the set occurence for that type
            double occurrence = profiles.get(i).getPercentageOccurrence();
            //number of cars according to the occurence and, the cells to ocupy and the size of that  profile.
            int numberOfCars = (int) (((int) (occupiedCells * occurrence)) / profiles.get(i).getSize());
            System.out.println("\nProfile: " + profiles.get(i).toString());
            System.out.println("Cars in this simulation: " + numberOfCars);
            nOfProfileCars[i] = numberOfCars;
            totalCarsToInit += numberOfCars;

        }
        //Creates cars mixing the profiles
        int idCount = 0;
        //stops when there is the total numbers of cars for each profile
        while (vehicles.size() < totalCarsToInit) {
            Random rand = new Random();
            int p = rand.nextInt(2);
            if (nOfProfileCars[p] > 0) {
                vehicles.add(new Vehicle(grid, this, profiles.get(p), idCount));
                nOfProfileCars[p]--;
                idCount++;

            }

        }

        System.out.println("\nTotal Vehicles Loaded: " + vehicles.size());

        //set the cars neighbours
        setNeighbours();
        grid.placeVehiclesOnGrid(vehicles);

        //grid.printGrid();
    }

    //set the neighbours in a circular way
    public void setNeighbours() {
        for (int i = 0; i < vehicles.size(); i++) {
            //case when its the first vehicle
            if (i == 0) {
                vehicles.get(i).setBackNeighbour(vehicles.get(vehicles.size() - 1));
                vehicles.get(i).setFrontNeighbour(vehicles.get(i + 1));
                //case when its the last vehicle    
            } else if (i == vehicles.size() - 1) {
                vehicles.get(i).setBackNeighbour(vehicles.get(i - 1));
                vehicles.get(i).setFrontNeighbour(vehicles.get(0));
                //case when its a normal vehicle in the middle
            } else {
                vehicles.get(i).setBackNeighbour(vehicles.get(i - 1));
                vehicles.get(i).setFrontNeighbour(vehicles.get(i + 1));

            }

        }

    }

    public Profile createProfile(FDPProvider fdpProvider, String name, int size, int velMax, int ahead, int safeDistance, int velIncrement, double percentageOccurrence, float alphaAcc, float betaAcc, float alphaAnt, float betaAnt) {
        Profile newProfile = new Profile(fdpProvider, name, size, velMax, ahead, safeDistance, velIncrement, percentageOccurrence, alphaAcc, betaAcc, alphaAnt, betaAnt);
        profiles.add(newProfile);
        return newProfile;
    }

}
