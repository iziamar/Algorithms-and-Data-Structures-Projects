// GenericNode.java
// Revised 2/2024

// A simple generic node class for use with generic data structures

public class GenericNode <T> {
  
    private T data;
    private GenericNode <T> next;

    // constructors
    
    public GenericNode () {}

    public GenericNode (T o, GenericNode <T> link) {
        data = o;
        next = link;
    }

    // selectors

    public T getData() {
        return data;
    }

    public void setData(T o) {
        data = o;
    }

    public GenericNode <T> getNext() {
        return next;
    }

    public void setNext(GenericNode <T> link) {
        next = link;
    }

    // instance variables

}  // GenericNode class
