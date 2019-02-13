package tema;

public interface Shapes extends Visitable {

    int ZERO = 0, UNU = 1, DOI = 2, TREI = 3, PATRU = 4;
    int CINCI = 5, SASE = 6, SAPTE = 7, OPT = 8, NOUA = 9;
    int ZECE = 10;

    /*
     * Interfata contine metoda makeShape, ce returneaza
     * instanta cu campurile updatate, implementand astfel
     * Factory Pattern.
     */
    Shapes makeShape(String shape);

}
