package exercises.week1.section111;

import static exercises.week1.section111.Program.*;


public class Task1 {
    public static void main(String[] args) {

        /*
        int n = 100_000;
        System.out.println(antallMaks(randPerm(n)));
         */

        int n = 100_000, antall = 2_000;
        long tid = 0;
        int[] a = randPerm(n);

        tid = System.currentTimeMillis();

        for (int i = 0; i < antall; i++) kostnader(a);

        tid = System.currentTimeMillis() - tid;
        System.out.println("Faste kostnader: " + tid + " millisek");

        tid = System.currentTimeMillis();

        for (int i = 0; i < antall; i++) Program.maks1(a);

        tid = System.currentTimeMillis() - tid;
        System.out.println("Maks1-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();

        for (int i = 0; i < antall; i++) maks2(a);

        tid = System.currentTimeMillis() - tid;
        System.out.println("Maks2-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();

        for (int i = 0; i < antall; i++) maks3(a);

        tid = System.currentTimeMillis() - tid;
        System.out.println("Maks3-metoden: " + tid + " millisek");
    }
}