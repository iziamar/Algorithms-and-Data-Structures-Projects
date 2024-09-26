public class StackException extends Exception  {
    public int size;
    public StackException(int size){
        this.size = size;
    }

    public int getSize(){
        return size;
    }
}