import java.util.Scanner;
public class BankAccount {
    public BankAccount(String initName, String initPass, double initBalance) {
        this.name = initName;
        this.password = initPass;
        this.balance = initBalance;
    }

    String name;
    private String password;
    double balance;

    public double getBalance(String enteredPassword) {
        if (password.equals(enteredPassword)) {
            return balance;
        } else {
            return -1;
        }
    }

    public boolean setPassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)) {
            password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    public void withdraw(String enteredPassword, double amount) {
        if (password.equals(enteredPassword) && balance >= amount) {
            balance = balance - amount;
        }
    }

    public void deposit(String enteredPassword, double amount) {
        if (password.equals(enteredPassword)) {
            balance = balance + amount;
        }
    }
    public void transfer(BankAccount other, String enteredPassword, double amount) {
        if (password.equals(enteredPassword)) {
            this.balance -= amount;
            other.balance += amount;
            System.out.println(this.name + "'s balance:" + this.balance);
            System.out.println(other.name + "'s balance:" + other.balance);
        }else{
            System.out.println(this.name + "'s balance:" + this.balance);
            System.out.println(other.name + "'s balance:" + other.balance);
        }
    }
    public static void main(String[] args){
            BankAccount checkings = new BankAccount("Dovoli", "password", 1000);
            BankAccount savings = new BankAccount("Taylor", "password", 100);

            System.out.println("Enter your password: ");
            Scanner myScanner = new Scanner(System.in);
            String enteredPassword = myScanner.nextLine();
            System.out.println("Your balance is: " + checkings.getBalance(enteredPassword));
            checkings.transfer(savings, enteredPassword, 100);

    }
}



