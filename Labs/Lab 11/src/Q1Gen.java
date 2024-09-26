// Q1Gen.java
// Updated 4/2024
// Generic queue implementation using a linked list of nodes 
// Uses GenericNode.java


public class Q1Gen <T> implements QGen <T> {

    // instance variables

    private int size;
    private GenericNode <T> front;
    private GenericNode <T> rear;

    // constructor

    public Q1Gen () {}

    // selectors

    public void add(T o) {

        if (size == 0) {
          front = new GenericNode <T> (o, null);
          rear = front;
        }
        else {
          rear.setNext(new GenericNode <T> (o, null));
          rear = rear.getNext();
        }
        size++;
    }

    public T remove() {

        T answer;

        if (size == 0)
          throw new RuntimeException("Removing from empty queue"); 
        
        answer = front.getData();
        front = front.getNext();
        size--;
        if (size == 0)
          rear = null;
        return answer;
    }

    public int length() {
        return size;
    }

}  // Q1Gen class
