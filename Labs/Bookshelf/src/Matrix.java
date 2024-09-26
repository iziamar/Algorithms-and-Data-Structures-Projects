public class Matrix {
    private int nrows;
    private int ncols;
    private int [][] matrix;
    public Matrix(int nrows, int ncols){
        this.nrows = nrows;
        this.ncols = ncols;
        this.matrix = new int[nrows][ncols];
    }
    public Matrix(int [][] arr){
        matrix = arr;
        nrows = arr.length;
        ncols = arr[0].length;
    }
    public Matrix transpose() {
        Matrix transposeMatrix = new Matrix(ncols, nrows);
        for (int i = 0;  i < nrows; i++) {
            for (int j = 0;j < ncols; j++) {
                transposeMatrix.matrix[j][i] = this.matrix[i][j];
            }
        }
        return transposeMatrix;
    }
    public String toString() {
        String matrixString = "";
        String matrixColumn = "";
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                matrixString += (matrix[i][j] + " ");
            }
            matrixString+="\n";
        }
        return matrixString;
    }
    public static void main(String[] args){
        int[][] mArray = new int[2][3];
        int[] row1 = new int[]{1, 2, 3};
        int[] row2 = new int[]{2, 3, 4};
        mArray[0] = row1;
        mArray[1] = row2;
        Matrix m = new Matrix(mArray);
        System.out.println(m);
        System.out.println(m.transpose());
    }
}