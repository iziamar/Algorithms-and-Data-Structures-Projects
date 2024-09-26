import java.util.Scanner;
import java.io.PrintStream;
public class Fib {
    public static int fibonacciRecursive(int n) {
        if (n < 0)
            return 0;
        else if (n <= 1)
            return n;
        else
            return fibonacciRecursive(n - 2) + fibonacciRecursive(n - 1);
    }
    public static int fibonacciIterative(int n){
        int previous = -1;
        int  result = 1;
        if (n < 0)
            return 0;
        if (n <= 1)
            return 1;
        for(int i = 0; i <= n; ++i){
            int sum = result + previous;
            previous = result;
            result = sum;
        }
        return result;
    }
    public static void main(String args[]) {
        System.out.println("Enter an integer n to get the n’th Fibonacci number:");
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        System.out.println("The " + n + "’th Fibonacci number using fibonacciRecursive is: " + fibonacciRecursive(n)
                + " and the " + n + "’th Fibonacci number using fibonacciIterative is: " + fibonacciIterative(n));
    }
}
