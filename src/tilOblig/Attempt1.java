package tilOblig;

import java.util.Arrays;

public class Attempt1 {

    /*public static void main(String[] args) {

        int[] a = {6, 10, 9, 4, 1, 3, 8, 5, 2, 7};

        delsortering(a);

        System.out.println(Arrays.toString(a));
    }*/

    public static void delsortering(int[] a) {

        int n = a.length;

        if (n == 0) {
            return;
        }

        int pLen = 0;
        int oLen = 0;


        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                pLen += 1;
            }
            else{
                oLen += 1;
            }
        }

        int[] partall = new int[pLen];
        int[] oddetall = new int[oLen];
        int delingP = 0;
        int delingO = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                partall[i - delingO] = a[i];
                delingP += 1;
            }
            else{
                oddetall[i - delingP] = a[i];
                delingO += 1;
            }
        }

        for (int i = 0; i < pLen - 1; i++) {
            for (int j = pLen - 1; j > i; j--) {
                if (partall[j] < partall[j - 1]) {
                    int temp = partall[j];
                    partall[j] = partall[j - 1];
                    partall[j - 1] = temp;
                }
            }
        }

        for (int i = 0; i < oLen - 1; i++) {
            for (int j = oLen - 1; j > i; j--) {
                if (oddetall[j] < oddetall[j - 1]) {
                    int temp = oddetall[j];
                    oddetall[j] = oddetall[j - 1];
                    oddetall[j - 1] = temp;
                }
            }
        }

        for (int i = 0; i < oLen; i++) {
            a[i] = oddetall[i];
        }

        for (int i = 0; i < pLen; i++) {
            a[i + oLen] = partall[i];
        }
    }
}
