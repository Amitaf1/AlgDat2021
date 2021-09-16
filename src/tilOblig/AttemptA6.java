package tilOblig;

import java.util.Arrays;

public class AttemptA6 {

    public static void main(String[] args) {
        int[] a = {6, 10, 9, 4, 1, 3, 8, 5, 2, 7};

        delsortering(a);

        System.out.println(Arrays.toString(a));
    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void delsortering(int[] a) {

        // Lager en int som heter "n" dette skal være lengden til arrayen som vi begynte med
        // Vi gjør dette for at koden skal ikke regne ut lengden til hoved-arrayen hver gang vil vil ha den
        int n = a.length;


        // Dette gjør det slik at, om lengden av arrayen er lik null, så vil ikke koden kjøre
        // Dette er for at ingen feil skal forekomme i koden
        if (n == 0) {
            return;
        }


        // Disse to linjene finner ut indeksen til den første og siste tallet i hoved-arrayen
        int a_først = 0;
        int a_sist = n - 1;


        // Dette skal sortere alle tallene
        // Kommentarer er inni metodene nede
        quickSort(a, n, a_først, a_sist);


        // Lager to tall som skal representere lengden til arrayen som skal inneholde partall og oddetall
        int partall_lengde = 0;
        int oddetall_lengde = 0;


        // En for loop som går igjennom hver element i arrayen
        for (int i : a) {

            // Om tallet er partall, så skal tallet som skal bestemme lengden til arrayen for partall, øke med en
            // Dette skjer for at partalls-arrayen skal ha muligheten til å romme alle partall
            if (i % 2 == 0) {
                partall_lengde += 1;
            }

            // Om tallet er ikke partall, så vil den være oddetall
            else {
                oddetall_lengde += 1;
            }
        }

        // Vi lager nå arrayen for partall og oddetall
        // Lengden har vi hentet ut fra forrige for-loop
        int[] partall = new int[partall_lengde];
        int[] oddetall = new int[oddetall_lengde];

        // Disse tallene skal hjelpe oss for at indeksen til tallene i partall og oddetall, ikke kommer ut feil
        // Disse skal holde styr på hvor mange ganger vi har satt tall inn i hver av de to arrayene
        int partall_ganger = 0;
        int oddetall_ganger = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {

                // Om tallet er partall, så skal tallet kopieres inn i partalls-arrayen
                /*
                Vi tar "i - oddetall-ganger", for om vi hadde hatt bare "i" så vil koden
                legge et partall på feil indeks i partalls-arrayen om et oddetall har blitt lagd inn før den
                 */
                partall[i - oddetall_ganger] = a[i];

                // Etter at vi har lagd inn et partall, så vil partall_ganger øke
                // Fordi da vil koden vite å trekke fre èn mer fra oddetall
                partall_ganger += 1;
            }
            else {
                oddetall[i - partall_ganger] = a[i];
                oddetall_ganger += 1;
            }
        }


        // For hver oddetall i oddetall-arrayen, så vil den erstatte det tallet fra hoved-arrayet med oddetallet
        for (int i = 0; i < oddetall_lengde; i++) {
            a[i] = oddetall[i];
        }

        /*
        Lengden til oddetalls-arrayenindeksen til a-indeksen, fordi alle oddetall har blitt lagd til
        a-arrayen allerede
        */
        for (int i = 0; i < partall_lengde; i++) {
            a[i + oddetall_lengde] = partall[i];
        }
    }

    // Lager en metode som skal holde styr på partisjonene
    public static int partisjon(int[] a, int n, int først, int sist) {

        // Dette velger den siste elementet i a-arrayen som partisjon
        int partisjon_tall = a[sist];


        // Dette gjør klar for at den første elementet skal velges
        // Om man bare gjør "i = først", så vil det ende opp i at koden kjører seg uendelig ganger
        int i = først - 1;

        // Denne delen av koden kjører en gang for hver at tallene i a-arrayen
        for (int j = først; j <= sist; j++) {

            /*
            Denne delen av koden bare kjører om elementet (teller fra null) i a-arrayen er mindre enn tallet
            som er partisjonstallet (for å sammenligne)
            */
            if (a[j] < partisjon_tall) {
                i++;

                /*
                Denne delen av koden minker indeksen til et element i a-arrayen, dette skjer ved
                å lage en midlertidig kopi av den elementet som brukes for å bytte elementets posisjon
                med elementet med indeks i
                Eksempel: "1, 4, 5, 2, 5" --> "1, 2, 5, 4, 3"
                */
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        // Denne delen skifter det første elementet som er større enn partisjonstallet, med partisjonstallet
        int temp = a[i + 1];
        a[i + 1] = a[sist];
        a[sist] = temp;

        // Dette gir tilbake tallet som er partisjonstallet
        return (i + 1);
    }

    // Selve sorteringsmetoden
    public static void quickSort(int[] a, int n, int først, int sist) {
        if (først < sist) {

            // Indeks på partisjonen
            int partisjon_indeks = partisjon(a, n, først, sist);

            // Disse to linjene lager nye partisjoner inni de to partisjonene som ble lagd
            // En for alle tall som er større enn partisjonstallet
            // En for alle tall som er mindre enn partisjonstallet
            // Dette fortsetter helt til hver partisjon er èn element lang
            // Deretter skal partisjonene bygges opp igjen
            quickSort(a, n, først, partisjon_indeks - 1);
            quickSort(a, n, partisjon_indeks + 1, sist);
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

        // Lager en int som heter "n" dette skal være lengden til arrayen som vi begynte med
        // Vi gjør dette for at koden skal ikke regne ut lengden til hoved-arrayen hver gang vil vil ha den
        int n = a.length;


        // Dette gjør det slik at, om lengden av arrayen er mindre enn en, så vil ikke koden kjøre
        // Dette er for at ingen feil skal forekomme i koden
        if (n <= 1) {
            return;
        }

        // Dette skal lagre de elementene som skal lagres for senere bruk (den er nå tom)
        char[] saved_elements = new char[Math.abs(k)];

        if (k > 0) {
            // Gjør at forskyvelsen blir mer effektiv, ved å trekke fra lengden til a
            // Eksempel: Om lengden til a er 10, så vil det å forskyve 101 elementer være det samme
            // som å forskyve 1 element
            while (k > n - 1) {
                k -= n;
            }

            // Lagrer de elementene som må lagres (antall elementer som forskyves)
            for (int i = k; i > 0; i--) {
                saved_elements[k - i] = a[n - i];
            }

            // Forskyver elementene
            for (int j = n - 1; j >= k; j--) {
                a[j] = a[j - k];
            }

            // Setter inn de lagrede elementene i starten
            for (int l = 0; l < Math.abs(k); l++) {
                a[l] = saved_elements[l];
            }

        }

        if (k < 0) {
            while (k < -1 * (n - 1)) {
                k += n;
            }

            for (int i = 0; i < Math.abs(k); i++) {
                saved_elements[i] = a[i];
            }

            for (int j = 0; j < n + k; j++) {
                a[j] = a[j - k];
            }

            for (int l = 0; l < Math.abs(k); l++) {
                a[n + l + k] = saved_elements[l];
            }

        }

    }

}