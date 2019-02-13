package tema;

public final class Diamond implements Shapes, Visitable {

    /*
     * Campurile reprezinta tipul formei(numele),
     * coordonatele centrului, lungimea diagonalelor
     * orizontale si veriticale, culoarea marginii
     * si a interiorului formei. Se va implementa
     * makeShape, intrucat clasa implementeaza interfata
     * Shapes, dar si metoda accept (implementand
     * totodata si interfata Visitable).
     */
    private String type;
    private int xCenter;
    private int yCenter;
    private int oDiagonal;
    private int vDiagonal;
    private String colorExt;
    private String colorInt;

    public Diamond() {
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public int getxCenter() {
        return xCenter;
    }

    public void setxCenter(final int xCenter) {
        this.xCenter = xCenter;
    }

    public int getyCenter() {
        return yCenter;
    }

    public void setyCenter(final int yCenter) {
        this.yCenter = yCenter;
    }

    public int getoDiagonal() {
        return oDiagonal;
    }

    public void setoDiagonal(final int oDiagonal) {
        this.oDiagonal = oDiagonal;
    }

    public int getvDiagonal() {
        return vDiagonal;
    }

    public void setvDiagonal(final int vDiagonal) {
        this.vDiagonal = vDiagonal;
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
        setxCenter(Integer.parseInt(splited[UNU]));
        setyCenter(Integer.parseInt(splited[DOI]));
        setoDiagonal(Integer.parseInt(splited[TREI]));
        setvDiagonal(Integer.parseInt(splited[PATRU]));
        setColorExt(splited[CINCI] + splited[SASE]);
        setColorInt(splited[SAPTE] + splited[OPT]);

        return this;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

}
