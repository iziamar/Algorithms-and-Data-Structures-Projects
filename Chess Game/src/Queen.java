//Written by Amara Marx (marx0109)
public class Queen{
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The current row of the Queen.
     * @param col   The current column of the Queen.
     * @param isBlack   The color of the Queen.
     */
    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            //Verifying either vertical, horizontal, or diagonal movement
            if (board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
                // Case 1: the ending position is empty
                if (board.getPiece(endRow, endCol) == null) {
                    return true;
                    // Case 2: the ending position contains a piece, and it is the opposing color
                } else return (board.getPiece(endRow, endCol).getIsBlack() != this.isBlack);
            }
        }
        return false;
    }
}