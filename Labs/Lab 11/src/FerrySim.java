/**
 * This is the driver class for the ferry simulation. It keeps track of the agenda,
 * and potentially other shared information. It also has the main, which initializes 
 * the simulation and runs the Events in the agenda.
 */
public class FerrySim {
      
    // Note that this is static. This way, it can be accessed anywhere.
    public static PQ agenda = new PQ();
    // Array of queues to store Passengers for each island
    public static Q<Passenger>[] lines = new Q[3];

    public static void main(String[] args) {
        lines[0] = new Q<Passenger>(0);
        lines[1] = new Q<Passenger>(1);
        lines[2] = new Q<Passenger>(2);


        // add PassengerArrivalEvents to agenda
        PassengerArrivalEvent pass0 = new PassengerArrivalEvent(0);
        agenda.add(pass0, 0);

        PassengerArrivalEvent pass1 = new PassengerArrivalEvent(1);
        agenda.add(pass1, 0);

        PassengerArrivalEvent pass2 = new PassengerArrivalEvent(2);
        agenda.add(pass2, 0);


        // add FerryEvent to agenda
        Ferry ship1 = new Ferry();
        FerryEvent ferryEvent1 = new FerryEvent(0, ship1);
        agenda.add(ferryEvent1, 0);

        // loops through agenda and runs the next iEvent
        // will throw an exception until events are added to the agenda
        while(agenda.getCurrentTime() <= 10000) {
            agenda.remove().run();
        }



    }

}
