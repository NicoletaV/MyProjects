package tema;

public final class Line implements Shapes, Visitable {

    /*
     * Campurile reprezinta tipul formei(numele),
     * coordonatele punctului de start si de final,
     * culoarea liniei. Se va implementa makeShape,
     * intrucat clasa implementeaza interfata Shapes,
     * dar si metoda accept (implementand totodata si
     * interfata Visitable).
     */
    private String type;
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private String color;

    public Line() {

    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(final int xStart) {
        this.xStart = xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(final int yStart) {
        this.yStart = yStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(final int xEnd) {
        this.xEnd = xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(final int yEnd) {
        this.yEnd = yEnd;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    @Override
    public Shapes makeShape(final String shape) {

        String[] splited = shape.split("\\s+");
        setType(splited[ZERO]);
        setxStart(Integer.parseInt(splited[UNU]));
        setyStart(Integer.parseInt(splited[DOI]));
        setxEnd(Integer.parseInt(splited[TREI]));
        setyEnd(Integer.parseInt(splited[PATRU]));
        setColor(splited[CINCI] + splited[SASE]);

        return this;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);

    }

}
