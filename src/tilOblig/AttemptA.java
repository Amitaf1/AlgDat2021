package tilOblig;

import java.util.NoSuchElementException;

public class AttemptA {

    public static void main(String[] args) {

        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        System.out.println(a);

        rotasjon(a);
        System.out.println(a);
    }

    public static void rotasjon(char[] a){


        for (int i = 0; i < a.length; i++) {


            if (i == 0) {
                a[i] = a[a.length - 1];
                continue;
            }
            a[i]--;
        }

    }
}



