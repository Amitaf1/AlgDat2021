package tilOblig;

import java.util.Arrays;

public class Attempt2 {

    public static void main(String[] args) {

        int[] a = {6, 10, 9, 4, 1, 3, 8, 5, 2, 7};

        delsortering(a);
        System.out.println(Arrays.toString(a));
    }

    public static void delsortering(int[] a){

        for (int i = 0; i < a.length - 1; i++) {

            for (int j = a.length - 1; j > i; j--) {


                if (a[j] < a[j - 1]) {

                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;


                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
