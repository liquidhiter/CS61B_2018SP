import java.lang.reflect.Array;

public class ArrayBasics {
    
    public static void printArray(int[] array) {
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /* By default initial values are all zeros */
        int[] x = new int[3];
        int[] y = new int[] {1, 2, 3, 4, 5};
        int[] z = {6, 7, 8, 9, 10, 11};

        printArray(x);
        printArray(y);
        printArray(z);

        int[] b = {12, 13, 14, 15,};
        printArray(b);
        System.arraycopy(b, 0, y, 2, 3);
        printArray(y);
    }
}
