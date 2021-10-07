package tilOblig2;

import java.util.*;

public class DobbeltLenketListe <T> implements Liste<T> {

    public static void main(String[] args) {
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>();

        liste = new DobbeltLenketListe<>(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"});

        System.out.println(liste.toString());

        liste.fjern("A");
        System.out.println(liste.toString());

        liste.fjern("B");
        System.out.println(liste.toString());

        liste.fjern("D");
        System.out.println(liste.toString());

        liste.fjern("F");
        System.out.println(liste.toString());

        liste.fjern("G");
        System.out.println(liste.toString());

        liste.fjern("I");
        System.out.println(liste.toString());

        liste.fjern("C");
        System.out.println(liste.toString());

        liste.fjern("E");
        System.out.println(liste.toString());

        liste.fjern("H");
        System.out.println(liste.toString());

    }

    /**
     * Klassen lager en ny node for en dobbelt lenket liste
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        /**
         * Konstruktøren for klassen Node (tre parametere)
         *
         * Konstruktøren tar inn tre parametere
         * for å lage en node og sette neste-pekeren til neste og forrige-pekeren til forrige
         *
         * @param verdi setter noden sin verdi
         * @param forrige setter forrige-pekeren for noden
         * @param neste setter neste-pekeren for noden
         */
        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        /**
         * Konstruktøren for klassen Node (en parameter)
         *
         * Konstruktøren tar inn en parameter
         * for å lage en node og sette neste- og forrige-pekerne til null
         *
         * @param verdi setter noden sin verdi
         */
        private Node(T verdi) {
            this(verdi, null, null);
        }

    }

