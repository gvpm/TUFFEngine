package tuff.engine;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Responsible to initiate, run, and log all the simulation, its the heart of
 * the program.
 *
 */
public class Core {

    ArrayList<Vehicle> vehicles;
    ArrayList<Profile> profiles;

    Grid grid;
    SimulationParameters parameters;

    Model model;
    DataExtractor dataExtractor;

    Logger logger;
    //----------------------------LOG
    FileWriter arq = null;
    PrintWriter gravarArq;
    //----------------------------

    public Core(SimulationParameters parameters) {
        this.parameters = parameters;
        profiles = new ArrayList<>();
        vehicles = new ArrayList<>();

    }

    //Initializes the core.
    public void init() {
        //Creates the model to be applied in the cars, using a factory.
        ModelFactory modelFactory = new ModelFactory();
        model = modelFactory.fabricate(parameters.getModel());
        //Creates a data extractor, will provide the information to be logged.
        dataExtractor = new DataExtractor(this);
        //Will log information 
        logger = new Logger("plotar");
        //Creates the grid
        createGrid();

    }

    /**
     * Will trigger the simulation to all densities, will go from inicialDensity
     * to finalDensity adding deltaDensity each time.
     *
     */
    public void simulateAllDensities() throws InterruptedException, ExecutionException {

        float density = parameters.getInitialDensity();
        float deltaDensity = parameters.getDeltaDensity();
        float finalDensity = parameters.getFinalDensity();

        //Triggers here all the simulations
        while (density < finalDensity) {

            simulateDensity(density);

            density = density + deltaDensity;
        }
        //End of simulation
        System.out.println("---------------------------------------------------------------");
        System.out.println("Simulation Ended");
        //-------------------------------LOG
        //Closing the log here.
        logger.closeLogger();
        //-------------------------------

    }

    /**
     * Simulates one specific density.
     *
     * @param d density to simulate
     */
    public void simulateDensity(float d) throws InterruptedException, ExecutionException {
        //Sets inicial condition or this density.
        setInitialCondition(d);

        int simulationTime = parameters.getSimulationTime();
        int statisticTime = parameters.getStatisticTime();
        int discardTime = parameters.getDiscardTime();
        int logTimeCounter = 0;

        //float lastPrinted = 0;
        //One step for each second  stated in simulation time
        for (int i = 0; i < simulationTime; i++) {
            /*
            int percentage = (int)(((float)i/(float)simulationTime)*(float)100);
            if((lastPrinted != percentage)&&percentage%10==0){
               //System.out.print(" "+percentage);
                System.out.print("-");
                lastPrinted = percentage;
            }
             */
            iterate();

            //LOG TIME
            //Will log every statisticTime, no logging the  initial discardTime
            if ((i > discardTime)) {
                logTimeCounter++;
                if ((logTimeCounter == statisticTime)) {

                    float roundD = (float) (Math.round(d * 100.0) / 100.0);
                    logger.logALine(dataExtractor.getFlow(roundD * 100), roundD * 100, dataExtractor.getAvgVel());
                    logTimeCounter = 0;
                }

            }

            //LOG TIME
            //Will log every statisticTime, no logging the  initial discardTime
//            if ((logTimeCounter == statisticTime)) {
//                if ((simulationTime > discardTime)) {
//                    float roundD = (float) (Math.round(d * 100.0) / 100.0);
//                    logger.logALine(dataExtractor.getFlow(roundD * 100), roundD * 100, dataExtractor.getAvgVel());
//                }
//                logTimeCounter = 0;
//            }
//            logTimeCounter++;
        }

    }

    /**
     * Main iteration, applies the model for each vehicle, updates the grid
     * after.
     */
    public void iterate() throws InterruptedException, ExecutionException {
//NORMAL ITERARION, COMMENTED, USING THREADS NOW
//        for (int i = 0; i < vehicles.size(); i++) {
//
//            model.apply(vehicles.get(i));
//
//        }
//          update();
        //Gets the number of processors available in the machine
        int processors = Runtime.getRuntime().availableProcessors();
        //Creates an executor service to run threads.
        ExecutorService es = Executors.newFixedThreadPool(processors);
        //An arrayList to store the tasks created
        ArrayList<Future<Integer>> tasks = new ArrayList<>();
        //Submits one thread per processor, dividing the vehicles in n(number of processors)groups
        for (int i = 1; i <= processors; i++) {
            Future<Integer> task = es.submit(new IterationCallable(this, i, processors, "Part"));
            tasks.add(task);
        }

        //Uses .get() in all the tasks, because it blockes the current one until tasks are finished.
        for (int i = 0; i < tasks.size(); i++) {
            Integer result = tasks.get(i).get();

        }
        //Closes the executor service
        es.shutdown();
        //Updates the grid
        update();

//        System.out.println("");
//        for (int i = 0; i < grid.getGrid().length; i++) {
//            if(grid.getGrid()[i]==-1){
//               System.out.print(" "); 
//            }else{
//            System.out.print("o");
//            }
//            
//        }
    }

    /**
     * Using a given number of parts and a part number, it divides the vehicle
     * in n parts and iterates the given part.
     *
     * @param partNumber number of the part
     * @param parts number of parts to divide the vehicles
     */
    public void iteratePart(int partNumber, int parts) {
        //total number of vehicles
        int total = vehicles.size();
        //number of vehicles in each part
        int partSize = vehicles.size() / parts;
        //end the current part to iterate
        int end = (partSize * partNumber);
        //case when its the last part
        if (partNumber == parts) {
            end = vehicles.size();
        }
        //iterates from the begining of that part to the end of that part
        for (int i = 0 + (partSize * (partNumber - 1)); i < end; i++) {
            //System.out.print(" " + i);
            //applies the model to the current vehicle
            model.apply(vehicles.get(i));
        }

    }

    /**
     * Asks the grid to update itself, using the newPosition on the grid of the
     * vehicles, Also updates the vehicles informations, making them ready for
     * next iteration.
     */
    public void update() {
        grid.updateVehiclesOnGrid(vehicles);
        for (int i = 0; i < vehicles.size(); i++) {
            vehicles.get(i).updateInfo();

        }

    }

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
        //rounds up densiti to 2 decimal cases
        float roundD = (float) (Math.round(d * 100.0) / 100.0);
        //number of cells that will be occupied in this density
        int occupiedCells = (int) (parameters.getCellsInX() * roundD);
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("Density: " + roundD + " Occupied Cells: " + occupiedCells + " out of " + parameters.getCellsInX());
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
            int p = rand.nextInt(profiles.size());

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

    public Profile createProfile(String fdpProvider, String name, int size, int velMax, int ahead, int safeDistance, int velIncrement, double percentageOccurrence, float alphaAcc, float betaAcc, float alphaAnt, float betaAnt) {
        Profile newProfile = new Profile(fdpProvider, name, size, velMax, ahead, safeDistance, velIncrement, percentageOccurrence, alphaAcc, betaAcc, alphaAnt, betaAnt);
        profiles.add(newProfile);
        return newProfile;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public SimulationParameters getParameters() {
        return parameters;
    }

}
