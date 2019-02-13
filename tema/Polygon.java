package tema;

public final class Polygon implements Shapes, Visitable {

    /*
     * Campurile reprezinta tipul formei(numele),
     * numarul de puncte din care e format poligonul,
     * un vector ce retine abscisele punctelor si unul
     * pentru ordonata y, culoarea marginii si a interiorului
     * formei. Se va implementa makeShape, intrucat clasa
     * implementeaza interfata Shapes, dar si metoda accept
     * (implementand totodata si interfata Visitable).
     */
    private String type;
    private int numberOfPoints;
    private int[] xPoints;
    private int[] yPoints;
    private String colorExt;
    private String colorInt;

    public Polygon() {

    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(final int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public void setxPoints(final int[] xPoints) {
        this.xPoints = xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public void setyPoints(final int[] yPoints) {
        this.yPoints = yPoints;
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

    /*
     * Salveaza in xPoints x-urile punctelor poligonului
     * si in yPoints, y-urile.
     */
    @Override
    public Shapes makeShape(final String shape) {

        String[] splited = shape.split("\\s+");
        setType(splited[ZERO]);
        setNumberOfPoints(Integer.parseInt(splited[UNU]));
        xPoints = new int[getNumberOfPoints()];
        yPoints = new int[getNumberOfPoints()];
        for (int pointIndex = 0; pointIndex < getNumberOfPoints(); pointIndex++) {
            xPoints[pointIndex] = Integer.parseInt(splited[DOI * pointIndex + DOI]);
            yPoints[pointIndex] = Integer.parseInt(splited[DOI * pointIndex + TREI]);
        }
        setColorExt(splited[getNumberOfPoints() * DOI + DOI]
                    + splited[getNumberOfPoints() * DOI + TREI]);
        setColorInt(splited[getNumberOfPoints() * DOI + PATRU]
                    + splited[getNumberOfPoints() * DOI + CINCI]);

        return this;
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

}
