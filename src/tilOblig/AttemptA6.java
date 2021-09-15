package tilOblig;

import java.util.Arrays;

public class AttemptA6 {

    public static void main(String[] args) {

        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        System.out.println(Arrays.toString(a));

        rotasjon(a, 3);

        System.out.println(Arrays.toString(a));

        rotasjon(a, -2);

        System.out.println(Arrays.toString(a));
    }

    public static void rotasjon(char[] a, int k){


        /*
        if (k > 0){

            for(int i = 0; i < k; i++) {

                char tmp = a[a.length - 1];

                for(int j = a.length - 1; j >= 0; j--) {
                    a[j] = j == 0 ? tmp : a[(j - 1 + a.length) % a.length];
                }
            }
        }

        else if (k < 0)
        {
            for (int i = 0; i < k - 1; i++){
                char tmp = a[a.length - 1];

                for (int j = a.length - 1; j <= 0; j--){

                    a[j]--;
                }
            }
        }
        */
        for (int i = 0; i < a.length; i++){

            char tmp = a[i];
            a[i] = a[i-1];
            a[i-1] = tmp;

            if (a[i] < 0){
                continue;
            }

            a[i]++;
        }
    }
}
