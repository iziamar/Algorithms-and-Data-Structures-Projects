/**
 * A class representing a Ferry, which transports Passengers from island to island.
 * Can hold up to 60 Passengers at a time.
 */

public class Ferry {

    private Passenger [] passengers = new Passenger[60];
    int passCount = 0;
    // TODO: implement this
    public boolean addPassenger(Passenger p) {
        if (!isFull() && p != null){
            passengers[passCount] = p;
            passCount++;
            return true;
        } else return false; }

    // TODO: implement this
    public Passenger[] removePassengersAtIsland(int island){

        Passenger[] remove = new Passenger[FerrySim.lines.length];
        for(int i = 0; i < FerrySim.lines[island].length(); i++){
            Passenger temp = FerrySim.lines[island].remove();
            if (temp.getDropoffIsland() == island) {
                remove[i] = temp;
                passCount--;
            } else {
                FerrySim.lines[island].add(temp);
            }
        }
        return remove; }
    
    // TODO: implement this
    public int getPassCount(){
        return passCount;
    }
    public boolean isFull() {
        if (passCount == 60) {
            return true;
        } else return false;
    }
}
