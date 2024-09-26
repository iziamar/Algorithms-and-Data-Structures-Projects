import java.util.Scanner;
import static java.lang.Math.max;
public class Max {
    public static int recursiveMaxDigit(int num){
        int n = num;
        if (n < 10)
            return n;
        else
            return Math.max(recursiveMaxDigit(n/10), n % 10);
    }
    public static int iterativeMaxDigit(int num){
        int n = num;
        int max = -1;
        if (n < 0)
            return n;
        while (n > 0) {
            int digit = n % 10;
            if (digit > max)
                max = digit;
            n /= 10;
        }
        return max ;
    }
    public static void main (String args[]) {
        System.out.println("Enter an integer to get the maximum digit: ");
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        System.out.println("The highest digit in " + n + " using the recursiveMaxDigit is " + recursiveMaxDigit(n) +
                " and the highest digit in " + n + " using the iterativeMaxDigit is " + iterativeMaxDigit(n));
    }
}
