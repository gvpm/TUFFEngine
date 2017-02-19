package tuff.engine;

/**
 * Implements the Nasch Model
 *
 * @author gvpm
 */
public class ModelNasch extends Model {

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
        newVel = min(currentVel + acceleration, vMax);

        //Gets the alpha to decide if its goind to use acceletarion or not
        if (vehicle.getProfile().getFdpProvider().provide(35)) {
            newVel = max(newVel - acceleration, 0);

        }
        //Caps the new vel bases on the distance to the vehicle on the front
        newVel = min(newVel, distanceToFront);

        //sets the vehicle new vel
        vehicle.setNewVelocity(newVel);

        //gets the new x position based on the current plus  adding new vel
        int newXPosition = grid.getNewXPostition(vehicle.getGridXPosition(), newVel);

        vehicle.setNewGridXPosition(newXPosition);

    }

}
