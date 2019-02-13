/**
 * VINA NICOLETA, 325CD
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import tema.DrawVisitor;
import tema.Image;
import tema.Shapes;

public final class Main {

    private Main() {

    }

    public static void main(final String[] args) throws IOException {

        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);

        /*
         * Se citeste numarul total de forme si apoi pentru fiecare forma
         * este salvat string-ul corespunzator acesteia (intreaga linie
         * din input) in array-ul shapeArray.
         */
        int totalNumber = Integer.parseInt(scanner.nextLine());
        ArrayList<String> shapeArray = new ArrayList<String>(totalNumber);

        for (int shapeIndex = 0; shapeIndex < totalNumber; shapeIndex++) {
            String shape = scanner.nextLine();
            shapeArray.add(shape);
        }

        scanner.close();

        /*
         * Apelandu-se metoda makeShapes, se formeaza array-ul instanceArray,
         * ce contine instantele formelor, pe care se face apoi apel de
         * metoda accept, din cadrul patternului Visitor.
         */
        ArrayList<Shapes> instanceArray = Image.makeShapes(shapeArray);
        DrawVisitor visitor = new DrawVisitor();

        for (int instanceIndex = 0; instanceIndex < instanceArray.size(); instanceIndex++) {
            instanceArray.get(instanceIndex).accept(visitor);
        }

        /*
         * La final, se formeaza imaginea de output in format drawing.png.
         */
        ImageIO.write(visitor.getBufferedImage(), "png", new File("drawing.png"));

    }

}
