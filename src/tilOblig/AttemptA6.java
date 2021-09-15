package tilOblig;

import java.util.Arrays;

public class AttemptA6 {
    public static void main(String[] args) {
        char[] a = {'A', 'B', 'C', 'D', 'E'};

        System.out.println(Arrays.toString(a));

        rotasjon(a, 14);


        System.out.println(Arrays.toString(a));

        rotasjon(a, -2);

        System.out.println(Arrays.toString(a));

    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void delsortering(int[] a){

        // Fatima (s351947)


        for (int i = 0; i < a.length; i++){

            for (int j = i + 1; j < a.length; j++){

                if (a[i] > a[j]){

                    int temporary = a[i];
                    a[i] = a[j];
                    a[j] = temporary;

                    int venstre = 0;
                    int hoyre = a.length - 1;

                    while (a[venstre] % 2 != 0) {

                        venstre++;
                    }

                    while (a[hoyre] % 2 == 0) {

                        hoyre--;
                    }

                    if (venstre < hoyre) {

                        int temp = a[venstre];
                        a[venstre] = a[hoyre];
                        a[hoyre] = temp;
                    }
                }

            }

        }
        System.out.println(Arrays.toString(a));
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

        // Fatima (s351947)

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
            }

            for (int iLast = 0; iLast < Math.abs(k); iLast++) {
                a[a.length + iLast + k] = savedElements[iLast];
            }

        }

    }
}