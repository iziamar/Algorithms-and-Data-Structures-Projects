//Partially written by Amara Marx, marx0109
import java.lang.Math;
public class Board {

    // Instance variables
    private Piece[][] board;
    private int blackKing;
    private int whiteKing;

    // Constructs an object of type Board using given arguments.

    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    // Returns the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    // Updates a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is seizable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // Your Game class should not directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startRow][startCol] != null) {
            if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
                board[endRow][endCol] = board[startRow][startCol];
                board[startRow][startCol].setPosition(endRow, endCol);
                board[startRow][startCol] = null;

                return true;
            }
            return false;
        }
        return false;
    }

    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        whiteKing = 0;
        blackKing = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j ++) {
                if(null != board[i][j]) {
                    if (board[i][j].getCharacter() == '\u2654') {
                        whiteKing++;
                    } else if (board[i][j].getCharacter() == '\u265a') {
                        blackKing++;
                    }
                }
            }
        }
        return blackKing + whiteKing != 2;
    }

    public int getBlackKing(){
        return blackKing;
    }
    public int getWhiteKing(){
        return whiteKing;
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append("\u2003");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2003|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        board = new Piece[8][8];
    }

    // Movement helper functions


    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow >= 0 && startRow < 8 && startCol >= 0 && startCol < 8 && endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8){
            if (board[startRow][startCol] != null){
                if(board[startRow][startCol].getIsBlack() == isBlack){
                    return (board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack);
                }
            }
        }
        return false;
    }

    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return true;
        }
        else if (startCol == endCol && endRow == startRow + 1 || startCol == endCol && endRow == startRow - 1){
            return true;
        }
        if (startRow == endRow && endCol == startCol + 1 || startRow == endRow && endCol == startCol - 1) {
            return true;
        }
        if (endRow == startRow + 1  && endCol == startCol + 1 || endRow == startRow - 1  && endCol == startCol - 1){
            return true;
        } else return (startRow == endRow + 1  && startCol == endCol - 1 || startRow == endRow - 1  && startCol == endCol + 1);
    }

    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol)
            return true;
        else if (startRow == endRow) {
            if (startRow >= 0 && startRow < 8 && startCol >= 0 && startCol < 8 && endCol >= 0 && endCol < 8){
                if (Math.abs(endCol - startCol) == 1){
                    return (endCol == startCol + 1 || endCol == startCol - 1);
                } else{
                    for (int i = Math.abs(endCol - startCol) - 1; i > 0; i--) {
                        if (endCol < startCol){
                            if (board[startRow][startCol - i] != null) {
                                return false;
                            }
                        } else {
                            if (board[startRow][endCol - i] != null){
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol == endCol && startRow == endRow) {
            return true;
        } else if (startCol == endCol) {
            if (startRow >= 0 && startRow < 8 && startCol >= 0 && startCol < 8 && endRow >= 0 && endRow < 8) {
                if (Math.abs(endRow - startRow) == 1) {
                    return (endRow == startRow + 1 || endRow == startRow - 1);
                } else {
                    for (int i = Math.abs(endRow - startRow) - 1; i > 0; i--) {
                        if (endRow < startRow) {
                            if (board[startRow - i][startCol] != null) {
                                return false;
                            }
                        } else {
                            if (board[endRow - i][startCol] != null) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return true;
        } else if (startRow == endRow || startCol == endCol) {
            return false;
        } else if (Math.abs(startRow + startCol) == Math.abs(endRow + endCol)) {
            if (endRow < startRow) {
                if (Math.abs(startRow - endRow) == 1) {
                    return (startRow - 1 == endRow);
                } else {
                    for (int i = Math.abs(startRow - endRow) - 1; i > 0; i--) {
                        if (board[startRow - i][startCol + i] != null) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                if (Math.abs(startRow - endRow) == 1) {
                    return (startRow + 1 == endRow);
                } else {
                    for (int i = Math.abs(startRow - endRow) - 1; i > 0; i--) {
                        if (board[startRow + i][startCol - i] != null) {
                            return false;
                        }
                    }
                }
                return true;
            }
        } else if (Math.abs(startRow - startCol) == Math.abs(endRow - endCol)) {
            if (endRow < startRow) {
                if (Math.abs(startRow - endRow) != Math.abs(startCol - endCol)) {
                    return false;
                } else {
                    for (int i = Math.abs(endRow - startRow) - 1; i > 0; i--) {
                        if (board[startRow - i][startCol - i] != null) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                if (Math.abs(startRow - endRow) != Math.abs(startCol - endCol)) {
                    return false;
                } else {
                    for (int i = Math.abs(endRow - startRow) - 1; i > 0; i--) {
                        if (board[startRow + i][startCol + i] != null) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}