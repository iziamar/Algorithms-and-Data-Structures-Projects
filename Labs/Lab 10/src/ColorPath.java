import java.util.*;
public class ColorPath {
    static int[][] colorPath(int[][] image, int sourceRow, int sourceCol, int newColor){
        bfs(image, sourceRow, sourceCol, newColor);
        return image;
    }

    public static void bfs(int[][] arr, int col, int row, int newColor){
        int sourceColor = arr[col][row];
        Queue<int[]> queue = new LinkedList<>();
        int[] first = new int[]{row, col};
        queue.add(first);

        while(queue != null && !queue.isEmpty()) {
            int[] last = queue.remove();
            int[][] checked = new int[arr.length][arr[last[0]].length];
            checked[row][col] = 1;
            int left = last[1] - 1;
            int right = last[1] + 1;
            int above = last[0] - 1;
            int below = last[0] + 1;
            if (left < arr[0].length && left >= 0 && checked[last[0]][left] != 1 && sourceColor == arr[last[0]][left]) {
                queue.add(new int[]{last[0], left});
                arr[last[0]][left] = newColor;
            }
            checked[last[0]][left] = 1;

            if (right < arr[0].length && right >= 0 && checked[last[0]][right] != 1 && sourceColor == arr[last[0]][right]) {
                queue.add(new int[]{last[0], right});
                arr[last[0]][right] = newColor;
            }
            checked[last[0]][right] = 1;

            if (above < arr.length && above >= 0 && checked[above][last[1]] != 1 && sourceColor == arr[above][last[1]]) {
                queue.add(new int[]{above, last[1]});
                arr[above][last[1]] = newColor;
            }
            checked[above][last[1]] = 1;

            if (below < arr.length  && below >= 0 && checked[below][last[1]] != 1 && sourceColor == arr[below][last[1]] ) {
                queue.add(new int[]{below, last[1]});
                arr[below][last[1]] = newColor;
            }
            checked[below][last[1]] = 1;
        }
    }

    public static void dfs(int[][] arr, int col, int row){
        if (row > arr.length || col > arr[row].length){
            return;
        }
        dfs(arr, col - 1, row);
        dfs(arr, col + 1, row);
        dfs(arr, col, row - 1);
        dfs(arr, col, row + 1);
    }
}
