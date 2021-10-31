package tilOblig3;

import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, hoyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            hoyre = h;
            this.forelder = forelder;
        }

        // konstruktør
        private Node(T verdi, Node<T> forelder) { this(verdi, null, null, forelder); }

        @Override
        public String toString()
        { return "" + verdi; }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.hoyre;
            else return true;
        }

        return false;
    }

    public int antall() { return antall; }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() { return antall == 0; }

    /**
     * Metode som legger inn en node i det binære søketreet
     *
     * Metoder kjører på forskjellige måter om noden er tom eller ikke
     * Noden blir lagt inn på en måte som gjør treet sortert
     *
     * @param verdi spesifiserer verdien som skal bli lagt til
     * @return returnerer true når noden har blitt lagt til
     */
    public boolean leggInn(T verdi)    // skal ligge i class SBinTre
    {
        // kaster feil om verdien som skal legges til er null
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                               // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);   // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.hoyre;   // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<T>(verdi, q);               // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) {
            q.venstre = p;                       // venstre barn til q
            p.forelder = q;                      // forelder blir til null
        }
        else {
            q.hoyre = p;                         // høyre barn til q
            p.forelder = q;                      // forelder blir til null
        }

        antall++;                                // én verdi mer i treet
        endringer++;                             // søketreet har blitt endret på
        return true;                             // vellykket innlegging
    }

    /**
     * Metode som sletter den første noden med den spesifiserte verdien i det binære søketreet
     *
     * Metoden først finner noden som skal slette
     * Deretter skjer én av to tilfeller:
     * 1. Noden har to barn
     * 2. Noden har ett eller ingen barn
     *
     * @param verdi definerer verdien som skal bli slettet fra treet
     * @return returnerer true når noden har blitt slettet
     *         men false om verdien finnes ikke eller er null
     */
    public boolean fjern(T verdi) {
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;        // q skal være forelder til p

        while (p != null)                 // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.hoyre; }   // går til høyre
            else break;                                 // den søkte verdien ligger i p
        }
        if (p == null) return false;      // finner ikke verdi

        if (p.venstre != null && p.hoyre != null)  // Tilfelle 3)
        {
            Node<T> s = p, r = p.hoyre;   // finner neste i inorden
            while (r.venstre != null) {
                s = r;    // s er forelder til r
                r = r.venstre;
                r.forelder = s;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.hoyre;
            else s.hoyre = r.hoyre;
        }
        else // Tilfelle 1) og 2)
        {

            Node<T> b = p.venstre != null ? p.venstre : p.hoyre;

            if (p == rot) {
                rot = b;
                if (b != null) b.forelder = null;
            }
            else if (p == q.venstre) {
                q.venstre = b;
                if (b != null) b.forelder = q;
            }
            else {
                q.hoyre = b;
                if (b != null) b.forelder = q;
            }
        }

        antall--;      // det er nå én node mindre i treet
        endringer++;   // søketreet har blitt endret på
        return true;
    }

    /**
     * Metode som sletter alle forekomster av et verdi inni et binær søketre
     *
     * @param verdi definerer verdien som skal fjernes fra treet
     * @return returnerer antall noder som ble slettet av metoden
     */
    public int fjernAlle(T verdi) {
        // metoden kjører ikke videre om verdien som skal fjernes er null, eller om treet er tomt
        if (verdi == null) return 0;
        if (tom()) return 0;

        // finner antall forekomster av verdien som skal fjernes
        int antallverdi = antall(verdi);

        // for-løkka kjører én gang for hver gang et verdi forekommer i treet
        for (int i = 0; i < antallverdi; i++) fjern(verdi);

        // returnerer antall verdier som ble fjernet
        return antallverdi;
    }

    /**
     * Metode som skal finne antall forekomster av verdien verdi i det binære søketreet
     *
     * Metoden bruker en stack for å sjekke alle nodene
     *
     * @param verdi spesifiserer verdien den skal søke etter i treet
     * @return returnerer antall forekomster av verdien verdi i treet
     */
    public int antall(T verdi) {
        if (verdi == null) { return 0;}     // om verdi er null returneres null, siden null kan ikke være i søketreet

        ArrayDeque<Node<T>> stack = new ArrayDeque<Node<T>>();  // lager en stack som kan inneholde noder

        stack.addLast(rot);     // det første som legges til stacken er roten

        int antallverdi = 0;    // holder styr på hvor mange  noder med verdien verdi har blitt funnet
        int cmp = 0;            // hjelpevariabel for å sammenligne

        // kjører helt til alle noder har blitt sjekket
        while (!stack.isEmpty()) {

            // p blir til den siste noden i stacken, og så slettes noden fra stacken (p fortsatt er noden)
            Node<T> p = stack.removeLast();

            // om den måværende noden som sjekkes har et venstre-barn, så legges venstrebarnet til i stacken
            if (p.venstre != null) stack.addLast(p.venstre);

            // om den måværende noden som sjekkes har et høyre-barn, så legges høyrebarnet til i stacken
            if (p.hoyre != null) stack.addLast(p.hoyre);

            // bruker komparatoren for å sammenligne verdiene i stacken med verdi
            cmp = comp.compare(verdi, p.verdi);

            // om verdiene som sammenlignes er de samme, økes antallverdi
            if (cmp == 0)  antallverdi++;

        }

        // returnerer antall forekomster av verdien verdi i treet
        return antallverdi;
    }

    /**
     * Metode som fjerner hvert verdi i det binære søketreet
     */
    public void nullstill() {
        for (int i = 0; i < antall; i++) fjern(rot.verdi);
    }

    /**
     * Metode som returnerer den første noden i det binære søketreet i postorden
     *
     * @param p skal være roten, men defineres som parameter fordi metoden er private
     * @param <T> spesifiserer at noden kan inneholde alle datatyper
     * @return returnerer den første noden i treet i postorden
     */
    private static <T> Node<T> førstePostorden(Node<T> p) {

        // kjører helt først node er blitt funnet i postorden
        while (true) {
            if (p.venstre != null) p = p.venstre;   // går ned til venstrabarnet
            else if (p.hoyre != null) p = p.hoyre;  // ellers går ned til høyrebarnet
            else return p;                          // om noden p har da ikke noen barn er p den første i postorden
        }
    }

    /**
     * Metode som returnerer den neste noden i det binære søketreet i postorden
     *
     * @param p definerer noden som skal bli funnet den neste av i postorden
     * @param <T> spesifiserer at noden kan inneholde alle datatyper
     * @return returnerer den neste noden i treet i postorden
     */
    private static <T> Node<T> nestePostorden(Node<T> p) {
        // om forelderen til p er null (om p er rot) returner null, siden roten er sist i postorden
        if (p.forelder == null) return null;

        // om p er høyrebarnet til forelderen sin er forelderen den neste i postorden
        else if (p == p.forelder.hoyre) return p.forelder;

        // om p er venstrebarnet
        else if (p == p.forelder.venstre) {
            // om forelderen har ingen høyrebarn er forelderen den neste i postorden
            if (p.forelder.hoyre == null) return p.forelder;

            // om et høyrebarn da finnes må vi finne den første noden som i kommer i postorden med høyrebarnet som rot
            else return førstePostorden(p.forelder.hoyre);
        }

        // om p er den siste i postorden (for at java ikke kaster feil siden vi vet at da er p roten)
        return null;

    }

    /**
     * Metode som utfører en oppgave med hver node i det binære søketreet
     *
     * Søketreet blir traversert i postorden med et while-løkke
     *
     * @param oppgave definerer oppgaven som blir utført
     */
    public void postorden(Oppgave<? super T> oppgave) {

        if (tom()) return;                  // kjører ikke om søketreet er tomt

        Node<T> p = førstePostorden(rot);   // setter p som den første noden i postorden

        while (p != null)       // utfører oppgaven med p sin verdi til p er ute av treet
        {
            oppgave.utførOppgave(p.verdi);      // utfører oppgaven med å sin verdi
            p = nestePostorden(p);              // går til den neste noden i postorden
        }

    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    /**
     * Metode som utfører en oppgave med hver node i det binære søketreet
     *
     * Søketreet blir traversert i postorden med rekursjon
     *
     * @param p definerer noden som skal være utgangspunktet for traverseringen
     * @param oppgave definerer oppgaven som blir utført
     */
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        if (p == null || tom()) return;             // om p er ute av treet, eller om søketreet er tomt, stoppes metoden

        postordenRecursive(p.venstre, oppgave);     // går ned til venstrebarnet
        postordenRecursive(p.hoyre, oppgave);       // etter å ha gått til venstre, så sjekkes høyrebarnet
        oppgave.utførOppgave(p.verdi);              // oppgaven utføres med verdien til p
    }

    /**
     * Metode som returnerer en ArrayList med alle verdiene i det binære søketreet i nivåorden
     *
     * Metoden bruker en queue for å legge inn nodene i ArrayListen
     *
     * @return returnerer en ArrayList med alle verdiene i treet i nivåorden
     */
    public ArrayList<T> serialize() {
        ArrayList<T> liste = new ArrayList<>(); // lager listen som skal inneholde verdiene av alle nodene i søketreet

        ArrayDeque<Node<T>> queue = new ArrayDeque<Node<T>>();  // lager en stack som brukes med noder

        // det første som legges til stacken og listen er roten
        queue.addLast(rot);
        liste.add(rot.verdi);

        // kjører helt til alle noder har blitt sjekket
        while (!queue.isEmpty()) {

            // p blir til den siste noden i stacken, og så slettes noden fra stacken (p fortsatt er noden)
            Node<T> p = queue.removeFirst();

            // om den måværende noden som sjekkes har et venstre-barn, så legges venstrebarnet til i stacken og listen
            if (p.venstre != null) {
                queue.addLast(p.venstre);
                liste.add(p.venstre.verdi);
            }

            // om den måværende noden som sjekkes har et høyre-barn, så legges høyrebarnet til i stacken og listen
            if (p.hoyre != null) {
                queue.addLast(p.hoyre);
                liste.add(p.hoyre.verdi);
            }

        }

        return liste;       // returnerer ArrayListen
    }

    /**
     * Metode som returnerer et binært søketre i nivåorden med alle verdiene i et ArrayList
     *
     *
     * @param data spesifiserer ArrayListen som skal bli gjort om til et binært søketre
     * @param c brukes for at treet kan bruke comparator
     * @param <K> spesifiserer at noden kan inneholde alle datatyper
     * @return returnerer en SBinTre (binært søketre) med alle verdiene fra den definerte ArrayListen
     */
    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        SBinTre<K> tre = new SBinTre<K>(c);     // initialiserer søketreet

        for (K i : data) tre.leggInn(i);        // går gjennom og legger inn alle verdiene i ArrayListen data

        return tre;                             // returnerer det nye binære søketreet
    }


} // ObligSBinTre


