package tema;

public final class Canvas implements Shapes, Visitable {

    /*
     * Campurile reprezinta tipul formei(numele),
     * inaltimea, grosimea si culoarea canvasului.
     * Se va implementa makeShape, intrucat clasa
     * implementeaza interfata Shapes, dar si metoda
     * accept (implementand totodata si interfata
     * Visitable).
     */
    private String type;
    private int height;
    private int width;
    private String color;

    private static Canvas instance = new Canvas();

    private Canvas() {

    }

    /*
     * Metoda getInstance returneaza instanta, conform
     * Singleton Pattern.
     */
    public static Canvas getInstance() {
        return instance;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    /*
     * Metoda sparge stringul primit, in functie de spatii
     * si seteaza campurile Canvasului cu aceste informatii
     * retinute in vectorul splited. Returneaza aceasta
     * instanta updatata.
     */
    @Override
    public Shapes makeShape(final String shape) {

        String[] splited = shape.split("\\s+");
        setType(splited[ZERO]);
        setHeight(Integer.parseInt(splited[UNU]));
        setWidth(Integer.parseInt(splited[DOI]));
        setColor(splited[TREI] + splited[PATRU]);

        return this;
    }

    /*
     * Contine metoda accept, fiind Visitable, ce accepta
     * un visitor care va apela metoda visit, aceasta
     * implementand propriu-zis desenarea fiecarei figuri.
     */
    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

}
