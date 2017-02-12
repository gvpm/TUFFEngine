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
public abstract class Model {

    public Model() {
    }

    public int min(int a, int b) {
        if (a > b) {
            return b;
        } else {
            return a;
        }

    }

    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }

    }

    public abstract void apply(Vehicle vehicle);

}
