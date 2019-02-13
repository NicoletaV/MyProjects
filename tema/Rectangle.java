package tema;

public final class Rectangle implements Shapes, Visitable {

    /*
     * Campurile reprezinta tipul formei(numele),
     * coordonatele coltului din stanga sus, inaltimea
     * si lungimea, culoarea marginii si a interiorului
     * formei. Se va implementa makeShape, intrucat clasa
     * implementeaza interfata Shapes, dar si metoda accept
     * (implementand totodata si interfata Visitable).
     */
    private String type;
    private int xLeft;
    private int yLeft;
    private int height;
    private int length;
    private String colorExt;
    private String colorInt;

    public Rectangle() {

    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public int getxLeft() {
        return xLeft;
    }

    public void setxLeft(final int xLeft) {
        this.xLeft = xLeft;
    }

    public int getyLeft() {
        return yLeft;
    }

    public void setyLeft(final int yLeft) {
        this.yLeft = yLeft;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(final int length) {
        this.length = length;
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
        setxLeft(Integer.parseInt(splited[UNU]));
        setyLeft(Integer.parseInt(splited[DOI]));
        setHeight(Integer.parseInt(splited[TREI]));
        setLength(Integer.parseInt(splited[PATRU]));
        setColorExt(splited[CINCI] + splited[SASE]);
        setColorInt(splited[SAPTE] + splited[OPT]);

        return this;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

}
