package tema;

public interface Visitor {

    /*
     * Contine metoda visit, ce va fi implementata
     * pentru fiecare tip de forma, in care se va
     * desena propriu-zis figura respectiva.
     */
    void visit(Canvas canvas);

    void visit(Circle circle);

    void visit(Diamond diamond);

    void visit(Line line);

    void visit(Polygon polygon);

    void visit(Rectangle rectangle);

    void visit(Square square);

    void visit(Triangle triangle);
}
