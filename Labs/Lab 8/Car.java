public class Car extends Vehicle{
    private double mpg;
    public Car(){
        mpg = 30.0;
        nVehicles ++;
    }
    public Car(double mpg){
        this.mpg = mpg;
        nVehicles ++;
    }
    public double getMPG(){
        return mpg;
    }
    public void movingForward(){
        System.out.println(this.getClass().getSimpleName() + " Moving Forward");
    }
    public void movingBackward() {
        System.out.println(this.getClass().getSimpleName() + " Moving Backward");
    }

    public String toString() {
        return (this.getClass().getSimpleName() + ": " + getMPG());
    }
}
