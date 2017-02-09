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
public class ModelNasch extends Model{
    
    


    @Override
    public void apply(Vehicle vehicle) {
        
        int distanceToFront;
        int newVel;
        int acceleration = vehicle.getAcceleration();
        int vMax = vehicle.getVelMax();
        Grid grid = vehicle.getGrid();
        
        int currentVel = vehicle.getVelocity();
        
        
        
        //calculate space between vehicles
        distanceToFront = vehicle.getDistanceToFront();
        newVel = min(currentVel+acceleration,vMax);
        newVel = min(newVel,distanceToFront);
        
        if(vehicle.getProfile().getFdpProvider().provide(30)){
            newVel = max(newVel-acceleration,0);
            
        }
        vehicle.setNewVelocity(newVel);
        
        int newXPosition = vehicle.getGridXPosition();
        for (int i = 0; i < newVel; i++) {
            
            
            newXPosition = grid.getNextXPosition(newXPosition);
            
        }
//        if(newVel!=currentVel && vehicle.getCore().vehicles.size()-1==vehicle.getId())
//            System.out.print(" "+currentVel);
        
        
        vehicle.setNewGridXPosition(newXPosition);
       
        
        
    }
    
}
