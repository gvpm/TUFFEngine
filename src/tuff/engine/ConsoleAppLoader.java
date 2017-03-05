package tuff.engine;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * The console way to run the simulation.
 *
 * It runs the file given via arguments. It runs the simulation.txt if no
 * arguments are passed.
 *
 */
public class ConsoleAppLoader {

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
