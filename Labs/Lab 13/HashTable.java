import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable<T>{
    NGen<T>[] hashTable;

    //TODO: Create a constructor that takes in a length and initializes the hash table array

    public HashTable(int length){
        hashTable = new NGen[length];
    }

    //TODO: Implement a simple hash function
    public int hash1(T item) {
        String itemString = (String) item;
        if (itemString.length() == 1){
            return itemString.charAt(0) % hashTable.length;
        } else return (26 * itemString.charAt(0) + itemString.charAt(1)) % hashTable.length;
    }

    //TODO: Implement a second (different and improved) hash function
    public int hash2(T item) {
        return -1;
    }

    //TODO: Implement the add method which adds an item to the hash table using your best performing hash function
    // Does NOT add duplicate items
    public void add(T item) {
        int index = hash1(item);
        if (hashTable[index] != item) {
            hashTable[index] = (NGen<T>) item;
        }
    }

    // ** Already implemented -- no need to change **
    // Adds all words from a given file to the hash table using the add(T item) method above
    @SuppressWarnings("unchecked")
    public void addWordsFromFile(String fileName) {
        Scanner fileScanner = null;
        String word;
        try {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName + " not found.");
            System.exit(1);
        }
        while (fileScanner.hasNext()) {
            word = fileScanner.next();
            word = word.replaceAll("\\p{Punct}", ""); // removes punctuation
            this.add((T) word);
        }
    }

    //TODO: Implement the display method which prints the indices of the hash table and the number of words "hashed"
    // to each index. Also prints:
    // - total number of unique words
    // - number of empty indices
    // - number of nonempty indices
    // - average collision length
    // - length of longest chain
    public void display() {
        String values = "";
        for (int i = 0; i < hashTable.length; i++){
            if (hashTable[i] != null){
                values += (i + " " + hashTable[i]);
            }
        }
        System.out.println("HashTable has a length of " + hashTable.length + " and it's values are" + values);
    }

    // TODO: Create a hash table, store all words from "canterbury.txt", and display the table
    //  Create another hash table, store all words from "keywords.txt", and display the table
    public static void main(String args[]) {
        HashTable hash1 = new HashTable(10);
        hash1.add("word");
        hash1.add("words");
        hash1.add("wordle");
        hash1.display();
    }
}
