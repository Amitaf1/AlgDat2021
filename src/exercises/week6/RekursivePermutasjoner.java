package exercises.week6;

import java.util.Arrays;

public class RekursivePermutasjoner {

    public static void main(String[] args) {

        System.out.println("Recursive permutations!");

        int[] values = {1, 2, 3};
        // 1, 2, 3
        // 1, 3, 2
        // 2, 1, 3
        // 2, 3, 1
        // 3, 1, 2
        // 3, 2, 1

        recursive_permutation(values, 0);
    }


    public static void recursive_permutation (int[] values, int k) {

        if (k == values.length) {
            System.out.println(Arrays.toString(values));
        }

        // [1], 2, 3 => 1, 3, 2
        // 2, [1], 3 => 2, 3, 1
        // 3, 2, [1] => 3, 1, 2

        for (int i = k; i < values.length; i++) {

            swap(values, i, k);

            recursive_permutation(values, k + 1);

            swap(values, k, i);
        }

        //recursive_permutation(values);
    }


    public static void swap (int[] values, int m, int n) {

        // Eksempel: m = 2, n = 3
        // tmp = values[2]
        int tmp = values[m];

        // values[2] = values[3]
        values[m] = values[n];

        // values[3] = tmp = values[2]
        values[n] = tmp;
    }
}
