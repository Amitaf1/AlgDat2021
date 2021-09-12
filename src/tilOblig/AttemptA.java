package tilOblig;

import java.util.Arrays;

public class AttemptA {
    public static void main(String[] args) {
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        System.out.println(Arrays.toString(a));

        rotasjon(a, 3);

        System.out.println(Arrays.toString(a));

        rotasjon(a, -2);

        System.out.println(Arrays.toString(a));

    }

    public static void rotasjon(char[] a, int k) {

        /*
        Koden inni denne for-loopen gjentar koden inni seg en gang for hver av bokstavene i "a"-arrayen

        "int i = 0" lager en variabel "int", før loopen lages. Den starter på 0, fordi i kode, så telles alt fra 0, og ikke 1
        "i < a.length" sier at koden skal fortsette mens "i" er mindre enn "a.length" ("a.length" er lengden til arrayen "a"), og koden inni loopen skal ikke kjøre om "i" blir like stor, eller større enn "a.length"
        "i++" sier at variabelen "i", skal øke med 1 hver gang koden inne i loopen utføres
         */
        for (int i = 0; i < a.length; i++) {

            // Denne sjekker om lengden av arrayen "a" er mindre enn hvor mange enheter hver bokstav skal forflytte seg. Om lengden til "a" er mindre en dette tallet, så vil ikke resten av for-loopen kjøre
            if (a.length <= k || a.length <= -k) {

                // "break;" gjør at, i stedet for at neste del av koden utenfor "if"-blokken kjører, så vil det gjøre at hele for-loopen blir ferdig
                break;
            }

            // Denne delen skal bare utføres om "k" er større enn 0 (f.eks: 1, 2, ...)
            // Den skal også bare utføres mens "i" er mindre enn "k"
            if (k > 0 && i < k) {

                // Denne skifter den første elementet i "a"-arrayen (fordi "k" skal være større enn 0 for at koden her skal kjøre) til den siste elementet i "a"-arrayen, dette skifter - i denne tilfellen - den første elementet i "a"-arrayen fra 'A' til 'I'
                a[i] = a[a.length - k + i];

                // "continue;" gjør at, i stedet for at neste del av koden utenfor "if"-blokken kjører, så vil det gjøre at for-loopen tror den er ferdig, og kjører igjen
                continue;
            }

            // Denne delen skal bare utføres om "k" er mindre enn 0 (f.eks: -1, -2, ...)
            // Den skal også bare utføres mens "i" er stor nok for at de siste elementene som skal byttes ut (de siste to elementene i arrayen om "k" er -2), skal byttes ut fra den andre siden
            if (k < 0 && i > a.length + k - 1) {

                // Denne skifter den de siste elementene i "a"-arrayen (fordi "k" skal være mindre enn 0 for at koden her skal kjøre) til dee første elementet i "a"-arrayen
                // Et eksempel: {}
                a[i] -= a.length + k;

                // "continue;" gjør at, i stedet for at neste del av koden utenfor "if"-blokken kjører, så vil det gjøre at for-loopen tror den er ferdig, og kjører igjen
                continue;
            }


            // "a[i] -= 1" gjør at elementet med indeksen "i" i arrayen "a" blir til elementet med én lavere indeks enn den.
            // Et eksempel: {'A', 'B', 'C', 'D'} --> {'A', 'A', 'C', 'D'} --> {'A', 'A', 'B', 'D'} --> ...
            a[i] -= k;
        }

    }

}
