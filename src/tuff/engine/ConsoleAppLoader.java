package tuff.engine;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * The console way to run the simulation.
 *
 */
public class ConsoleAppLoader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        FileLoader fileLoader;
        if (args.length == 0) {
            fileLoader = new FileLoader("simulation.txt");
        } else {
            fileLoader = new FileLoader(args[0]);
        }

        fileLoader.load();

        Core core = fileLoader.getCore();

        core.init();
        core.simulateAllDensities();

    }

}
