/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

import java.util.Random;

/**
 *
 * @author gvpm
 */
public class FDPProviderUniform extends FDPProvider {

    @Override
    public boolean provide(int a) {
        Random rand = new Random();
        int p = rand.nextInt(100);

        return p < a;
    }

}
