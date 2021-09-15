package tilOblig;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AttemptATest {

    ///// Oppgave 4 //////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave4() {
        int antallFeil = 0;

        int[] a = {};   // skal ikke kaste unntak her!

        try {
            AttemptA.delsortering(a);  // kaller metoden
        } catch (Exception ex) {
            System.out.println
                    ("Oppgave 4: a) Ikke unntak for en tom tabell!");
            antallFeil++;
        }

        a = new int[]{5};
        int[] b = {5};

        try {
            AttemptA.delsortering(a);  // kaller metoden
        } catch (Exception ex) {
            System.out.println
                    ("Oppgave 4: b) Skal ikke kastes unntak her!");
            antallFeil++;
        }

        if (!Arrays.equals(a, b)) {
            System.out.println
                    ("Oppgave 4: c) Metoden gjør feil for en tabell en verdi!");
            antallFeil++;
        }

        a = new int[]{4};
        b = new int[]{4};

        try {
            AttemptA.delsortering(a);  // kaller metoden
        } catch (Exception ex) {
            System.out.println
                    ("Oppgave 4: d) Skal ikke kastes unntak her!");
            antallFeil++;
        }

        if (!Arrays.equals(a, b)) {
            System.out.println
                    ("Oppgave 4: e) Metoden gjør feil for en tabell en verdi!");
            antallFeil++;
        }

        a = new int[]{4, 2, 6, 10, 8};
        b = new int[]{2, 4, 6, 8, 10};

        try {
            AttemptA.delsortering(a);  // kaller metoden
        } catch (Exception ex) {
            System.out.println
                    ("Oppgave 4: f) Det går galt hvis det kun er partall!");
            antallFeil++;
        }

        if (!Arrays.equals(a, b)) {
            System.out.println
                    ("Oppgave 4: g) Det blir feil hvis det kun er partall!");
            antallFeil++;
        }

        a = new int[]{9, 5, 3, 1, 7};
        b = new int[]{1, 3, 5, 7, 9};

        try {
            AttemptA.delsortering(a);  // kaller metoden
        } catch (Exception ex) {
            System.out.println
                    ("Oppgave 4: h) Det går galt hvis det kun er oddetall!");
            antallFeil++;
        }

        if (!Arrays.equals(a, b)) {
            System.out.println
                    ("Oppgave 4: i) Det blir feil hvis det kun er oddetall!");
            antallFeil++;
        }

        a = new int[]{1, 2, 3, 4, 5, 6};
        b = new int[]{1, 3, 5, 2, 4, 6};
        boolean flere = true;

        do {
            int[] c = a.clone();
            AttemptA.delsortering(c);

            if (!Arrays.equals(c, b)) {
                System.out.println
                        ("Oppgave 4: j) Gitt tabell:     " + Arrays.toString(a));
                System.out.println
                        ("              Metoden skal gi: " + Arrays.toString(b));
                System.out.println
                        ("              Du fikk:         " + Arrays.toString(c));

                antallFeil++;
                break;
            }
        } while (nestePermutasjon(a));

        a = new int[]{-4, -1, 3, 0, 2, -3, -2, 4, 1};
        b = new int[]{-3, -1, 1, 3, -4, -2, 0, 2, 4};

        try {
            AttemptA.delsortering(a);  // kaller metoden
        } catch (Exception ex) {
            System.out.println
                    ("Oppgave 4: k) Skal ikke kastes unntak her!");
            antallFeil++;
        }

        if (!Arrays.equals(a, b)) {
            System.out.println
                    ("Oppgave 4: l) Metoden gjør feil for negative verdier!");
            antallFeil++;
        }

        if (antallFeil == 0) {
            a = randPerm(100000);
            long tid = System.currentTimeMillis();
            AttemptA.delsortering(a);
            tid = System.currentTimeMillis() - tid;

            for (int i = 0; i < 50000; i++) {
                if (a[i] != 2 * i + 1) {
                    System.out.println
                            ("Oppgave 4: m) Feil resultat for denne tabellen!");
                    antallFeil++;
                    break;
                }
            }

            for (int i = 50000; i < 100000; i++) {
                if (a[i] != 2 * (i - 49999)) {
                    System.out.println
                            ("Oppgave 4: n) Feil resultat for denne tabellen!");
                    antallFeil++;
                    break;
                }
            }

            if (tid > 100) {
                System.out.println
                        ("Oppgave 4: o) Tid: " + tid + ". Metoden er for ineffektiv!");
                System.out.println
                        ("              Hint: Bruk en partisjoneringsteknikk!");
                antallFeil++;
            }
        }

        assertEquals(0, antallFeil, "Du har for mange feil i oppgave 4");
    }

    ///// Oppgave 5 //////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave5() {
        int antallFeil = 0;

        char[] a = {};

        try {
            AttemptA.rotasjon(a);  // kaller metoden
        } catch (Exception e) {
            System.out.println
                    ("Oppgave 5: a) Skal ikke kaste unntak for en tom tabell!!");
            antallFeil++;
        }

        char[] b = {'A'};
        char[] b0 = {'A'};
        AttemptA.rotasjon(b);
        if (!Arrays.equals(b, b0)) {
            System.out.println("Oppgave 5: b) Feil hvis tabellen har ett element!");
            antallFeil++;
        }

        char[] c = {'A', 'B'};
        char[] c0 = {'B', 'A'};
        AttemptA.rotasjon(c);
        if (!Arrays.equals(c, c0)) {
            System.out.println("Oppgave 5: c) Feil hvis tabellen har to elementer!");
            antallFeil++;
        }

        char[] d = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        char[] d0 = {'J', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        AttemptA.rotasjon(d);
        if (!Arrays.equals(d, d0)) {
            System.out.println("Oppgave 5: d) Feil hvis tabellen har flere elementer!");
            antallFeil++;
        }

        assertEquals(0, antallFeil, "Du har for mange feil i oppgave 5");
    }

    ///// Oppgave 6 //////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave6() {
        int antallFeil = 0;

        char[] a = {};

        try {
            AttemptA.rotasjon(a, 1);  // kaller metoden
        } catch (Exception e) {
            System.out.println(e);
            System.out.println
                    ("Oppgave 6: a) Skal ikke kaste unntak for en tom tabell!!");
            antallFeil++;
        }

        char[] b = {'A'};
        char[] b0 = {'A'};
        AttemptA.rotasjon(b, 0);
        if (!Arrays.equals(b, b0)) {
            System.out.println("Oppgave 6: b) Feil hvis tabellen har ett element!");
            antallFeil++;
        }

        AttemptA.rotasjon(b, 1);
        if (!Arrays.equals(b, b0)) {
            System.out.println("Oppgave 6: c) Feil hvis tabellen har ett element!");
            antallFeil++;
        }

        AttemptA.rotasjon(b, -1);
        if (!Arrays.equals(b, b0)) {
            System.out.println("Oppgave 6: d) Feil hvis tabellen har ett element!");
            antallFeil++;
        }

        char[] c = {'A', 'B'};
        char[] c0 = {'B', 'A'};
        AttemptA.rotasjon(c, 1);

        if (!Arrays.equals(c, c0)) {
            System.out.println("Oppgave 6: e) Feil hvis tabellen har to elementer!");
            antallFeil++;
        }

        c = new char[]{'A', 'B'};

        AttemptA.rotasjon(c, -1);
        if (!Arrays.equals(c, c0)) {
            System.out.println("Oppgave 6: f) Feil hvis tabellen har to elementer!");
            antallFeil++;
        }

        char[] d = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        char[] d0 = {'G', 'H', 'I', 'J', 'A', 'B', 'C', 'D', 'E', 'F'};

        AttemptA.rotasjon(d, 4);
        if (!Arrays.equals(d, d0)) {
            System.out.println("Oppgave 6: g) Feil hvis tabellen har flere elementer!");
            antallFeil++;
        }

        d = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        AttemptA.rotasjon(d, -6);
        if (!Arrays.equals(d, d0)) {
            System.out.println("Oppgave 6: h) Feil hvis tabellen har flere elementer!");
            antallFeil++;
        }

        char[] x = new char[100_000];
        long tid = System.currentTimeMillis();
        AttemptA.rotasjon(x, 99_999);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 6: i) Metoden "
                    + "er for ineffektiv. Må forbedres!");
            antallFeil++;
        }

        tid = System.currentTimeMillis();
        AttemptA.rotasjon(x, 199_999);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 6: j) Metoden "
                    + "er for ineffektiv. Må forbedres!");
            antallFeil++;
        }

        tid = System.currentTimeMillis();
        AttemptA.rotasjon(x, 50_000);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 6: k) Metoden "
                    + "er for ineffektiv. Må forbedres!");
            antallFeil++;
        }

        tid = System.currentTimeMillis();
        AttemptA.rotasjon(x, -40_000);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 6: l) Metoden "
                    + "er for ineffektiv. Må forbedres!");
            antallFeil++;
        }

        assertEquals(0, antallFeil, "Du har for mange feil i oppgave 6");
    }

    ///// Hjelpemetoder /////////////////////////////

    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean nestePermutasjon(int[] a) {
        int n = a.length;
        int i = n - 2;

        while (i >= 0 && a[i] > a[i + 1]) i--;

        if (i < 0) return false;

        int verdi = a[i];
        int j = n - 1;

        while (verdi > a[j]) j--;
        bytt(a, i, j);

        i++;
        j = n - 1;
        while (i < j) bytt(a, i++, j--);
        return true;
    }

    public static int[] randPerm(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall
        for (int i = 0; i < n; i++)
            a[i] = i + 1;                  // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
        {
            int i = r.nextInt(k + 1);        // en tilfeldig tall fra 0 til k

            int temp = a[k];
            a[k] = a[i];
            a[i] = temp;
        }

        return a;                        // permutasjonen returneres
    }
}