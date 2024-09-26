public class Helicopter extends Vehicle{
    private double mpg;
    public Helicopter(){
        nVehicles ++;
        mpg = 0.3;
    }
    public Helicopter(double mpg){
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
    public void movingUp() {
        System.out.println(this.getClass().getSimpleName() + " Moving Up");
    }

    public void movingDown() {
        System.out.println(this.getClass().getSimpleName() + " Moving Down");
    }

    public String toString() {
        return (this.getClass().getSimpleName() + ": " + getMPG());
    }
}
