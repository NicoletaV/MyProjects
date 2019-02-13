package tema;

public interface Visitable {

    /*
     * Interfata va contine metoda accept, deoarece
     * elementele de tip Visitable trebuie sa dea accept.
     */
    void accept(Visitor v);

}
