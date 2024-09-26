//Written by Amara Marx, marx0109
import java.util.Queue;
import java.util.Random;

public class Minefield {
    //ANSI KEY CODES
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";


    /**
     * Minefield
     * 
     * 2-d Cell array representing your minefield.
     * Constructor
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */

    public Cell[][] field;
    public int flags;
    public boolean guessedMine;
    public Minefield(int rows, int columns, int flags) {
        this.field = new Cell[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                field[i][j] = new Cell(false, "-");
            }
        }
        this.flags = flags;
    }
    public int getFlags(){
        return flags;
    }

    /**
     * evaluateField
     * 
     *
     * @function:
     * Evaluate entire array.
     * When a mine is found check the surrounding adjacent tiles. If another mine is found during this check, increment adjacent cells status by 1.
     * 
     */
    public void evaluateField() {
        int i, j;
        for (i = 0; i < field.length; i ++){
            for (j = 0; j < field[i].length; j++){
                int total = 0;
                if (!field[i][j].getStatus().equals("M")){ //checking all nearby cells for mines
                    if (i - 1 >= 0 && field[i-1][j].getStatus().equals("M")) {
                        total++;
                    }
                    if (i + 1 < field.length && field[i+1][j].getStatus().equals("M")) {
                        total++;
                    }
                    if (i - 1 >= 0 && j + 1 < field[0].length && field[i-1][j+1].getStatus().equals("M")) {
                        total++;
                    }
                    if (i - 1 >= 0 && j - 1 >= 0 && field[i-1][j-1].getStatus().equals("M")) {
                        total++;
                    }
                    if (j - 1 >= 0 && field[i][j-1].getStatus().equals("M")) {
                        total++;
                    }
                    if (j + 1 < field[i].length && field[i][j+1].getStatus().equals("M")) {
                        total++;
                    }
                    if (i + 1 < field.length && j + 1 < field[i].length && field[i+1][j+1].getStatus().equals("M")) {
                        total++;
                    }
                    if (i + 1 < field.length && j - 1 >= 0 && field[i+1][j-1].getStatus().equals("M")) {
                        total++;
                    }
                    field[i][j].setStatus(String.valueOf(total)); //updating cell to amount of mine surrounding it
                }
            }
        }
    }

    /**
     * createMines
     * 
     * Randomly generate coordinates for possible mine locations.
     * If the coordinate has not already been generated and is not equal to the starting cell set the cell to be a mine.
     * utilize rand.nextInt()
     * 
     * @param x       Start x, avoid placing on this square.
     * @param y        Start y, avoid placing on this square.
     * @param mines      Number of mines to place.
     */
    public void createMines(int x, int y, int mines) {
        Random rand = new Random();
        while (mines > 0 ) {
            int xLocation = rand.nextInt(field.length - 1);
            int yLocation = rand.nextInt(field[0].length - 1);
            while (xLocation == x){ // generates a new random int if it equals the starting x
                xLocation = rand.nextInt(field.length - 1);
            }
            while (yLocation == y){ // generates a new random int if it equals the starting y
                yLocation = rand.nextInt(field[0].length - 1);
            }
            if (field[xLocation][yLocation].getStatus() != "M"){
                field[xLocation][yLocation].setStatus("M"); //sets the location as a mine if it is not already
                mines--;
            }
        }
    }

    /**
     * guess
     * 
     * Checks if the guessed cell is inbounds
     * Either places a flag on the designated cell if the flag boolean is true or clear it.
     * If the cell has a 0 call the revealZeroes() method or if the cell has a mine end the game.
     * At the end reveal the cell to the user.
     * 
     * 
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     * @param flag    A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        if (0 <= x && x < field.length && 0 <= y && y < field[0].length ){
            if (flag){
                return false;
            } else {
                if (field[x][y].getStatus().equals("0")){ //reveals nearby zeros if the user selected one
                    revealZeroes(x, y);
                } else if (field[x][y].getStatus().equals("M")){ //ends game if user selected a mine
                    guessedMine = true;
                    return true;
                }
                field[x][y].setRevealed(true); // reveals the selected cell
                return false;
            }
        } else {
            System.out.println("Guess is out of bounds");
            return false;
        }
    }

    /**
     * gameOver
     * 
     * Ways a game of Minesweeper ends:
     * 1. player guesses a cell with a mine: game over -> player loses
     * 2. player has revealed the last cell without revealing any mines -> player wins
     * 
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() { //checks to see if the game has ended
        if (guessedMine){
            return true;
        } else if (flags == 0){ //if there are no mines left the user won
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reveals the cells that contain zeroes that surround the inputted cell.
     * Continue revealing 0-cells in every direction until no more 0-cells are found in any direction.
     * Utilizes a STACK to accomplish this.
     *
     * @param x      The x value the user entered.
     * @param y      The y value the user entered.
     */

    public void revealZeroes(int x, int y) { // uses DFS to reveal all nearby 0s
        Stack1Gen<Integer> stack = new Stack1Gen<Integer>();
        stack.push(x);
        stack.push(y);
        while (!stack.isEmpty()){
            y = stack.pop();
            x = stack.pop();
            field[x][y].setRevealed(true);
            if (x - 1 >= 0 && field[x-1][y].getStatus().equals("0") && !field[x - 1][y].getRevealed()) {
                stack.push(x-1);
                stack.push(y);
            }
            if (x + 1 < field.length && field[x+1][y].getStatus().equals("0") && !field[x + 1][y].getRevealed()) {
                stack.push(x+1);
                stack.push(y);
            }
            if (y - 1 >= 0 && field[x][y-1].getStatus().equals("0") && !field[x][y - 1].getRevealed()) {
                stack.push(x);
                stack.push(y-1);
            }
            if (y + 1 < field.length && field[x][y+1].getStatus().equals("0") && !field[x][y + 1].getRevealed()) {
                stack.push(x);
                stack.push(y+1);
            }
        }
    }


    /**
     * revealStartingArea
     *
     * On the starting move only reveal the neighboring cells of the inital cell and continue revealing the surrounding concealed cells until a mine is found.
     * Utilize a QUEUE to accomplish this.

     * @param x     The x value the user entered.
     * @param y     The y value the user entered.
     */
    public void revealStartingArea(int x, int y) {  // uses BFS to reveal cells until it reaches a mine
        Q1Gen<Integer> q = new Q1Gen<Integer>();
        q.add(x);
        q.add(y);
        while (q.length() != 0){
            x = q.remove();
            y = q.remove();
            field[x][y].setRevealed(true);
            if (field[x][y].getStatus().equals("M")){
                break;
            }
            if (x-1 >= 0 && !field[x-1][y].getRevealed()){
                q.add(x-1);
                q.add(y);
            }
            if (x + 1 < field.length && !field[x+1][y].getRevealed()){
                q.add(x+1);
                q.add(y);
            }
            if (y-1 >= 0 && !field[x][y-1].getRevealed()){
                q.add(x);
                q.add(y-1);
            }
            if (y + 1 < field[x].length && !field[x][y+1].getRevealed()){
                q.add(x);
                q.add(y+1);
            }
            if (x - 1 >= 0 && y - 1 >= 0 && !field[x-1][y-1].getRevealed()){
                q.add(x - 1);
                q.add(y - 1);
            }
            if (x + 1 < field.length && y + 1 < field[x].length && !field[x+1][y+1].getRevealed()){
                q.add(x + 1);
                q.add(y + 1);
            }
            if (x - 1 >= 0 && y + 1 < field[x].length  && !field[x-1][y+1].getRevealed()){
                q.add(x - 1);
                q.add(y + 1);
            }
            if (x + 1 < field.length && y - 1 >= 0 && !field[x+1][y-1].getRevealed()){
                q.add(x + 1);
                q.add(y - 1);
            }
        }
    }

    /**
     * For both printing methods utilize the ANSI colour codes provided! 
     * 
     * 
     * 
     * 
     * 
     * debug
     *
     * @function This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    public void debug() { // same as toString except it prints the actual value instead of - when hidden
        if (field.length <= 10) {
            String board = "   ";
            for (int i = 0; i < field[0].length; i++) {
                board += (" " + i + " ");
            }
            board += "\n";
            for (int j = 0; j < field.length; j++) {
                board += (" " + j + " ");
                for (int k = 0; k < field[j].length; k++) {
                    String status = (field[j][k].getStatus());
                    if (status.equals("0")) {
                        board += (ANSI_YELLOW + " 0 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("1")) {
                        board += (ANSI_BLUE + " 1 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("2")) {
                        board += (ANSI_GREEN + " 2 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("3")) {
                        board += (ANSI_RED + " 3 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("4")) {
                        board += (ANSI_PURPLE + " 4 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("5")) {
                        board += (ANSI_CYAN + " 5 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("6")) {
                        board += (ANSI_BLUE_BRIGHT + " 6 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("7")) {
                        board += (ANSI_YELLOW_BRIGHT + " 7 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("8")) {
                        board += (ANSI_WHITE_BACKGROUND + " 8 " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("M")) {
                        board += (ANSI_RED_BRIGHT + " M " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("F")) {
                        board += (ANSI_RED_BRIGHT + " F " + ANSI_GREY_BACKGROUND);
                    }
                }
                board += "\n";
            }
            System.out.println(board);
        } else {
            String board = "   ";
            for (int i = 0; i < 10; i++) {
                board += (" 0" + i + " ");
            }
            for (int b = 10; b < field.length; b++) {
                board += (" " + b + " ");
            }
            board += "\n";
            for (int j = 0; j < 10; j++) {
                board += (" 0" + j + " ");
                for (int k = 0; k < field.length; k++) {
                    String status = (field[j][k].getStatus());
                    if (status.equals("0")) {
                        board += (ANSI_YELLOW + " 0  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("1")) {
                        board += (ANSI_BLUE + " 1  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("2")) {
                        board += (ANSI_GREEN + " 2  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("3")) {
                        board += (ANSI_RED + " 3  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("4")) {
                        board += (ANSI_PURPLE + " 4  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("5")) {
                        board += (ANSI_CYAN + " 5  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("6")) {
                        board += (ANSI_BLUE_BRIGHT + " 6  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("7")) {
                        board += (ANSI_YELLOW_BRIGHT + " 7  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("8")) {
                        board += (ANSI_WHITE_BACKGROUND + " 8  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("M")) {
                        board += (ANSI_RED_BRIGHT + " M  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("F")) {
                        board += (ANSI_RED_BRIGHT + " F  " + ANSI_GREY_BACKGROUND);
                    }
                }
                board += "\n";
            }
            for (int j = 10; j < field.length; j++) {
                board += (" " + j + " ");
                for (int k = 0; k < field[j].length; k++) {
                    String status = (field[j][k].getStatus());
                    if (status.equals("0")) {
                        board += (ANSI_YELLOW + " 0  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("1")) {
                        board += (ANSI_BLUE + " 1  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("2")) {
                        board += (ANSI_GREEN + " 2  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("3")) {
                        board += (ANSI_RED + " 3  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("4")) {
                        board += (ANSI_PURPLE + " 4  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("5")) {
                        board += (ANSI_CYAN + " 5  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("6")) {
                        board += (ANSI_BLUE_BRIGHT + " 6  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("7")) {
                        board += (ANSI_YELLOW_BRIGHT + " 7  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("8")) {
                        board += (ANSI_WHITE_BACKGROUND + " 8  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("M")) {
                        board += (ANSI_RED_BRIGHT + " M  " + ANSI_GREY_BACKGROUND);
                    } else if (status.equals("F")) {
                        board += (ANSI_RED_BRIGHT + " F  " + ANSI_GREY_BACKGROUND);
                    }
                }
                board += "\n";
            }
            System.out.println(board);
        }
    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() {
        if (field.length <= 10) { //prints out board when the size is less than  or equal to 10 x 10
            String board = "   ";
            for (int i = 0; i < field[0].length; i++) {
                board += (" " + i + " ");
            }
            board += "\n";
            for (int j = 0; j < field.length; j++) {
                board += (" " + j + " ");
                for (int k = 0; k < field[j].length; k++) {
                    if (!field[j][k].getRevealed()) {
                        board += (" - ");
                    } else {
                        String status = (field[j][k].getStatus());
                        if (status.equals("0")) {
                            board += (ANSI_YELLOW + " 0 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("1")) {
                            board += (ANSI_BLUE + " 1 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("2")) {
                            board += (ANSI_GREEN + " 2 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("3")) {
                            board += (ANSI_RED + " 3 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("4")) {
                            board += (ANSI_PURPLE + " 4 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("5")) {
                            board += (ANSI_CYAN + " 5 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("6")) {
                            board += (ANSI_BLUE_BRIGHT + " 6 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("7")) {
                            board += (ANSI_YELLOW_BRIGHT + " 7 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("8")) {
                            board += (ANSI_WHITE_BACKGROUND + " 8 " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("M")) {
                            board += (ANSI_RED_BRIGHT + " M " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("F")) {
                            board += (ANSI_RED_BRIGHT + " F " + ANSI_GREY_BACKGROUND);
                        }
                    }
                }
                board += "\n";
            }
            return board;
        } else { // prints out the board when it is greater than 10 x 10
            String board = "   ";
            for (int i = 0; i < 10; i++) {
                board += (" 0" + i + " ");
            }
            for (int b = 10; b < field.length; b++) {
                board += (" " + b + " ");
            }
            board += "\n";
            for (int j = 0; j < 10; j++) {
                board += (" 0" + j + " ");
                for (int k = 0; k < field.length; k++) {
                    if (!field[j][k].getRevealed()) {
                        board += (" -  ");
                    } else {
                        String status = (field[j][k].getStatus());
                        if (status.equals("0")) {
                            board += (ANSI_YELLOW + " 0  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("1")) {
                            board += (ANSI_BLUE + " 1  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("2")) {
                            board += (ANSI_GREEN + " 2  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("3")) {
                            board += (ANSI_RED + " 3  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("4")) {
                            board += (ANSI_PURPLE + " 4  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("5")) {
                            board += (ANSI_CYAN + " 5  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("6")) {
                            board += (ANSI_BLUE_BRIGHT + " 6  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("7")) {
                            board += (ANSI_YELLOW_BRIGHT + " 7  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("8")) {
                            board += (ANSI_WHITE_BACKGROUND + " 8  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("M")) {
                            board += (ANSI_RED_BRIGHT + " M  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("F")) {
                            board += (ANSI_RED_BRIGHT + " F  " + ANSI_GREY_BACKGROUND);
                        }
                    }
                }
                board += "\n";
            }
            for (int j = 10; j < field.length; j++) {
                board += (" " + j + " ");
                for (int k = 0; k < field[j].length; k++) {
                    if (!field[j][k].getRevealed()) {
                        board += (" -  ");
                    } else {
                        String status = (field[j][k].getStatus());
                        if (status.equals("0")) {
                            board += (ANSI_YELLOW + " 0  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("1")) {
                            board += (ANSI_BLUE + " 1  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("2")) {
                            board += (ANSI_GREEN + " 2  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("3")) {
                            board += (ANSI_RED + " 3  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("4")) {
                            board += (ANSI_PURPLE + " 4  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("5")) {
                            board += (ANSI_CYAN + " 5  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("6")) {
                            board += (ANSI_BLUE_BRIGHT + " 6  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("7")) {
                            board += (ANSI_YELLOW_BRIGHT + " 7  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("8")) {
                            board += (ANSI_WHITE_BACKGROUND + " 8  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("M")) {
                            board += (ANSI_RED_BRIGHT + " M  " + ANSI_GREY_BACKGROUND);
                        } else if (status.equals("F")) {
                            board += (ANSI_RED_BRIGHT + " F  " + ANSI_GREY_BACKGROUND);
                        }
                    }
                }
                board += "\n";
            }
            return board;
        }
    }
}
