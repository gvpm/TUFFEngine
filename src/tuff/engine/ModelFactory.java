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
public class ModelFactory {

    public Model fabricate(int n) {

        switch (n) {

            case 1:
                return new ModelNasch();

            case 2:
                return new ModelTUFF();
            case 3:
                return new ModelNaschWithBeta();
            default:
                throw new UnsupportedOperationException("Model not supported"); //To change body of generated methods, choose Tools | Templates.

        }

    }

    public Model fabricate(String s) {

        switch (s) {

            case "nasch":
                return new ModelNasch();
            case "naschWithBeta":
                return new ModelNaschWithBeta();

            case "tuff":
                return new ModelTUFF();
            default:
                throw new UnsupportedOperationException("Model not supported"); //To change body of generated methods, choose Tools | Templates.

        }

    }

}
