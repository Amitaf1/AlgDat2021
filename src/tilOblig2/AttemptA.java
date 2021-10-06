package tilOblig2;
////////////////// class DobbeltLenketListe //////////////////////////////

import tilOblig2.Liste;

import java.util.*;

public class AttemptA <T> implements Liste<T> {

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
    private int endringer;         // antall endringer i listen

    public AttemptA() {
        this.hode = null;
        this.hale = null;
        this.antall = 0;
        this.endringer = 0;
    }

    public AttemptA(T[] a) {
        Objects.requireNonNull(a, "Tabellen a er null");

        if (a.length == 0) { return; }

        // lag en midlertidig beholder
        Node<T> temp;

        // passer på at den første noden er ikke null og at den første noden kodes annerledes enn de andre
        int nonNullNode = 0;

        Node<T> nyNode;

        for (T i : a) {

            // ignorer null verdier
            if (i == null) { continue; }

            // lag den første noden
            if (nonNullNode == 0) {
                // lag en ny node med verdien a[i]
                nyNode = new Node<>(i);

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
            nyNode = new Node<>(i, temp, null);
            temp.neste = nyNode;
            this.hale = nyNode;
            this.antall++;
            this.endringer++;
        }

    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(this.antall, fra, til);

        Liste<T> subliste = new AttemptA<>();

        for (int i = fra; i < til; i++) { subliste.leggInn(this.finnNode(i).verdi); }

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
    public boolean tom() { return this.antall == 0; }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Verdien er null");

        if (this.antall == 0) {

            // lag en ny node med verdien verdi
            Node<T> nyNode = new Node<>(verdi);

            this.hode = nyNode;
            this.hale = nyNode;
            this.hode.forrige = null;
            this.hale.neste = null;

        }
        else {
            // lag en midlertidig beholder
            Node<T> temp;

            // finn den siste noden
            temp = this.hale;

            // lenker nodene sammen
            Node<T> nyNode = new Node<>(verdi, temp, null);
            temp.neste = nyNode;
            this.hale = nyNode;
        }

        this.antall++;
        this.endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {

        Objects.requireNonNull(verdi, "Verdien er null");

        indeksKontroll(indeks, true);

        if (this.antall == 0) {

            // lag en ny node med verdien verdi
            Node<T> nyNode = new Node<>(verdi);

            this.hode = nyNode;
            this.hale = nyNode;
            this.hode.forrige = null;
            this.hale.neste = null;

        }
        else if (indeks == 0) {
            // lag en midlertidig beholder
            Node<T> temp;

            // finn den siste noden
            temp = this.hode;

            // lenker nodene sammen
            Node<T> nyNode = new Node<>(verdi, null, temp);
            temp.forrige = nyNode;
            this.hode = nyNode;
        }
        else if (indeks == this.antall) {
            // lag en midlertidig beholder
            Node<T> temp;

            // finn den siste noden
            temp = this.hale;

            // lenker nodene sammen
            Node<T> nyNode = new Node<>(verdi, temp, null);
            temp.neste = nyNode;
            this.hale = nyNode;
        }
        else {
            // lag en midlertidig beholder
            Node<T> temp, temp2;

            // finn den siste noden
            temp = this.finnNode(indeks - 1);
            temp2 = this.finnNode(indeks);

            // lenker nodene sammen
            Node<T> nyNode = new Node<>(verdi, temp, temp2);
            temp.neste = nyNode;
            temp2.forrige = nyNode;
        }

        this.antall++;
        this.endringer++;

    }

    @Override
    public boolean inneholder(T verdi) {

        if (verdi != null) {
            for (int i = 0; i < this.antall; i++) {
                if (this.finnNode(i).verdi.equals(verdi)) { return true; }
            }
        }

        return false;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);

        return this.finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {

        if (verdi != null) {
            for (int i = 0; i < this.antall; i++) {
                if (this.finnNode(i).verdi.equals(verdi)) { return i; }
            }
        }

        return -1;
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
        if (verdi == null) {
            return false;
        }
        Node<T> nodeÅFjerne = hode;

        while(nodeÅFjerne != null){
            if(nodeÅFjerne.verdi.equals(verdi)){
                break;
            }
            nodeÅFjerne = nodeÅFjerne.neste;
        }
        if (nodeÅFjerne == null){
            return false;
        }else{
            if(antall == 1){
                hode = hale = null;
            }else if (nodeÅFjerne == hode){
                nodeÅFjerne.neste.forrige = null;
                hode = nodeÅFjerne.neste;
            }else if(nodeÅFjerne == hale){
                nodeÅFjerne.forrige.neste = null;
                hale = nodeÅFjerne.forrige;
            }else{
                nodeÅFjerne.neste.forrige = nodeÅFjerne.forrige;
                nodeÅFjerne.forrige.neste = nodeÅFjerne.neste;
            }
            antall --;
            endringer ++;
        }
        return true;

    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> current = finnNode(indeks);
        if(antall == 1){
            hode = hale = null;
        }else if(hode == current){
            current.neste.forrige = null;
            hode = current.neste;
            current.neste = null;

        }else if(current == hale){
            current.forrige.neste = null;
            hale = current.forrige;
            current.forrige = null;
        }else{
            Node<T> nodeFørCurrent = current.forrige;
            Node<T> nodeEtterCurrent = current.neste;
            nodeEtterCurrent.forrige = nodeFørCurrent;
            nodeFørCurrent.neste= nodeEtterCurrent;
            current.neste = null;
            current.forrige = null;
        }
        antall --;
        endringer ++;
        return current.verdi;
    }

    @Override
    public void nullstill() {
        while (this.antall != 0) { this.fjern(0); }
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

    private class AttemptAIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private AttemptAIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private AttemptAIterator(int indeks) {
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

    } // class AttemptAIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class AttemptA


