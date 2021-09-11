package tilOblig;

import java.util.Arrays;

public class Attempt1 {

    public static void main(String[] args) {

        int[] a = {6, 10, 9, 4, 1, 3, 8, 5, 2, 7};

        delsortering(a);
    }

    public static void delsortering(int[] a){


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
}