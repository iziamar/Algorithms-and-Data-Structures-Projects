public class Bike implements Drivable {
    @Override
    public void movingForward(){
        System.out.println(this.getClass().getSimpleName() + " Moving Forward");
    }

    @Override
    public void movingBackward() {
        System.out.println(this.getClass().getSimpleName() + " Moving Backward");

    }
}
