package tema;

public final class Circle implements Shapes, Visitable {

    /*
     * Campurile reprezinta tipul formei(numele),
     * abscisa si ordonata centrului, lungimea razei,
     * culoarea marginii(Ext) si culoarea de interior.
     * Se va implementa makeShape, intrucat clasa
     * implementeaza interfata Shapes, dar si metoda
     * accept (implementand totodata si interfata
     * Visitable).
     */
    private String type;
    private int xCenter;
    private int yCenter;
    private int radius;
    private String colorExt;
    private String colorInt;

    public Circle() {
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(final int radius) {
        this.radius = radius;
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
        setRadius(Integer.parseInt(splited[TREI]));
        setColorExt(splited[PATRU] + splited[CINCI]);
        setColorInt(splited[SASE] + splited[SAPTE]);

        return this;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

}
