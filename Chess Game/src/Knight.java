public class Knight{
    //instance variable
    private int row;
    private int col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The current row of the Knight.
     * @param col   The current column of the Knight.
     * @param isBlack   The color of the Knight.
     */
    public Knight(int row, int col, boolean isBlack){
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
            // Verifying that the piece is moving in an L shape
            if (Math.abs(endRow - this.row) == 2 && Math.abs(endCol - this.col) == 1 || Math.abs(endRow - this.row) == 1 && Math.abs(endCol - this.col) == 2) {
                // Case 1: the ending position is empty
                if (board.getPiece(endRow, endCol) == null) {
                    return true;
                }
                // Case 2: the ending position is not empty and the piece being captured is the opposing color
                else return (board.getPiece(endRow, endCol).getIsBlack() != this.isBlack);
            }
        }
        return false;
    }
}