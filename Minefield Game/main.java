//Written by Amara Marx, marx0109
import java.util.Random;
import java.util.Scanner;

public class main{
    public static void main(String[] args) {
        boolean debugMode; //instance variable
        Minefield minefield;
        Scanner s = new Scanner(System.in);
        System.out.println("Please select the level you would like to play on: \n Easy (E) \n Medium (M) \n Hard (H)");
        String level = s.nextLine();//getting ui for hardness level
        System.out.println(level);

        int x;
        int y;
        int flags;

        while (!level.equals("E") && !level.equals("M") && !level.equals("H")){  //Making sure it's a valid input
            System.out.println("INVALID ENTRY \n Please try again, your options are: \n Easy (E) \n Medium (M) \n Hard (H)\n");
            level =s.nextLine();
        }
        if (level.equals("E")) {  // initializing minefield depending on the level
            x = 5;
            y = 5;
            flags = 5;
            minefield = new Minefield(x, y, flags);
        }else if (level.equals("M")) {
            x = 9;
            y = 9;
            flags = 12;
            minefield = new Minefield(x, y, flags);
        }else {
            x = 20;
            y = 20;
            flags = 40;
            minefield = new Minefield(x, y, flags);
        }
        System.out.println("Would you like to play in debug mode?: (y/n)");
        String mode = s.nextLine();
        while (!mode.equals("y") && !mode.equals("n")){ // making sure the ui is valid
            System.out.println("INVALID ENTRY \n Please try again, your options are y or n");
            mode = s.nextLine();
        }
        if (mode.equals("y")){
            debugMode = true;
        } else {
            debugMode = false;
        }
        System.out.println("Please enter your starting coordinates: (x) (y) \n (don't forget the space)");
        String[] start = s.nextLine().split(" "); //takes in the ui and splits it into the seperate coordinates
        int startX = Integer.parseInt(start[0]);
        int startY = Integer.parseInt(start[1]); //varyfying that ui is valid
        while (startX < 0 || startX >= minefield.field.length || startY < 0 || startY >= minefield.field.length){
            System.out.println("INVALID INPUT \n please enter new coordinates: (x) (y) \n (don't forget the space)");
            start = s.nextLine().split(" ");
            startX = Integer.parseInt(start[0]);
            startY = Integer.parseInt(start[1]);
        }
        minefield.createMines(startX, startY, flags); //setting up the board (creating mines, evaluating field, starting area)
        minefield.evaluateField();
        minefield.revealStartingArea(startX, startY);

        if (debugMode){ // prints field with nothing hidden
            minefield.debug();
        }

        System.out.println(minefield); // prints what the user should see if they are actually playing

        while (!minefield.gameOver()) { // game loop
            System.out.println("Enter a coordinate and if you wish to place a flag (Remaining: " + minefield.flags + "): (x,y) (f(-1, else)");
            String[] guessXYF = s.nextLine().split(" ");
            int guessX = Integer.parseInt(guessXYF[0]);
            int guessY = Integer.parseInt(guessXYF[1]);
            int flag = Integer.parseInt(guessXYF[2]);
            while (guessX < 0 || guessX >= minefield.field.length || guessY < 0 || guessY >= minefield.field.length) {
                System.out.println("INVALID INPUT \n please enter new coordinates: (x) (y) \n (don't forget the space)");
                guessXYF = s.nextLine().split(" ");
                guessX = Integer.parseInt(guessXYF[0]);
                guessY = Integer.parseInt(guessXYF[1]);
            }
            if (flag == -1) {
                minefield.field[guessX][guessY].setStatus("F");
            } else {
                minefield.guess(guessX, guessY, false);
            }
            if (debugMode) {
                minefield.debug();
            }
            System.out.println(minefield);
       }
        if (minefield.guessedMine){
            System.out.println("YOU GUESSED A MINE \n GAME OVER");
        } else {
            System.out.println("CONGRATS YOU WON!");
        }
    }
    
}
