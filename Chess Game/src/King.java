//Written by Amara Marx (marx0109)
public class King {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The current row of the King.
     * @param col   The current column of the King.
     * @param isBlack   The color of the King.
     */
    public King(int row, int col, boolean isBlack) {
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
            // Case 1: verifies that move is within 1 space of starting pos and does not contain a piece.
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol)){
                return (board.getPiece(endRow, endCol) == null);
                // Case 2: a piece is being captured
            } else {
                // verifies that the piece being captured is a different color
                return ((board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack));
            }
        }
        return false;
    }
}