/*
Oppgave 1:
I denne oppgaven lagde jeg en metode som lager et nytt node med et spesifisert verdi inn i søketreet.
Metoden sammenligner verdien som skal legges inn med de andre verdiene i søketreet
for å bestemme om noden som legges inn  skal være et høyrebarn eller et venstrebarn. Så legges noden inn i treet.

For å gjøre dette kopierte jeg koden programkode 5.2.3 a) og endret noen deler av den.
*/

/*
Oppgave 2:
I denne oppgaven lagde jeg en metode som sjekker hvor mange ganger et verdi kommer opp i søketree.
Metoden bruker en stack for å gå gjennom alle verdier av søketreet.
Comparator brukes da for å sammenligne verdiene med parameteren verdi.

Jeg fikk hjelp av videoen om dybde først-traversering og endret noen deler av koden for at metoden skulle fungere.
*/

/*
Oppgave 3:
I denne oppgave lagde jeg to metoder.
Den første sjekker hva den første noden i postorden ville ha vært om parameternoden var roten.
Den bruker en while(true)-løkke for å gå gjennom alle noder helt til den første i postorden finnes.

Den andre sjekker hvilket node som kommer etter parameternoden i postorden.
Den sjekker først om parametern er roten, så sjekkes det om parameteren er venstre eller høyrebarnet til forelderen sin.

For å gjøre dette så jeg på seksjon 5.1.7 og kodet etter hvilke krav som måtte oppfølges.
*/

