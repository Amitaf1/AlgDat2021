package tilOblig2;


import java.util.*;

public class DobbeltLenketListe <T> implements Liste<T> {

    /*
        LinkedList<Integer> a = new LinkedList<>();

        Integer[] b = {1, 12, 96, 5, 32};

        List<Integer> c = Arrays.asList(b);

        a.addAll(c);

        System.out.println(a);
        System.out.println(a.size());
     */

    public void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<>();


        System.out.println(liste.antall() + " " + liste.tom());
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

    /*
    static Node getNode() {
        return new Node(null);
    }
     */

    Node lagListe(T[] a, Node hode) {
        int n = a.length;

        // Declare newNode and temporary pointer
        Node temp;

        // Iterate the loop until array length
        for(int i = 0; i < n; i++) {
            // Create new node
            Node newNode = new Node(a[i]);

            // Assign the array data
            //newNode.verdi = a[i];

            // If it is first element
            // Put that node prev and next as start
            // as it is circular
            if (i == 0) {
                hode = newNode;
                newNode.forrige = hode;
                newNode.neste = hode;
            }
            else {
                // Find the last node
                temp = (hode).forrige;

                // Add the last node to make them
                // in circular fashion
                temp.neste = newNode;
                newNode.neste = hode;
                newNode.forrige = temp;
                temp = hode;
                temp.forrige = newNode;
            }
        }
        return hode;

    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        Node hode = null;
        Node hale = null;
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen a er null");

        hode = lagListe(a, hode);

        this.antall = a.length;
        this.endringer = 0;

    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if (antall == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
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


