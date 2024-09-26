public class Stack<T>{
    private int size;
    private int currSize;
    private T data;
    private Node <T> front;
    private Node <T> last;
    public Stack(){
        size = 5;
        Node<T> node = new Node<T>(data,null);
        front = node;
        last = front;
    }
    public Stack(int size){
        this.size = size;
        Node<T> node = new Node<T>(data,null);
        front = node;
        last = front;
    }
    public T pop(){
        T answer;
        if (currSize == 0){
            throw new RuntimeException("Removing from empty queue");
        } else{
            answer = front.getData();
            front = front.getNext();
            currSize--;
            if(currSize == 0) {
                Node<T> node1 = new Node<>();
                last = node1;
                front = last;
            }
            return answer;
        }

    }
    public void push(T item) {
        if (currSize == 0){

            front.setData(item);
            last = front;
            currSize++;
        }
        else if (currSize + 1 < size) {
            last = front;
            front = new Node<T>(item, last);
            currSize ++;
        }
    }

    public int getSize(){
        return size;
    }

    public static void main (String[] args){

    }

}