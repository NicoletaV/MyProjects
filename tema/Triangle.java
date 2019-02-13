package tema;

public final class Triangle implements Shapes, Visitable {

    /*
     * Campurile reprezinta tipul formei(numele),
     * coordonatele celor 3 varfuri, culoarea marginii
     * si a interiorului formei. Se va implementa
     * makeShape, intrucat clasa implementeaza interfata
     * Shapes, dar si metoda accept (implementand
     * totodata si interfata Visitable).
     */
    private String type;
    private int x1, y1;
    private int x2, y2;
    private int x3, y3;
    private String colorExt;
    private String colorInt;

    public Triangle() {

    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(final int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(final int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(final int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(final int y2) {
        this.y2 = y2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(final int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(final int y3) {
        this.y3 = y3;
    }

    public String getColorExt() {
        return colorExt;
    }

    public void setColorExt(final String colorExt) {
        this.colorExt = colorExt;
    }

    public String getColorInt() {
        return colorInt;
    }

    public void setColorInt(final String colorInt) {
        this.colorInt = colorInt;
    }

    @Override
    public Shapes makeShape(final String shape) {

        String[] splited = shape.split("\\s+");
        setType(splited[ZERO]);
        setX1(Integer.parseInt(splited[UNU]));
        setY1(Integer.parseInt(splited[DOI]));
        setX2(Integer.parseInt(splited[TREI]));
        setY2(Integer.parseInt(splited[PATRU]));
        setX3(Integer.parseInt(splited[CINCI]));
        setY3(Integer.parseInt(splited[SASE]));
        setColorExt(splited[SAPTE] + splited[OPT]);
        setColorInt(splited[NOUA] + splited[ZECE]);

        return this;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

}
