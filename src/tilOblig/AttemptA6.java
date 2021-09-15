package tilOblig;

import java.util.Arrays;

public class AttemptA6 {

    ///// Oppgave 4 //////////////////////////////////////
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

    ///// Oppgave 5 //////////////////////////////////////

    public static void rotasjon(char[] a){

        /*
        Fatima (s351947)

        Koden inni denne for-loopen gjentar koden inni seg
        en gang for hver av bokstavene i "a"-arrayen

        "int i = 0" lager en variabel "int",
        før loopen lages. Den starter på 0, fordi i kode,
        så telles alt fra 0, og ikke 1
        "i < a.length" sier at koden skal fortsette
        mens "i" er mindre enn "a.length"
        ("a.length" er lengden til arrayen "a"),
        og koden inni loopen skal ikke kjøre om
        "i" blir like stor, eller større enn "a.length"
        "i++" sier at variabelen "i",
        skal øke med 1 hver gang koden inne i loopen utføres
         */


        for (int i = 0; i < a.length; i++) {

            // Siden "i" starter på 0, så utføres koden
            // inni her bare første gang for-loopen utføres

            if (i == 0) {

                // Denne skifter den første elementet i
                // "a"-arrayen (fordi i skal være 0 for at
                // koden her skal kjøre) til den siste
                // elementet i "a"-arrayen,
                // dette skifter - i denne tilfellen - den
                // første elementet i "a"-arrayen fra 'A'
                // til 'I'

                a[i] = a[a.length - 1];

                // "continue;" gjør at,
                // i stedet for at neste del av koden
                // utenfor "if"-blokken kjører,
                // så vil det gjøre at for-loopen tror
                // den er ferdig, og kjører igjen

                continue;
            }
            // "a[i] -= 1" gjør at elementet med indeksen "i"
            // i arrayen "a" blir til elementet med
            // én lavere indeks enn den.
            // Et eksempel: {'A', 'B', 'C', 'D'} --> {'A', 'A', 'C', 'D'} --> {'A', 'A', 'B', 'D'} --> ...
            a[i] -= 1;
        }

    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {

        // Et variabel som skal passe på at bokstavene skal ikke rotere for mange ganger
        int iteratekControl = Math.abs(k);

        int n = a.length;

        if (n <= 1 && n >= -1) {
            return;
        }

        while (iteratekControl > n - 1) {
            if (k > n - 1) {
                k -= n;
            } else if (k < -1 * (n - 1)) {
                k += n;
            }

            iteratekControl -= Math.abs(n);
        }

        char[] savedElements = new char[Math.abs(k)];

        if (k > 0) {
            for (int iSave = k; iSave > 0; iSave--) {
                savedElements[k - iSave] = a[n - iSave];
            }

            for (int iFirst = n - 1; iFirst >= k; iFirst--) {
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

            for (int iFirst = 0; iFirst < n + k; iFirst++) {
                a[iFirst] = a[iFirst - k];
            }

            for (int iLast = 0; iLast < Math.abs(k); iLast++) {
                a[n + iLast + k] = savedElements[iLast];
            }

        }

    }

}