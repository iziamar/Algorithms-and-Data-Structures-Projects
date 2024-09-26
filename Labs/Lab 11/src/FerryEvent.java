/**
 * A class that represents a FerryEvent, which handles boarding, deboarding, and transport of Passengers
 */
public class FerryEvent implements IEvent {
  private int island;
  private Ferry ship;

  public FerryEvent(int island, Ferry ship) {
    this.island = island;
    this.ship = ship;
  }

  public void setIsland(int island) {
    this.island = island;
  }

  public void setShip(Ferry ship) {
    this.ship = ship;
  }

  // What two attributes will we need to keep track of?

  // We should create a constructor that takes in those attributes and sets them.

  // TODO: implement this
  @Override
  public void run() {
    int temp = ship.removePassengersAtIsland(island).length;
    if (temp > 0) {
      FerryEvent newFerryEvent = new FerryEvent(island, ship);
      FerrySim.agenda.add(newFerryEvent, temp);
      System.out.println("Currently deboarding at island " + island + " it took " + temp + " seconds to deboard " + temp + " passengers.");
    } else if (temp == 0 && !ship.isFull() && FerrySim.lines[island].length() > 0) {
      int i;
      for (i = ship.getPassCount(); i < 60 || i == FerrySim.lines[island].length(); i++) {
        ship.addPassenger(FerrySim.lines[island].remove());
      }
      PassengerArrivalEvent newArrival = new PassengerArrivalEvent(island);
      FerrySim.agenda.add(newArrival, i);
      System.out.println("Currently boarding at island " + island + " it took " + i + " seconds to deboard " + i + "passengers.");
    } else {
      int newIsland;
      if (island == 2) {
        newIsland = 0;
      } else {
        newIsland = island + 1;
      }
      FerryEvent moveIslands = new FerryEvent(island, ship);
      FerrySim.agenda.add(moveIslands, 60);
      System.out.println("Currently moving from island " + island + " to " + newIsland);
    }



    // Check if there are passengers to deboard.
    // If there are, deboard them and create a new FerryEvent with the same 
    //   island and ferry. The time will be the amount of time it takes to
    //   deboard these passengers.
    
    // If there aren't any passengers to deboard, let's check if we can board 
    //   passengers. If there's passengers who want to board and space on the 
    //   ferry, then let's board. Keep track of the number of passengers that
    //   board, because we'll once again be adding another FerryEvent to allow
    //   for the time that passed, just like for deboarding.
    
    // If there is no deboarding and no boarding, then it's time for the ferry 
    //   to move on. We'll add a new FerryEvent to the agenda, this time with 
    //   the next island (remember that the ferry goes 0->1->2->0...), and the 
    //   time as the amount of time it takes to travel to the next island.
       
  }
}
