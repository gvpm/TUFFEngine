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
        
        //ModelFactory modelFactory = new ModelFactory();
        //Model model = modelFactory.fabricate("nasch");
        SimulationParameters parameters = new SimulationParameters(25, 35, 15000, 10000, 121, (float) 0.05, (float) 0.01, (float) 0.9, 10000, 1, (float)1.5, (float) 7.5, "nasch");
        
        Core core = new Core(parameters);
        
        FDPProviderFactory  fdpFactory =  new FDPProviderFactory();

        core.createProfile(fdpFactory.fabricate(1), "Basic", 1, 5, 0, 0, 1, 1, 0, 0, 0, 0);
        
        core.init();
    
    }
    
}
