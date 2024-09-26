// Written by Amara Marx, marx0109
// This class is where the game will be executed
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        // initializes a new board
        Board myBoard = new Board();
        Fen f = new Fen();
        // loads in the starting positions of all pieces
        f.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
        Scanner s = new Scanner(System.in);
        int i = 0;
        while(!myBoard.isGameOver()) { // loops until the game is over
            System.out.print(myBoard);
            if (i%2 == 0) { // if i is even it is white's turn
                System.out.println("It is currently white's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col] ex. 1 3 0 4)");
                int startRow = s.nextInt(); // each take in one of the user entered ints
                int startCol = s.nextInt();
                int endRow = s.nextInt();
                int endCol = s.nextInt();
                if (myBoard.movePiece(startRow, startCol, endRow, endCol)) { //if the entered pos is valid it will move
                    myBoard.movePiece(startRow, startCol, endRow, endCol);   //the piece  and i++ to start black's turn
                    i ++;
                } else {
                    System.out.println("invalid entry please try again"); // if the input was invalid it will have you try again.
                }
            } else { // if i is odd it is black's turn
                System.out.println("It is currently black's turn to play");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                int startRow = s.nextInt(); // all methods here function the same as for white's turn
                int startCol = s.nextInt();
                int endRow = s.nextInt();
                int endCol = s.nextInt();
                if (myBoard.movePiece(startRow, startCol, endRow, endCol)) {
                    myBoard.movePiece(startRow, startCol, endRow, endCol);
                    i ++;
                } else {
                    System.out.println("invalid entry please try again");
                }
            }
        }
        if (myBoard.getWhiteKing() == 0) { // if the white king is no longer on the board black won
            System.out.println("Black has won the game!");
        } else if (myBoard.getBlackKing() == 0) { // if the black king is no longer on the board white won
            System.out.println("White has won the game");
        } else {
            System.out.println("issue with endGame"); // error message for debugging
        }

    }
}