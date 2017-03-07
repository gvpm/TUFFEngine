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
        //case where no arguments where given
        if (args.length == 0) {
            GUIOutputWindow frame = new GUIOutputWindow();
            frame.setVisible(true);
            MessageConsole mc = new MessageConsole(frame.getjTextArea1());
            mc.redirectOut();

            mc.setMessageLines(100);

            fileLoader = new FileLoader("simulation.txt");

            //Case where arguments were given, running on prompt probably    
        } else {
            fileLoader = new FileLoader(args[0]);
        }

        fileLoader.load();

        Core core = fileLoader.getCore();

        core.init();

        core.simulateAllDensities();

    }

}
