package tilOblig;

import java.util.Arrays;

public class Attempt1 {

    public static void main(String[] args) {

        int[] a = {6, 10, 9, 4, 1, 3, 8, 5, 2, 7};

        delsortering(a);

        System.out.println(Arrays.toString(a));
    }

    public static void delsortering(int[] a) {

        int n = a.length;

        if (n == 0) {
            return;
        }

        int pLen = 0;
        int oLen = 0;


        for (int i : a) {
            if (i % 2 == 0) {
                pLen += 1;
            } else {
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

        int oLav = 0;
        int oHøy = oLen - 1;
        int pLav = 0;
        int pHøy = pLen - 1;


        quickSort(oddetall, oLav, oHøy);
        quickSort(partall, pLav, pHøy);


        for (int i = 0; i < oLen; i++) {
            a[i] = oddetall[i];
        }

        for (int i = 0; i < pLen; i++) {
            a[i + oLen] = partall[i];
        }
    }

    public static int partisjon(int[] a, int lav, int høy) {
        int mellom = a[høy];
        int i = lav - 1;

        for (int j = lav; j <= høy; j++) {
            if (a[j] < mellom) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        int temp = a[i + 1];
        a[i + 1] = a[høy];
        a[høy] = temp;

        return (i + 1);
    }

    public static void quickSort(int[] array, int lav, int høy) {
        if (lav < høy) {
            int mellom = partisjon(array, lav, høy);

            quickSort(array, lav, mellom - 1);
            quickSort(array, mellom + 1, høy);
        }
    }

}
