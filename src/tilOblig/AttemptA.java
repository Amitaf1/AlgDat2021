package tilOblig;

import java.util.Arrays;

public class AttemptA {
    public static void main(String[] args) {
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        System.out.println(Arrays.toString(a));

        rotasjon(a, 1);

        System.out.println(Arrays.toString(a));

        rotasjon(a, -1);

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

            // Denne sjekker om lengden av arrayen "a" er større enn hvor mange enheter hver bokstav skal forflytte seg. Om lengden til "a" er større en dette tallet, så vil ikke resten av for-loopen kjøre
            if (a.length > k) {

                // "break;" gjør at, i stedet for at neste del av koden utenfor "if"-blokken kjører, så vil det gjøre at hele for-loopen blir ferdig
                break;
            }

            // Siden "i" starter på 0, så utføres koden inni her bare første gang for-loopen utføres
            if (i >= 1) {

                // Denne skifter den første elementet i "a"-arrayen (fordi i skal være 0 for at koden her skal kjøre) til den siste elementet i "a"-arrayen, dette skifter - i denne tilfellen - den første elementet i "a"-arrayen fra 'A' til 'I'
                a[i] = a[a.length - 1];

                // "continue;" gjør at, i stedet for at neste del av koden utenfor "if"-blokken kjører, så vil det gjøre at for-loopen tror den er ferdig, og kjører igjen
                continue;
            }

            // "a[i] -= 1" gjør at elementet med indeksen "i" i arrayen "a" blir til elementet med én lavere indeks enn den.
            // Et eksempel: {'A', 'B', 'C', 'D'} --> {'A', 'A', 'C', 'D'} --> {'A', 'A', 'B', 'D'} --> ...
            a[i] -= 1;
        }

    }

}
