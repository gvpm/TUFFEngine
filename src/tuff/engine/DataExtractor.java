/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

import java.util.ArrayList;

/**
 *
 * @author gvpm
 */
public class DataExtractor {

    Core core;

    public DataExtractor(Core core) {
        this.core = core;
    }

    public float getFlow(float density) {

        return density * getAvgVel();
    }

    public float getAvgVel() {
        int numOfVehicles = core.getVehicles().size();
        float sum = 0;
        for (int i = 0; i < numOfVehicles; i++) {
            float velInKMH;
            velInKMH = (core.getVehicles().get(i).getVelocity()) * core.getParameters().getCellSize() * (float) 3.6;
            sum += velInKMH;
            //sum +=core.getVehicles().get(i).getVelocity();

        }

        return sum / numOfVehicles;
    }

}
