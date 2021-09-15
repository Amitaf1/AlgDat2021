package tilOblig;

import java.util.Arrays;

public class AttemptA {
    public static void main(String[] args) {
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        System.out.println(Arrays.toString(a));

        rotasjon(a, -21);

        System.out.println(Arrays.toString(a));

        rotasjon(a, 5);

        System.out.println(Arrays.toString(a));

    }

    public static void rotasjon(char[] a, int k) {

        // Et variabel som skal passe på at bokstavene skal ikke rotere for mange ganger
        int iteratekControl = Math.abs(k);

        if (a.length <= 1 && a.length >= -1) {
            return;
        }

        while (iteratekControl > a.length - 1) {
            if (k > a.length - 1) {
                k -= a.length;
            }
            else if (k < -1 * (a.length - 1)) {
                k += a.length;
            }

            iteratekControl -= Math.abs(a.length);
        }


        char[] savedElements = new char[Math.abs(k)];


        if (k > 0) {
            for (int iSave = k; iSave > 0; iSave--) {
                savedElements[k - iSave] = a[a.length - iSave];
            }

            for (int iFirst = a.length - 1; iFirst >= k; iFirst--) {
                a[iFirst] = a[iFirst - k];
            }

            for (int iLast = 0; iLast < Math.abs(k); iLast++) {
                a[iLast] = savedElements[iLast];
            }

            
        }

        
        if (k < 0) {
            for (int iSave = 0; iSave < Math.abs(k); iSave++) {
                savedElements[iSave] = a[iSave];
            }

            for (int iFirst = 0; iFirst < a.length + k; iFirst++) {
                a[iFirst] = a[iFirst - k];
            }

            for (int iLast = 0; iLast < Math.abs(k); iLast++) {
                a[a.length + iLast + k] = savedElements[iLast];
            }

        }

    }

}
