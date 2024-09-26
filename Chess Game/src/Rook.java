//Written by Amara Marx (marx0109)
public class Rook{
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The current row of the Rook.
     * @param col   The current column of the Rook.
     * @param isBlack   The color of the Rook.
     */
    public Rook(int row, int col, boolean isBlack){
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
            //Verifying the vertical or horizontal movement
            if (board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol)) {
                //Case 1: the ending position does not contain a piece
                if (board.getPiece(endRow, endCol) == null) {
                    return true;
                    //Case 2: the ending position does contain a piece, and it is the opposing color.
                } else return (board.getPiece(endRow, endCol).getIsBlack() != this.isBlack);
            }
        }
        return false;
    }
}