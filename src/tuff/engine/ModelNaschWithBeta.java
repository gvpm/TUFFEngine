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
public class ModelNaschWithBeta extends Model {

    @Override
    public void apply(Vehicle vehicle) {

        Grid grid = vehicle.getGrid();
        int distanceToFront;
        int newVel;
        int acceleration = vehicle.getAcceleration();
        int vMax = vehicle.getVelMax();
        int currentVel = vehicle.getVelocity();

        //calculate space between vehicles
        distanceToFront = vehicle.getDistanceToFront();

        //Calculate new vel, addind acceleratio to vel
        float alpha = vehicle.getBetaFunctionAcc();
        float roundA = (float) (Math.round(alpha * 100.0) / 100.0);
        newVel = min(currentVel + (int) (acceleration * (1 - roundA)), vMax);
        //System.out.println((int)(acceleration*(1-roundA)));

        //Caps the new vel bases on the distance to the vehicle on the front
        newVel = min(newVel, distanceToFront);

        //sets the vehicle new vel
        vehicle.setNewVelocity(newVel);

        //gets the new x position based on the current plus  adding new vel
        int newXPosition = grid.getNewXPostition(vehicle.getGridXPosition(), newVel);

        vehicle.setNewGridXPosition(newXPosition);

    }

}