package tema;

import java.util.ArrayList;

public final class Image {

    private Image() {

    }

    /*
     * Metoda primeste un array de stringuri reprezentand informatiile
     * pentru fiecare figura si in functie de tipul acesteia (numele),
     * getShape va intoarce instanta corespunzatoare. Apoi instanta se
     * va adauga in array-ul de returnat, dar cu campurile updatate cu
     * informatiile din input, cu ajutorul metodei makeShape.
     */
    public static ArrayList<Shapes> makeShapes(final ArrayList<String> shapeArray) {

        ArrayList<Shapes> instanceArray = new ArrayList<Shapes>();

        for (int shapeIndex = 0; shapeIndex < shapeArray.size(); shapeIndex++) {
            String aux = shapeArray.get(shapeIndex);
            String shapeType = aux.substring(0, aux.indexOf(" "));

            Shapes shape = ShapeFactory.getShape(shapeType);
            instanceArray.add(shape.makeShape(aux));
        }

        return instanceArray;
    }

}
