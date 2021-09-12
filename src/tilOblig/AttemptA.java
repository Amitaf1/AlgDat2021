package tilOblig;

import java.util.Arrays;

public class AttemptA {
    public static void main(String[] args) {
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        int iterate = 1;

        System.out.println(Arrays.toString(a));

        rotasjon(a, 3, iterate);

        iterate += 1;

        System.out.println(Arrays.toString(a));

        rotasjon(a, -2, iterate);

        System.out.println(Arrays.toString(a));

    }

    public static void rotasjon(char[] a, int k, int iterate) {

        if (a.length <= k || a.length <= -k) {
            break;
        }

        char[] savedElements = new char[5];

        for (int j = 0; j < Math.abs(k); j++) {
            if (k > 0) {
                savedElements[j] = a[a.length - k + j];
            }
            else if (k < 0) {
                savedElements[j] = a[j];
            }
        }

        System.out.println(savedElements);

        String aDebug = Arrays.toString(a);


        if (k > 0) {
            for (int i = 0; i < a.length; i++) {
                a[i] = a[i + k];
            }

            
        }

        
        if (k < 0) {
            for (int j = a.length - 1; j > 0; j--) {
                a[j] = a[j + k];
            }

        }


        for (int q = 0; q < Math.abs(k); q++) {
            if (k < 0) {
                a[q] = savedElements[a.length - k + q];
            } else if (k > 0) {
                a[q] = savedElements[q];;
            }
        }

    }

}
