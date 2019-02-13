package tema;

public final class ShapeFactory {

    private ShapeFactory() {

    }

    /*
     * Metoda va intoarce o noua instanta a formei primite
     * (numele acesteia), respectand astfel Factory Pattern.
     * La Canvas se aplica Singleton Pattern, apelandu-se
     * getInstance, deoarece va exista un singur canvas.
     */
    public static Shapes getShape(final String shapeType) {

        if (shapeType.equals("CANVAS")) {
            return Canvas.getInstance();
        } else if (shapeType.equals("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equals("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equals("TRIANGLE")) {
            return new Triangle();
        } else if (shapeType.equals("DIAMOND")) {
            return new Diamond();
        } else if (shapeType.equals("POLYGON")) {
            return new Polygon();
        } else if (shapeType.equals("SQUARE")) {
            return new Square();
        } else if (shapeType.equals("LINE")) {
            return new Line();
        } else {
            return null;
        }
    }

}
