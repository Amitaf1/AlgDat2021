package exercises.week1;

/**
 * Windows: [alt] + [enter] mens du holder på klassen for å lage tester
 * Mac: [option] + [enter] --"--
 */

public class MyFirstSort {


    public static void main(String[] args) {

        //System.out.println("Hello algdat");

        int[] values = {1, 7, 2, 4, 6, 9};

        myFirstSort(values);
    }

    /**
     * Denne funksjonen tar inn ett array med verdier (heltall),
     * og sorterer dem "in place"
     * @param values Verdier vi skal sortere.
     */

    public static void myFirstSort(int[] values) {
        for (int k = 0; k < values.length - 1; k++) {

            //Sjekk at vi får fornuftig svar for *ett* tilfelle.
            //Vi må utføre ordentlig testing før vi
            //faktisk kan stole på kildekoden vår

            int max_index = findMaxIndex(values, k, values.length);

            //Debug printing used when developing:
            //System.out.println("Største verdi ligger på plass "
            //        + max_index + " og har verdi " + values[max_index]);

            //Bytte plass på tall på plass 0 og max_index

            int temp = values[k];

            values[k] = values[max_index];

            values[max_index] = temp;

            //Debug printing used when developing:
            //System.out.println("Bytter plass " + k + " med plass " + max_index);
            //System.out.println("Arrayet etter ombytting");
            //for (int i = 0; i < values.length; i++) {
            //    System.out.print(values[i] + ", ");
            //}
            //System.out.println();
            }
        }

        /**
         * Findmax - finner index til største tall i et array,
         * men søker bare innenfor tallene i [fra, til)
         */

        public static int findMaxIndex(int[] values, int fra, int til) {

            //Initialiser ved å se på første "kort"
            //Dette er det største tallet jeg har funnet
            //så langt

            int index = fra;

            int max_value = values[fra];

            //Sjekk at grensene for løkken er riktig.
            // [1, values.length)

            for (int i = fra + 1; i < til; i++) {

                //Sjekk om vi har funnet nytt største tall

                if (values[i] > max_value) {
                    max_value = values[i];
                    index = i;
                }
            }

            return index;
        }

        /**
         * findTwoMaxIndices - finner index til største og nest største
         * tall i et array, men søker bare innenfor tallene i
         * [fra, til)
         */

        public static int findTwoMaxIndices(int[] values, int fra, int til) {

            //Test at grensene gir mening

            if (til-fra < 2) {
                throw new ArrayIndexOutOfBoundsException("Feil i grenser");
            }
            if (fra < 0) {
                throw new ArrayIndexOutOfBoundsException("Fra er negativ");
            }

            //Test at vi ikke har for stor til-verdi
            //values.length = 6
            //Indekser:             0  1  2  3  4  5
            //Starter med verdiene {1, 7, 2, 4, 6, 9};

            if (til > values.length) {
                throw new ArrayIndexOutOfBoundsException("Til er ikke lang nok");
            }

            //Initialiser ved å se på første to verdier (1, 7)
            //Dette er det største tallet og nest største jeg har funnet
            //så langt

            int index_max = fra; //1

            int index_nestmax = fra+1; //7

            int max_value = values[fra]; // 1
            int nest_max_value = values[fra+1]; // 7

            //Hvis nest_max_value har en verdi som er større
            //enn variabelen max_value, så må vi bytte dem.

            if (max_value < nest_max_value) {
                int temp = max_value;
                max_value = nest_max_value;
                nest_max_value = temp;

                temp = index_max;
                index_max = index_nestmax;
                index_nestmax = temp;
            }

            //Status så langt:
            // Array: {1, 7, 2, 4, 6, 9};
            // Vi har sett på de to første elementene {1, 7}
            // max_value = 7, index_max = 1
            // nest_max_value = 1, index_nestmax = 0
            // Nå må vi løpe gjennom resten av arrayet

            //Sjekk at grensene for løkken er riktig.
            // Intervallet vårt er [fra, til)
            //Siden vi har sjekket de to første tallene, begynner vår
            //for-løkke på fra+2.

            for (int i=fra+2; i<til; i++) {

                //Tilfelle 1: nytt største tall:

                if (values[i] > max_value) {

                    //Oppdater største og nest største verdi

                    nest_max_value = max_value; // tallet 7
                    max_value = values[i];      // f.eks. 14

                    //oppdater indeksene på samme måte

                    index_nestmax = index_max;
                    index_max = i;
                }
                else if (values[i] > nest_max_value) {

                    //Oppdater nest største verdi og index

                    nest_max_value = values[i];
                    index_nestmax = i;
                }
                else {
                    //Ingenting, max og nest max er større begge to.
            }
        }

        return index_nestmax;
    }
}

