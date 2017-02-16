package tuff.engine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gvpm
 */
public class ConsoleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //-------------------------------------(int speedLimit, float probP, int simulationTime, int discardTime, int statisticTime, float initialDensity, float deltaDendity, float finalDensity, int cellsInX, int cellsInY, float cellSize, float defaultCarSize, String model) 
        SimulationParameters parameters = new SimulationParameters(25, 35, 15000, 10000, 121, (float) 0.01, (float) 0.01, (float) 0.9, 10000, 1, (float) 1.5, (float) 7.5, "naschWithBeta");
        //Parameters for nasch
        //SimulationParameters parameters = new SimulationParameters(5, 35, 15000, 10000, 121, (float) 0.01, (float) 0.01, (float) 0.9, 10000, 1, (float)7.5, (float) 7.5, "nasch");

        Core core = new Core(parameters);

        //---------(FDPProvider fdpProvider, String name, int size, int velMax, int ahead, int safeDistance, int velIncrement, double percentageOccurrence, float alphaAcc, float betaAcc, float alphaAnt, float betaAnt) 
        //core.createProfile("normal", "Nasch Basic", 1, 5, 0, 0, 1, 1, 0, 0, 0, 0);
        core.createProfile("uniform", "Nasch Basic", 5, 25, 0, 0, 5, 1, 0, 0, 0, 0);
        //core.createProfile("normal", "Basic2", 5, 25, 0, 0, 3, 0.5, 0, 0, 0, 0);

        core.init();
        core.simulateAllDensities();

    }

}
