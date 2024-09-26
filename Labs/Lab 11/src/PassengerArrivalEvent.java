import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

/**
 * A class that handles the addition of Passengers to the line on an island
 */
public class PassengerArrivalEvent implements IEvent{
    private int island;
    public PassengerArrivalEvent(int island){
        this.island = island;
    }
    public int getIsland(){
        return island;
    }
    
    // TODO: implement this method
    @Override
    public void run() {
        Random rand = new Random();
        PassengerArrivalEvent event = new PassengerArrivalEvent(island);
        Passenger pass = new Passenger(island);
        FerrySim.lines[island].add(pass);
        double randTime = rand.nextInt(10) + 5;
        FerrySim.agenda.add(event, randTime);
        System.out.println("Passenger Event Island: " + getIsland() + ", Current time is: " + FerrySim.agenda.getCurrentTime() + ", Next Passenger in: " + randTime);
        System.out.println("Island " + island + ", Number of Passengers in line: " + FerrySim.lines[island].length());
    }
}
