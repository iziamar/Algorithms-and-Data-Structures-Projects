/**
 * This class is a representation of a passenger.
 *
 * Created by nguy2284 on 4/1/2017.
 */
import java.util.Random;
public class Passenger {
    // The island that the passenger boards the ferry
    private int pickupIsland;
    // The island that the passenger gets off the ferry
    private int dropoffIsland;

    public int getPickupIsland(){
        return pickupIsland;
    }

    public int getDropoffIsland(){
        return dropoffIsland;
    }

    /**
     * Constructor that takes in a pickup island and 
     * sets the dropoff island to a random island that
     * is not the pickup island.
     */
    public Passenger(int pickupIsland){
        this.pickupIsland = pickupIsland;
        Random rand = new Random();
        dropoffIsland = rand.nextInt(3);
        while(dropoffIsland == pickupIsland){
            dropoffIsland = rand.nextInt(3);
        }
    }
}
