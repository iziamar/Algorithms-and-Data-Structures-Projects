import java.lang.Math;
public class BinaryTree {
    // TODO complete this method
    public static boolean isValid(int[] arr) {
        if (arr.length == 0){
            return true;
        }
        if (arr.length != Math.pow(2, levels(arr)) - 1){
            return false;
        }
        return (validChildren(arr, 0, -1000, 1000));
    }

    public static boolean validChildren(int[] arr, int i, int min, int max) {
        if (i >= arr.length) {
            return true;
        }
        if ( arr[i] < max && arr[i] > min) {
            return validChildren(arr, 2 * i + 1, min, arr[i] ) && validChildren(arr, 2 * i + 2, arr[i], max);
        }
        return false;
    }

    public static int levels(int[] arr){
        int count = 0;
        double i;
        for (i = arr.length + 1; i > 1; i = i/2){
            count++;
        }
        return count;
    }

    public static void main (String args[]) {
      // milestone 1
      int[] arr1 = new int[]{7,4,10,3,6,8,15};
      int[] arr2 = new int[]{20,12,32,5,18,25,38};
      int[] arr3 = new int[]{11,3,33,2,8,10,44};
      int[] arr4 = new int[]{55,44,77,33,48,54,95,22,34,45,57,53,70,85,98};

      System.out.println("arr1 valid: " + isValid(arr1));  // expected: true
      System.out.println("arr2 valid: " + isValid(arr2));  // expected: true
      System.out.println("arr3 valid: " + isValid(arr3));  // expected: false
      System.out.println("arr4 valid: " + isValid(arr4));  // expected: false
    }
}
