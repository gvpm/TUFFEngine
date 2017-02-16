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
public class FDPProviderFactory {

    public FDPProvider fabricate(int n) {

        switch (n) {

            case 1:
                return new FDPProviderUniform();

            case 2:
                return new FDPProviderBeta();
            default:
                throw new UnsupportedOperationException("FDPProvider not supported"); //To change body of generated methods, choose Tools | Templates.

        }

    }

    public FDPProvider fabricate(String s) {

        switch (s) {

            case "uniform":
                return new FDPProviderUniform();

            case "beta":
                return new FDPProviderBeta();
            default:
                throw new UnsupportedOperationException("FDPProvider not supported"); //To change body of generated methods, choose Tools | Templates.

        }

    }

}
