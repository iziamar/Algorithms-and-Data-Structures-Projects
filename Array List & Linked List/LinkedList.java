//Written by Amara Marx, marx0109

public class LinkedList <T extends Comparable<T>> implements List<T> {
    Node<T> head; // instance variables for the head, tail and size of the linked list
    Node<T> tail;
    int size;
    private boolean isSorted;
    public LinkedList(){ //constructor; creates an empty list
        head = null;
        size = 0;
        isSorted = true;
    }

    @Override
    public boolean add(T element) { // adds to the end of the list
        if (element != null){ // makes sure the element isn't null
            if (size == 0) { //for when this is the first element being added
                head = new Node<T>(element);
                tail = head;
                size++;
                return true;
            } else {
                Node<T> temp = new Node<>(element);
                tail.setNext(temp);
                Node<T> last = tail;
                tail = temp;
                size++;
                if (element.compareTo(last.getData()) > 0) isSorted = false;
                return true;
            }
        } else return false;
    }
    @Override
    public boolean add(int index, T element){ // adds the element at a specified index
        if (element != null && index <= size && index >= 0){
            if (size == 0 ){ //again special case for when it is the first element
                head = new Node<T>(element);
                tail = head;
                size++;
                return true;
            } else {
                Node<T> temp = new Node<>(element);
                Node<T> previous = head;
                for (int i = 1; i < index; i++){ // parses through the list until it reaches the node before index
                    previous = previous.getNext();
                }
                Node<T> next = previous.getNext();
                temp.setNext(next);
                previous.setNext(temp);
                size++;
                if (!isSorted){ //updates isSorted
                    return true;
                } else if (next != null) {
                    isSorted = (temp.getData().compareTo(previous.getData()) >= 0 && temp.getData().compareTo(next.getData()) <= 0);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() { //clears the LinkedList, sets size to 0, and isSorted to true
        tail = null;
        head = null;
        size = 0;
        isSorted = true;
    }


    @Override
    public T get(int index){ //returns the element at a certain index
        if (0 <= index && index < size){
            if (index == 0){
                return head.getData();
            } else {
                Node<T> temp = head;
                for (int i = 0; i < index; i++) {
                    temp = temp.getNext();
                }
                return temp.getData();
            }
        }
        return null;
    }

    @Override
    public int indexOf(T element) { //returns the index of a certain element
        if (element != null) {
            Node<T> temp = head;
            for(int i = 0; i < size; i++) {
                if (temp.getData().compareTo(element) == 0) {
                    return i;
                }
                temp = temp.getNext();
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() { //sorts the linked list in ascending order using bubble sort
        if (isSorted() || head == null){
            return;
        } else {
            for (int i = 0; i < size; i++){
                Node<T> previous = head;
                while (previous != null && previous.getNext() != null){
                    if(previous.getData().compareTo(previous.getNext().getData()) > 0){
                        T temp1 = previous.getData();
                        T temp2 = previous.getNext().getData();
                        previous.getNext().setData(temp1);
                        previous.setData(temp2);
                    }
                    previous = previous.getNext();
                }
            }
            isSorted = true;
        }

    }

    @Override
    public T remove(int index) { //removes an element from the list
        if (0 <= index && index < size) { //makes sure its in bounds
            if (size == 0) { //nothing to remove
                return null;
            } else if (size == 1) { //removing the only element
                T temp = head.getData();
                head.setData(null);
                size--;
                return temp;
            }else{ //all other cases
                if (index == 0) {
                    T temp = head.getData();
                    head = head.getNext();
                    size--;
                    if (!isSorted() && size > 2){
                        return temp;
                    } else if (head.getNext() != null) {
                        isSorted = (head.getData().compareTo(head.getNext().getData()) <= 0);
                        return temp;
                    } else {
                        isSorted = true;
                        return temp;
                    }
                } else {
                    Node<T> previous = head;
                    for (int i = 1; i < index; i++) {
                        previous = previous.getNext();
                    }
                    T temp = previous.getNext().getData();
                    previous.setNext(previous.getNext().getNext());
                    size--;
                    //isSorted = (previous.getData().compareTo(previous.getNext().getData()) > 0);
                    return temp;
                }
            }
        }
        return null;
    }

    @Override
    public void removeDuplicates() { //statically removes all duplicates in the Linked List
        Node<T> current = head;
        while(current != null){
            Node<T> temp = current;
            while (temp.getNext() != null){
                if (current.getData().compareTo(temp.getNext().getData()) == 0){
                    temp.setNext(temp.getNext().getNext());
                    size--;
                } else {
                    temp = temp.getNext();
                }
            }
            current = current.getNext();
        }
    }

    @Override
    public void reverse() { //reverses the order of the list by changing the direction of the pointers
        if (head != null) {
            tail = head;
            Node<T> previous = null;
            Node<T> current = head;
            Node<T> next = null;
            while(current != null) {
                next = current.getNext();
                current.setNext(previous);
                previous = current;
                current = next;
            }
            head = previous;
            isSorted = false;
        }

    }

    @Override
    public void exclusiveOr(List<T> otherList) { //does not work
        if (otherList == null) {
            return;
        }
        LinkedList<T> other = (LinkedList<T>) otherList;
        this.sort();
        this.removeDuplicates();
        other.sort();
        other.removeDuplicates();

        Node<T> current1 = this.head;
        Node<T> current2 = other.head;
        LinkedList<T> result = new LinkedList<>();

        while (current1 != null && current2 != null) {
            int compare = current1.getData().compareTo(current2.getData());
            if (compare < 0) {
                result.add(current1.getData());
                current1 = current1.getNext();
            } else if (compare > 0) {
                result.add(current2.getData());
                current2 = current2.getNext();
            } else {
                current1 = current1.getNext();
                current2 = current2.getNext();
            }
            while (current1 != null) {
                result.add(current1.getData());
                current1 = current1.getNext();
            }
            while (current2 != null) {
                result.add(current2.getData());
                current2 = current2.getNext();
            }
            // Update this list with exclusiveOr result

            this.head = result.head;
            this.size = result.size;
            this.isSorted = result.isSorted;
        }
    }


    @Override
    public T getMin() { //returns the min
        if (head != null) {
            if (isSorted()) {
                return head.getData();
            } else {
                sort();
                return head.getData();
            }
        }
        return null;
    }

    @Override
    public T getMax() { //returns the max
        if (tail != null){
            if (isSorted()) {
                return tail.getData();
            } else {
                sort();
                return tail.getData();
            }
        }
        return null;
    }

    public String toString(){
        String list = "";
        Node<T> temp = head.getNext();
        for (int i = 0; i < size; i++){
            list += temp.getData() + "\n";
            temp = temp.getNext();
        }
        return list;
    }

    @Override
    public boolean isSorted(){
        return isSorted;
    }
}

