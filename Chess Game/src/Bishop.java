//Written by Amara Marx (marx0109)
public class Bishop {
    // instance variables.
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param row   The current row of the Bishop.
     * @param col   The current column of the Bishop.
     * @param isBlack   The color of the Bishop.
     */
    public Bishop(int row, int col, boolean isBlack) {
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
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            // verifying diagonal movement
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol))
                // Case 1: the path being travelled is diagonal and clear of any other pieces
                if (board.getPiece(endRow, endCol) == null){
                    return true;
                //Case 2: the piece is moving diagonally but a piece is being captured
                } else return (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack);
        }
        return false;
    }
}