    /**
     * Metoden finner og returnerer noden på indeksen som settes
     *
     * (her skal "indeks" tolkes som om den dobbelt lenkede listen var en tabell og hver node hadde sin egen indeks)
     *
     * Indeksen finnes ved iterativ traversering
     *
     * @param indeks setter indeksen som metoden skal prøve å finne i den dobbelt lenkede listen
     * @return skal returnere noden som metoden finner
     */
    private Node<T> finnNode(int indeks) {
        // Lager en midlertidig node som skal finne noden på indeksen
        Node<T> p;

        // For å øke effektiviteten til metoden, teller metoden fra starten
        // om indeks er mindre enn halvparten av lengden og fra slutten ellers

        // Kjører om indeks er mindre enn halvparten av lengden av den dobbelt lenkede listen
        if (indeks <= this.antall / 2) {
            // p starter fra hoden
            p = this.hode;

            // for-loopen gjør p til noden som ligger foran den indeks ganger
            // fordi da vil vi finne noden som ligger på den spesifiserte "indeksen"
            for (int i = 0; i < indeks; i++) { p = p.neste; }
        }

        // Kjører om indeks er større enn halvparten av lengden av den dobbelt lenkede listen
        else {
            // p starter fra halen
            p = this.hale;

            // for-loopen gjør p til noden som ligger bak den antall - indeks ganger (indeks invertert)
            // fordi da vil vi finne noden som ligger på den spesifiserte "indeksen"
            for (int i = this.antall - indeks - 1; i > 0; i--) { p = p.forrige; }
        }

        // returnerer noden som ble funnet
        return p;
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall ikke-null elementer i listen
    private int endringer;         // antall endringer i listen

    /**
     * Konstruktør
     *
     * Setter instansvariablene til startverdiene sine
     */
    public DobbeltLenketListe() {
        this.hode = null;
        this.hale = null;
        this.antall = 0;
        this.endringer = 0;
    }

    /**
     * Konstruktør
     *
     * Tar inn tabellen a, og lager en ny dobbelt lenket liste
     * der hver verdi i tabellen blir til en node i den dobbelt lenkede listen
     *
     * Om a er null, så kastes en NullPointerException
     * Om a er tom, så lages ikke en ny dobbelt lenket liste
     *
     * @param a skal være tabellen som blir gjort om til en dobbelt lenket liste
     */
    public DobbeltLenketListe(T[] a) {
        // kaster en NullPoinerException om tabellen a er null
        Objects.requireNonNull(a, "Tabellen a er null");

        // lager ikke en dobbelt lenkede liste om a har ingen elementer
        if (a.length == 0) { return; }

        // passer på at den første noden er ikke null og at den første noden kodes annerledes enn de andre
        int nonNullNode = 0;

        // lager en midlertidig node
        Node<T> p;

        // itereres én gang for hver verdi i tabellen a
        for (T i : a) {
            // ignorerer null verdier
            if (i == null) { continue; }

            // lager den første noden
            if (nonNullNode == 0) {
                // lager en ny node med verdien verdi der både neste- og forrige-pekerne er null
                // slik at this.hode.forrige = null og this.hale.neste = null
                p = new Node<>(i);

                // halen og hoden lenkes til den nye noden
                this.hode = p;
                this.hale = p;

                // øker antall og endringer
                this.antall++;
                this.endringer++;

                // de andre ikkenull nodene vil nå kodes anerledes
                nonNullNode++;

                // passer på at neste iterasjon av for-loopen kjører
                continue;
            }

            // lager en ny node som peker på halen (forrige) og null (neste)
            // slik at this.hale.neste = null
            p = new Node<>(i, this.hale, null);

            // først skal den nåværende halen peke på den nye noden, så blir den nye noden til halen i listen
            this.hale.neste = p;
            this.hale = p;

            // øker antall og endringer
            this.antall++;
            this.endringer++;
        }

    }

    /**
     * fratilKontroll(int antall, int fra, int til) void
     *
     * Metoden sjekker om indeksene fra og til er innenfor grensene til den dobbelt lenkede listen
     * den kaster en feil om indeksene fra eller til er utenfor grensene til den dobbelt lenkede listen
     * eller om fra er større enn til
     *
     * @param antall skal fortelle metoden hvor lang den dobbelt lenkede listen er
     *               dette gjør at denne metoden vet hva den skal sammenligne indeksene med
     * @param fra er indeksen på det først elementet som
     * @param til er indeksen på det siste elementet som skal sjekkes
     */
    public static void fratilKontroll(int antall, int fra, int til) {
        // lager en int for switch blokken
        int sjekk = 0;

        // om fra er negativ
        if (fra < 0) { sjekk = 1; }

        // om til er utenfor tabellens grenser
        else if (til > antall) { sjekk = 2; }

        // om fra er større enn til
        else if (fra > til) { sjekk = 3; }

        // kaster feil basert på switch-verdiene
        // om sjekk fortsatt er 0, skjer ingenting
        switch (sjekk) {
            case 1 -> throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");
            case 2 -> throw new IndexOutOfBoundsException
                    ("til(" + til + ") er mer enn antall elementer i listen(" + antall + ")!");
            case 3 -> throw new IllegalArgumentException
                    ("fra(" + fra + ") er større enn til(" + til + ")!");
        }
    }

    /**
     * subliste(int fra, int til) Liste
     *
     * Returnerer en ny dobbelt lenket liste som starter fra og til indeksene som er parameterene
     *
     * @param fra setter indeksen til det første elementet i den nye dobbelt lenkede listen (inkludert)
     * @param til setter indeksen til det siste elementet i den nye dobbelt lenkede listen (ekskludert)
     * @return skal returnere en ny dobbelt lenket liste fra og med fra-indeksen, til til-indekswn
     */
    public Liste<T> subliste(int fra, int til) {
        // kontrollerer om indeksene er utenfor grensene til den dobbelt lenkede listen
        fratilKontroll(this.antall, fra, til);

        // lager den nye dobbelt lenkede listen
        Liste<T> subliste = new DobbeltLenketListe<>();

        // legger inn hver node mellom fra og til indeksene
        for (int i = fra; i < til; i++) { subliste.leggInn(this.finnNode(i).verdi); }

        // returnerer den nye dobbelt lenkede listen
        return subliste;
    }

    /**
     * Metoden finner og returnerer antall noder i den dobbelt lenkede listen
     *
     * @return returnerer antall elementer i den dobbelt lenkede listen
     */
    @Override
    public int antall() { return this.antall; }

    /**
     * Metoden ser om den dobbelt lenkede listen er tom (har ingen noder) eller ikke
     *
     * @return returnerer true om listen er tom og false om listen har en eller flere noder
     */
    @Override
    public boolean tom() { return this.antall == 0; }

    /**
     * Metoden legger inn en node bakerst i listen
     *
     * Metoden sjekker om den dobbelt lenkede listen er tom eller ikke før den legger elementet inn
     *
     * @param verdi setter verdien som skal legges inn i den dobbelt lenkede listen
     *              om verdi er null, kastes en NullPointerException
     * @return skal returnere true når elementet er blitt lagd inn i den dobbelt lenkede listen
     */
    @Override
    public boolean leggInn(T verdi) {
        // om verdi er null, så kastes en NullPointerException
        Objects.requireNonNull(verdi, "Verdien er null");

        // metoden sjekker om den dobbelt lenkede listen er tom eller ikke før den legger inn elementet
        if (this.antall == 0) {
            // lager en ny node som peker på null (forrige) og null (neste)
            // slik at this.hode.forrige = null og this.hale.neste = null
            Node<T> p = new Node<>(verdi);

            // halen og hoden lenkes til den nye noden
            this.hode = p;
            this.hale = p;
        }
        else {
            // lager en ny node som peker på halen (forrige) og null (neste)
            // slik at this.hale.neste = null
            Node<T> p = new Node<>(verdi, this.hale, null);

            // først skal den nåværende halen peke på den nye noden, så blir den nye noden til halen i listen
            this.hale.neste = p;
            this.hale = p;
        }

        // øker antall og endringer
        this.antall++;
        this.endringer++;

        // returnerer true når noden er lagt inn
        return true;
    }

    /**
     * Metoden legger inn en node i listen på den indeksen vi spesifiserer
     *
     * Metoden sjekker om listen er tom eller ikke, om noden som skal legges inn ligger fremst,
     * om noden ligger bakerst, og om noden skal legges inn mellom to andre noder
     *
     * @param indeks setter indeksen der den nye verdien skal ligge
     *               om indeks er utenfor grensene til listen, kastes en IndexOutOfBoundsException
     * @param verdi setter verdien som skal legges inn i den dobbelt lenkede listen
     *              om verdi er null, kastes en NullPointerException
     */
    @Override
    public void leggInn(int indeks, T verdi) {

        // kaster en NullPoinerException om verdien er null
        Objects.requireNonNull(verdi, "Verdien er null");

        // kontrollerer om indeksen er utonfor grensene til den dobbelt lenkede listen
        indeksKontroll(indeks, true);

        // lager en int for switch blokken
        int legginnsjekk = 0;

        // denne delen av koden legger inn noder på forskjellige måter basert på indeksen og lengden av listen

        /*
         kjører den første casen om man skal legge inn en node i den første posiosjon i listen
         og der listen bare har én element

         kjører den andre casen om man skal legge inn en node i den første posisjonen i listen

         kjører den tredje casen om man skal legge inn en node i den siste posisjonen i listen

         ellers kjøres den fjerde casen om man skal legge inn en node mellom to andre noder i listen
         */

        if (this.antall == 0) { legginnsjekk = 1; }
        else if (indeks == 0) { legginnsjekk = 2; }
        else if (indeks == this.antall) { legginnsjekk = 3; }

        switch (legginnsjekk) {
            case 1 -> {
                // lager en ny node med verdien verdi der både neste- og forrige-pekerne er null
                // slik at this.hode.forrige = null og this.hale.neste = null
                Node<T> p = new Node<>(verdi);

                // halen og hoden lenkes til den nye noden
                this.hode = p;
                this.hale = p;
            }
            case 2 -> {
                // lager en ny node som peker på null (forrige) og hoden (neste)
                // slik at this.hale.neste = null
                Node<T> p = new Node<>(verdi, null, this.hode);

                // først skal den nåværende hoden peke på den nye noden, så blir den nye noden til hoden i listen
                this.hode.forrige = p;
                this.hode = p;

            }
            case 3 -> {
                // lager en ny node som peker på halen (forrige) og null (neste)
                // slik at this.hale.neste = null
                Node<T> p = new Node<>(verdi, this.hale, null);

                // først skal den nåværende halen peke på den nye noden, så blir den nye noden til halen i listen
                this.hale.neste = p;
                this.hale = p;
            }
            default -> {
                // lager en ny node som peker på nodene som ligger bak indeksen (forrige) ) og på indeksen (neste)
                // slik at den noden som ligger på indeksen går én posisjon frem
                Node<T> p = new Node<>(verdi, this.finnNode(indeks - 1), this.finnNode(indeks));

                // de to nodene som ligger foran og bak den nye skal peke på den nye noden
                p.forrige.neste = p;
                p.neste.forrige = p;
            }
        }

        // øker antall og endringer
        this.antall++;
        this.endringer++;
    }

    /**
     * Metoden returnerer true om verdien som vi spesifiserer er i den dobbelt lenkede listen
     *
     * @param verdi setter verdien som metoden søker etter i listen
     * @return returnerer true om den finner verdien i listen og false om den gjør ikke det
     */
    @Override
    public boolean inneholder(T verdi) {
        // bare sjekker om en verdi er i listen om man søker etter en ikke-null verdi
        if (verdi != null) {
            // noden p settes som hode for å søke fra starten
            // siden man skal sjekke hver node i listen
            Node<T> p = this.hode;

            // sjekker en gang for hvert node i listen om verdien i noden er lik verdien man søker etter
            while (p != null) {
                if (p.verdi.equals(verdi)) { return true; }
                p = p.neste;
            }
        }

        // returnerer false om verdien man søker etter finnes ikke i listen
        return false;
    }

    /**
     * Metoden returnerer verdien til noden på indeksen som vi spesifiserer
     *
     * @param indeks setter indeksen som metoden skal finne
     * @return returnerer verdien til noden på indeksen indeks
     */
    @Override
    public T hent(int indeks) {
        // kontrollerer om indeksen er utonfor grensene til den dobbelt lenkede listen
        indeksKontroll(indeks, false);

        // returnerer verdien til noden på den valgte indeksen
        return this.finnNode(indeks).verdi;
    }

    /**
     * Metoden returnerer indeksen til verdien som man spesifiserer
     *
     * @param verdi setter verdien som metoden skal prøve å finne
     * @return returnerer indeksen til den første noden som har en verdi som er lik parameteren verdi
     *         om parameteren verdi finnes ikke i den dobbelt lenkede listen, eller verdi er null, returneres -1
     */
    @Override
    public int indeksTil(T verdi) {

        // om verdien man søker etter er null, stoppes metoden og -1 returneres
        if (verdi == null) { return -1; }

        // noden p settes som hode for å søke fra starten
        // siden man skal returnere den første gangen en verdi oppstår i koden
        Node<T> p = this.hode;

        // for-loopen søker etter verdien i hver node i listen, og returnerer indeksen til den om den finnes
        for (int i = 0; i < this.antall; i++) {
            if (p.verdi.equals(verdi)) { return i; }
            p = p.neste;
        }

        // returnerer -1 om verdien ikke finnes i listen
        return -1;
    }

    /**
     * Metoden oppdaterer verdien til noden som ligger på den valgte indeksen, og endrer dens verdi
     *
     * Metoden skal returnere den gamle verdien til den oppdaterte noden
     *
     * @param indeks setter indeksen på noden som vi vil skifte verdien til
     * @param nyverdi setter den nye verdien til noden
     * @return returnerer den gamle verdien til noden (verdien før den ble byttet ut)
     */
    @Override
    public T oppdater(int indeks, T nyverdi) {
        // kaster en NullPoinerException om verdien er null
        Objects.requireNonNull(nyverdi, "Den nye verdien er null");

        // kontrollerer om indeksen er utonfor grensene til den dobbelt lenkede listen
        indeksKontroll(indeks, false);

        // lag en midlertidig beholder
        Node<T> p = this.finnNode(indeks);

        // lager en kopi av nodens gamle verdi
        T gammelverdi = p.verdi;

        // endrer nodens verdi med den spesifisert i parameteren
        p.verdi = nyverdi;

        // øker endringer
        endringer++;

        // returner den oppdaterte noden sin gamle verdi
        return gammelverdi;
    }

    /**
     * Metoden fjerner en node i listen med den verdien vi spesifiserer
     *
     * Metoden sjekker om listen er tom eller ikke, om noden som skal fjernes ligger fremst,
     * om noden ligger bakerst, og om noden som skal fjernes ligger mellom to andre noder
     *
     * @param verdi setter verdien som skal fjernes fra den dobbelt lenkede listen
     * @return returnerer false om metoden finner og sletter den første noden med den verdien
     *         og returnerer false om en node med den verdien finnes ikke i listen eller om verdien er null
     */
    @Override
    public boolean fjern(T verdi) {

        // denne if-blokken kjører om verdi er ikke null
        if (verdi == null) { return false;}

        // setter node p til å være hode, slik at while-loopen kan starte fra starten og telle fremover
        Node<T> p = this.hode;

        // while-loopen prøver å finne indeksen av noden som skal fjernes
        while (p != null) {
            if (p.verdi.equals(verdi)) { break; }
            p = p.neste;
        }

        // om verdien finnes ikke i listen, returneres false
        if (p == null) { return false; }

        // lager en int for switch blokken
        int fjernsjekk = 0;

        // denne delen av koden sletter noder på forskjellige måter basert på indeksen og lengden av listen

        /*
         kjører den første casen om man skal slette den første noden i listen der listen bare har én element

         kjører den andre casen om man skal slette den første noden i listen

         kjører den tredje casen om man skal slette den siste noden i listen

         ellers kjøres default casen om man skal slette en node som ligger mellom to noder i listen
         */

        if (this.antall == 1) { fjernsjekk = 1; }
        else if (p == this.hode) { fjernsjekk = 2; }
        else if (p == this.hale) { fjernsjekk = 3; }

        // denne delen av koden sletter noder på forskjellige måter basert på verdien til fjernsjekk
        switch (fjernsjekk) {
            case 1 -> {
                // setter hoden og halen til null, slik at det er ikke noe som peker på p
                this.hode = null;
                this.hale = null;
            }
            case 2 -> {
                // hoden skal peke på noden bak p noden
                this.hode = p.neste;
                // hoden sin forrige-verdi skal ikke lenger peke på p
                this.hode.forrige = null;
            }
            case 3 -> {
                // halen skal peke på noden foran p noden
                this.hale = p.forrige;
                // halen sin neste-verdi skal ikke lenger peke på p noden
                this.hale.neste = null;
            }
            default -> {
                // noden foran p noden, skal peke på noden bak p noden
                p.forrige.neste = p.neste;
                // noden bak p noden, skal peke på noden foran p noden
                p.neste.forrige = p.forrige;
            }
        }

        // minker antall og øker endringer
        this.antall--;
        this.endringer++;

        return true;
    }

    /**
     * Metoden fjerner en node i listen på den indeksen vi spesifiserer
     *
     * Metoden sjekker om listen er tom eller ikke, om noden som skal fjernes ligger fremst,
     * om noden ligger bakerst, og om noden som skal fjernes ligger mellom to andre noder
     *
     * @param indeks setter indeksen til noden som skal fjernes
     *               om indeks er utenfor grensene til listen, kastes en IndexOutOfBoundsException
     * @return returnerer den gamle verdien til noden som ble fjernet
     */
    @Override
    public T fjern(int indeks) {
        // kontrollerer om indeksen er utonfor grensene til den dobbelt lenkede listen
        indeksKontroll(indeks, false);

        Node<T> p = this.finnNode(indeks);

        // lager en kopi av verdien til noden som skal fjernes
        T gammelverdi = p.verdi;

        // lager en int for switch blokken
        int fjernsjekk = 0;

        // denne delen av koden fjerner noder på forskjellige måter basert på indeksen og lengden av listen

        /*
         kjører den første casen om man skal slette den første noden i listen der listen bare har én element

         kjører den andre casen om man skal slette den første noden i listen

         kjører den tredje casen om man skal slette den siste noden i listen

         ellers kjøres den fjerde casen om man skal slette en node som ligger mellom to noder i listen
         */

        if (this.antall == 1) { fjernsjekk = 1; }
        else if (indeks == 0) { fjernsjekk = 2; }
        else if (indeks == this.antall - 1) { fjernsjekk = 3; }

        // denne delen av koden sletter noder på forskjellige måter basert på verdien til fjernsjekk
        switch (fjernsjekk) {
            case 1 -> {
                // setter hoden og halen til null, slik at det er ikke noe som peker på p
                this.hode = null;
                this.hale = null;
            }
            case 2 -> {
                // hoden skal peke på noden bak p noden
                this.hode = p.neste;
                // hoden sin forrige-verdi skal ikke lenger peke på p
                this.hode.forrige = null;
            }
            case 3 -> {
                // halen skal peke på noden foran p noden
                this.hale = p.forrige;
                // halen sin neste-verdi skal ikke lenger peke på p noden
                this.hale.neste = null;
            }
            default -> {
                // noden foran p noden, skal peke på noden bak p noden
                p.forrige.neste = p.neste;
                // noden bak p noden, skal peke på noden foran p noden
                p.neste.forrige = p.forrige;
            }
        }

        // minker antall og øker endringer
        this.antall--;
        this.endringer++;

        // returnerer en kopi av verdien til noden som ble slettet
        return gammelverdi;
    }

    /**
     * Fjerner hvert node i den dobbelt lenkede listen
     *
     * Fjerner nodene slik at hode og hale peker på null og antallet blir lik 0
     */
    @Override
    public void nullstill() {
        // fjerner den første noden gjentatte ganger mens den dobbelt lenkede listen har elementer i den
        while (this.antall != 0) { this.fjern(0); }
    }

    /**
     * Returnerer en streng med verdiene i hvert node i den dobbelt lenkede listen
     *
     * Setter verdiene i listen inn i en StringBuilder
     *
     * f.eks. om man bruker toString() på en liste med tallene 1, 2 og 3
     *        returneres en streng på formen "[1, 2, 3]"
     *
     * @return returnerer en streng med verdiene i en StringBuilder
     */
    @Override
    public String toString() {
        // returnerer bare klammer om den dobbelt lenkede listen er tom
        if (this.antall == 0) { return "[]"; }

        // lager en StringJoiner som skal starte med "["
        StringBuilder build = new StringBuilder("[");

        // den midlertidige noden skal starte fra hoden
        Node<T> p = this.hode;

        // fortsetter å legge til noder mens noden sin neste-verdi er ikke null
        // vi gjør dette for at den siste elementet i strengen ikke legger til en komma på slutten
        while (p.neste != null) {
            // legger til streng-versjonen av p sin verdi og lager en mellomrom
            build.append(p.verdi.toString()).append(", ");

            // p blir til noden som ligger foran den
            p = p.neste;
        }

        // legger til den siste noden sin verdi og legger til en klamme for å slutte
        build.append(p.verdi.toString()).append("]");

        // gjør om StringJoineren til en String, og returnerer den
        return build.toString();
    }

    /**
     * Returnerer en streng med verdiene i hvert node i motsatt retning i den dobbelt lenkede listen
     *
     * Setter verdiene i listen inn i en StringJoiner,
     * men starter fra halen og går baklengs
     *
     * f.eks. om man bruker toString() på en liste med tallene 1, 2 og 3
     *        returneres en streng på formen "[3, 2, 1]"
     *
     * @return returnerer en streng med verdiene i StringJoineren
     */
    public String omvendtString() {
        // returnerer bare klammer om den dobbelt lenkede listen er tom
        if (this.antall == 0) { return "[]"; }

        // lager en StringJoiner som skal starte med "["
        StringBuilder build = new StringBuilder("[");

        // den midlertidige noden skal starte fra halen
        Node<T> p = this.hale;

        // fortsetter å legge til noder mens halen sin forrige-verdi er ikke null
        // vi gjør dette for at den siste elementet i strengen ikke legger til en komma på slutten
        while (p.forrige != null) {
            // legger til streng-versjonen av p sin verdi og lager en mellomrom
            build.append(p.verdi.toString()).append(", ");

            // p blir til noden som ligger foran den
            p = p.forrige;
        }

        // legger til den siste noden sin verdi og legger til en klamme for å slutte
        build.append(p.verdi.toString()).append("]");

        // gjør om StringJoineren til en String, og returnerer den
        return build.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


