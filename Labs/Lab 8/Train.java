public class Train extends Vehicle {
    private double mpg;

    public Train() {
        mpg = 470.0;
        nVehicles ++;
    }

    public Train(double mpg) {
        this.mpg = mpg;
        nVehicles ++;
    }

    public double getMPG() {
        return mpg;
    }

    public void movingForward() {
        System.out.println(this.getClass().getSimpleName() + " Moving Forward");
    }


    public void movingBackward() {
        System.out.println(this.getClass().getSimpleName() + " Moving Backward");
    }

    public void enteringStation() {
        System.out.println(this.getClass().getSimpleName() + " Entering Station");
    }


    public void leavingStation() {
        System.out.println(this.getClass().getSimpleName() + " Leaving Station");
    }

    public String toString() {
        return (this.getClass().getSimpleName() + ": " + getMPG());
    }
}