/*
Oppgave 4:
I denne oppgaven lagde jeg to metoder.
Den første utfører en spesifisert oppgave som er kodet ved bruk av en while-løkke.
While-løkken fortsetter helt til oppgaven er utført med hver node sin verdi, og hjelpenoden p er ute av treet.

Den andre utfører en spesifisert oppgave som er kodet ved rekursjon.
Den rekursive kallen fortsetter til p er ute av treet og roten har blitt lagt til.

Hinten i oppgaven hjelp meg på veien til å kode den første metoden, og videoen om dybde først-traversering på den andre.
*/

/*
Oppgave 5:
I denne oppgaven lagde jeg to metoder.
Den første gjør det binære søketreet om til en ArrayList. Verdiene blir lagt til i nivåorden.
Den bruker en kø til å traversere treet i nivå orden.

Den første gjør ArrayListom om til et binær søketre. Verdiene blir lagt til i nivåorden.

Jeg fikk hjelp av videoen om bredde først-traversering for å lage den første metoden,
mens den andre metoden var mye lettere, og jeg kunne gjøre det selv.
*/

/*
Oppgave 6:
I denne oppgaven lagde jeg tre metoder.
Den første sletter den første noden i søketreet med den spesifiserte verdien.
Jeg kopierte programkode 5.2.8 d) og endret lit på koden for å få det til.

Den andre sletter alle noder i søketreet med den spesifiserte verdien. Jeg brukte en for-løkke for å slette alle nodene.

Den tredje sletter alle noden i søketreet. Jeg brukte en for-løøe for å slette alle nodene.
*/