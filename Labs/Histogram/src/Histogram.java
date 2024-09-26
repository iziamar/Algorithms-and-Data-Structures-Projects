import java.util.Scanner;
public class Histogram {
    public int lower;
    public int upper;
    public int range;
    public int[] frequency;
    public Histogram(int lowerBound, int upperBound){
        lower = lowerBound;
        upper = upperBound;
        range = (upper - lower) + 1;
        frequency = new int[range];
    }
    public boolean add(int i){
        if (lower <= i && i <= upper){
            (frequency[i - lower])++;
            return true;
        }
        else
            return false;
    }
    public String toString() {
        String histoString = ("") ;
        for (int count = 0; count < range; count++) {
            histoString += ((lower + count) + ":" + "*".repeat(frequency[count])+ "\n");
        }
            return histoString;
    }
    public static void main(String[] args){
        Histogram histo = new Histogram(0, 5);
        histo.add(3);
        histo.add(2);
        histo.add(1);
        histo.add(1);
        histo.add(5);
        histo.add(4);
        histo.add(2);
        histo.add(3);
        histo.add(4);
        histo.add(1);
        System.out.println(histo);
    }
    public static class HistogramApp{
        public static void main (String[] args){
            System.out.println("""
                    Options
                    add - used to add  numbers to the histogram
                    print - prints the histogram to the screen
                    quit - leaves the program""");
            Scanner s = new Scanner(System.in);
            System.out.println("Enter a lower bound: ");
            int lower = s.nextInt();
            System.out.println("Enter an upper bound: ");
            int upper = s.nextInt();
            Histogram myHisto = new Histogram(lower, upper);
            String next = "";
            while (!(next.equals("quit"))) {
                next = s.nextLine();
                if ((next.equals("add"))) {
                    System.out.println("Enter a number: ");
                    int i = s.nextInt();
                    if (!myHisto.add(i)) {
                        System.out.println(i + " is not in the range.");
                    }
                }
                else if (next.equals("print")) {
                    System.out.println(myHisto);
                }
            }
            System.out.println("Bye!");
        }
    }

}