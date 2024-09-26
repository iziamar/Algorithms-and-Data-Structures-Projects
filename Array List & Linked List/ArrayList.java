//Written by Amara Marx, marx0109
import java.util.Arrays;

public class ArrayList <T extends Comparable<T>> implements List<T> {
    private int size; // instance variable for ArrayList
    private T[] list;
    private boolean isSorted;
    public ArrayList(){ //constructor; sets default length to 2, size to 0, and isSorted to true
        list = (T[]) new Comparable[2];
        size = 0;
        isSorted = true;
    }

    public T[] resize(T[] original){ // resize method makes a new list double the size and copies all elements over
        T[] temp = (T[]) new Comparable[2 * original.length];
        for (int i= 0; i < original.length; i++) {
            temp[i] = original[i];
        }
        return temp;
    }

    @Override
    public boolean add(T element) { //this add method simply adds the element to the end of the list
        if (element == null || list == null) { // if the element or list is null it will not continue
            return false;
        }else{
            if(size == 0){ // if the size is zero then this is the first element in the list
                size++;
                list[0] = element;
                isSorted = true;
                return true;
            }
            else if (size == list.length) { //if the array is full this will double the size
                list = resize(list);
            }
            list[size++] = element; //adds element to end of list
             if (isSorted()){ //updates isSorted depending on if the element added is bigger than the last one was
                 isSorted = (element.compareTo(list[size - 2]) >= 0);
             }
            return true;
        }
    }
    @Override
    public boolean add(int index, T element){ // adds element to the specified index
        if (element == null || list == null || 0 > index || index > size) { //makes sure nothing is null and we are in bounds
            return false;
        }else{
            if(size == 0){ // again if the size is zero this is the first element
                size++;
                list[0] = element;
                isSorted = true;
            }
            else if (size == list.length){ // resizes the array to double the length
                list = resize(list);
            }
            if (index == size){ // if the index was at the end of the list
                list[size++] = element;
                if (isSorted()){
                    isSorted = (element.compareTo(list[index - 1]) >= 0 );
                }
                return true;
            } else {
                for (int i = size; i > index; i--){ // adds the element at the specific index
                    list[i] = list[i-1];
                }
                list[index] = element;
                if (isSorted()){ //checks to see if the list is still sorted
                    if (index != 0 && (element.compareTo(list[index - 1]) >= 0) && (element.compareTo(list[index + 1]) <= 0)){
                        isSorted = true;
                    } else if (index == 0 && (element.compareTo(list[index + 1]) <= 0)){
                        isSorted = true;
                    }
                }
                size++;
                return true;
            }
        }
    }

    @Override
    public void clear() { //clears the array, sets the size to 0, and isSorted to true
        for(int i = 0; i < size; i++){
            list[i] = null;
        }
        size = 0;
        isSorted = true;
    }
    @Override
    public T get(int index){ //returns the element at a certain index
        if (index < 0 || index >= list.length){
            return null;
        }
        return list[index];
    }

    @Override
    public int indexOf(T element) { //returns the index of a certain element
        if (element == null){
            for (int i = 0; i < size; i++){
                if(list[i] == null){
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++){
                if (element.compareTo(list[i]) == 0){
                    return i;
                }
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
    public void sort() { //sorts the array using bubble sort
        if(list == null){
            return;
        }
        if (size <= 1){
            isSorted = true;
        } else {
            boolean swapped = true;
            for (int i = 0; i < list.length && swapped == true; i++) {
                swapped = false;
                for (int j = 1; j < list.length - i; j++) {
                    if (list[j] != null && list[j].compareTo(list[j-1]) < 0) {
                        swapped = true;
                        T temp = list[j];
                        list[j] = list[j-1];
                        list[j-1] = temp;
                    }
                }
            }
            isSorted = true;
        }
    }

    @Override
    public T remove(int index) { //removes an element at a certain index
        if (index < 0 || index >= size) { // makes sure it is in bounds
            return null;
        } else {
            T temp = (T) list[index]; //holds the value that will be removed
            for (int i = index; i < size - 1; i++) { //shifts everything over by one
                list[i] = list[i + 1];
            }
            list[--size] = null;
            if (isSorted()) {  //checks if the list is still sorted
                if (list != null && index != 0 && (list[index].compareTo(list[index - 1]) >= 0) && (list[index].compareTo(list[index + 1]) <= 0)) {
                    isSorted = true;
                } else if (list!= null && index == 0 && (list[index].compareTo(list[index + 1]) <= 0)) {
                    isSorted = true;
                }
            }
            return temp;
        }

    }

    @Override
    public void removeDuplicates() { //removes any duplicates in the array, and maintains order if previously sorted
        if (size <= 1){
            return;
        }
        int newSize = 1;
        for (int i = 1; i < size; i++){
            boolean isDuplicate = false;
            for (int j = 0; j < newSize; j++) {
                if (list[i].compareTo(list[j]) == 0){
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate){
                list[newSize++] = list[i];
            }
        }
        for (int i = newSize; i < size; i++){
            list[i] = null;
        }
        size = newSize;
    }

    @Override
    public void reverse() { //reverses the order of the array
        for(int i = 0; i < size / 2; i++){
            T temp = (T) list[i];
            list[i] = list[size - i - 1];
            list[size - i - 1] = temp;
        }
        isSorted = false;
    }

    @Override
    public void exclusiveOr(List<T> otherList) {
        if (otherList == null){
            return;
        }
        ArrayList<T> other;
        other = (ArrayList<T>) otherList;
        sort();
        other.sort();
        removeDuplicates();
        other.removeDuplicates();

        ArrayList<T> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;

        while(index1 < size && index2 < other.size){
            T elem1 = (T) list[index1];
            T elem2 = other.get(index2);

            int compare = elem1.compareTo(elem2);
            if (compare < 0){
                result.add(elem1);
                index1++;
            } else if (compare > 0){
                result.add(elem2);
                index2++;
            } else {
                index1++;
                index2++;
            }
            while (index1 < size) {
                result.add((T) list[index1]);
                index1++;
            }

            while (index2 < other.size) {
                result.add(other.get(index2));
                index2++;
            }

            // Update this list with exclusiveOr result
            this.clear();
            for (int i = 0; i < result.size(); i++){
                add(result.get(i));
            }
        }



    }

    @Override
    public T getMin() {
        if (isSorted()){
            return list[0];
        } else {
            sort();
            return list[0];
        }
    }

    @Override
    public T getMax() {
        if (isSorted()){
            reverse();
            return list[0];
        } else {
            sort();
            reverse();
            return list[0];
        }
    }

    public String toString(){
        String string = "";
        for (int i = 0; i < size; i++){
            string += list[i];
        }
        return string;
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }
}

