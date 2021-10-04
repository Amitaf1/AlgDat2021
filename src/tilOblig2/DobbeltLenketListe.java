package tilOblig2;

import static java.util.Objects.requireNonNull;
import java.util.*;
import java.io.*;

public class DobbeltLenketListe <T> implements Liste<T> {

    public static void main(String[] args) {
        Character[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);

        System.out.println(liste.subliste(3,8)); // [D,E, F, G, H]

        System.out.println(liste.subliste(5,5)); // []

        System.out.println(liste.subliste(8,liste.antall())); // [I, J]

        // System.out.println(liste.subliste(0,11));
        // skal kaste unntak
        }

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }

    }

    private Node<T> finnNode(int indeks) {
        Node<T> temp;

        if (indeks < this.antall / 2) {
            temp = this.hode;

            for (int i = 0; i < indeks; i++) {
                temp = temp.neste;
            }
        }
        else {
            temp = this.hale;

            for (int i = this.antall - indeks - 1; i > 0; i--) {
                temp = temp.forrige;
            }
        }

        return temp;
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall ikke-null elementer i listen
    private int total;             // antall elementer i listen
    private int endringer;         // antall endringer i listen
    private boolean tom;           // om listen er tom eller ikke

    public DobbeltLenketListe() {
        this.hode = null;
        this.hale = null;
        this.antall = 0;
        this.endringer = 0;
        this.tom = true;
    }

    public DobbeltLenketListe(T[] a) {
        this.total = a.length;

        Objects.requireNonNull(a, "Tabellen a er null");

        if (this.total > 0) {
            // lag en midlertidig beholder
            Node<T> temp;

            // passer på at den første noden er ikke null og at den første noden kodes annerledes enn de andre
            int nonNullNode = 0;

            // Iterate the loop until array length
            for (T i : a) {

                // ignorer null verdier
                if (i == null) { continue; }

                // lag den første noden
                if (nonNullNode == 0) {
                    // lag en ny node med verdien a[i]
                    Node<T> nyNode = new Node<T>(i);

                    this.hode = nyNode;
                    this.hale = nyNode;
                    this.hode.forrige = null;
                    this.hale.neste = null;
                    this.antall++;
                    this.endringer++;
                    nonNullNode++;
                    continue;
                }

                // finn den siste noden
                temp = this.hale;

                // lenker nodene sammen
                Node<T> nyNode = new Node<T>(i, temp, null);
                temp.neste = nyNode;
                this.hale = nyNode;
                this.antall++;
                this.endringer++;
            }

        }

    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(this.antall, fra, til);

        Liste<T> subliste = new DobbeltLenketListe<>();

        for (int i = fra; i < til; i++) {
            subliste.leggInn(this.hent(i));
        }

        return subliste;
    }

    public static void fratilKontroll(int antall, int fra, int til) {
        int iter = 0;

        // fra er negativ
        if (fra < 0) { iter = 1; }

        // til er utenfor tabellen
        else if (til > antall) { iter = 2; }

        // fra er større enn til
        else if (fra > til) { iter = 3; }


        switch (iter) {
            case 1 -> throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");
            case 2 -> throw new IndexOutOfBoundsException
                    ("til(" + til + ") er mer enn antall elementer i listen(" + antall + ")");
            case 3 -> throw new IllegalArgumentException
                    ("fra(" + fra + ") er større enn til(" + til + ") - ulovlig intervall!");
        }
    }

    @Override
    public int antall() { return this.antall; }

    @Override
    public boolean tom() {
        if (this.antall != 0) { this.tom = false; }
        return this.tom;
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Verdien er null");

        if (this.antall == 0) {
            this.tom = false;

            // lag en ny node med verdien verdi
            Node<T> nyNode = new Node<T>(verdi);

            this.hode = nyNode;
            this.hale = nyNode;
        }
        else {
            // lag en midlertidig beholder
            Node<T> temp;

            // finn den siste noden
            temp = this.hale;

            // lenker nodene sammen
            Node<T> nyNode = new Node<T>(verdi, temp, null);
            temp.neste = nyNode;
            this.hale = nyNode;
        }

        this.antall++;
        this.total++;
        this.endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);

        return this.finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Den nye verdien er null");

        indeksKontroll(indeks, false);

        // lag en midlertidig beholder
        Node<T> gammelverdi;

        gammelverdi = this.finnNode(indeks);
        T gammelverdikopi = gammelverdi.verdi;

        if (this.antall != 0) {
            gammelverdi.verdi = nyverdi;

            endringer++;
        }

        return gammelverdikopi;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if (this.antall == 0) { return "[]"; }

        StringJoiner stringB = new StringJoiner(", ", "[", "]");

        Node<T> temp = this.hode;

        while (temp != null) {
            String tempNode = temp.verdi.toString();

            stringB.add(tempNode);

            temp = temp.neste;
        }

        return stringB.toString();
    }

    public String omvendtString() {
        if (this.antall == 0) { return "[]"; }

        StringJoiner stringB = new StringJoiner(", ", "[", "]");

        Node<T> temp = this.hale;

        while (temp != null) {
            String tempNode = temp.verdi.toString();

            stringB.add(tempNode);

            temp = temp.forrige;
        }

        return stringB.toString();
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
            /*
            if (this.hasNext() == true) {
                T next = this.neste;
                return next;
            }
             */

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


