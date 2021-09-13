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
            return;
        }

        char[] savedElements = new char[5];

        for (int i = 0; i < Math.abs(k); i++) {
            if (k > 0) {
                savedElements[i] = a[a.length - k + i];
            }
            else if (k < 0) {
                savedElements[i] = a[i];
            }
        }

        System.out.println(savedElements);

        String aDebug = Arrays.toString(a);


        if (k > 0) {
            for (int iFirst = a.length - 1; iFirst >= k; iFirst--) {
                a[iFirst] = a[iFirst - k];
            }

            for (int iLast = 0; iLast < Math.abs(k); iLast++) {
                a[iLast] = savedElements[iLast];
            }

            
        }

        
        if (k < 0) {
            for (int iFirst = 0; iFirst < a.length + k; iFirst++) {
                a[iFirst] = a[iFirst - k];
                aDebug = Arrays.toString(a);
            }

            for (int iLast = 0; iLast < Math.abs(k); iLast++) {
                a[a.length + k] = savedElements[iLast];
            }

        }

    }

}
