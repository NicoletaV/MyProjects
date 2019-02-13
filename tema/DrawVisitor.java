package tema;

import java.awt.image.BufferedImage;

public final class DrawVisitor implements Visitor {

    /*
     * Campurile sunt: imaginea pe care se deseneaza,
     * inaltimea maxima si latimea maxima, ce vor fi
     * updatate cu inaltimea si latimea canvasului.
     *
     * Va fi implementata metoda visit, ce deseneaza
     * propriu zis forma primita ca argument.
     */
    private BufferedImage bufferedImage;
    private int maxHeight;
    private int maxWidth;

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(final BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(final int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(final int maxWidth) {
        this.maxWidth = maxWidth;
    }

    /*
     * Metoda returneaza o coordonata a centrului de
     * greutate, facand media aritmetica a coordonatelor
     * primite sub forma de vector. Va fi apelata pentru
     * x si y, obtinand astfel coordonatele centrului formei.
     */
    public static int getG(final int[] coord, final int numberOfPoints) {

        int sum = 0;
        for (int i = 0; i < numberOfPoints; i++) {
            sum += coord[i];
        }

        return sum / numberOfPoints;

    }

    /*
     * Se seteaza maxHeight si maxWidth cu valorile acestora
     * din canvas si se initializeaza imaginea. Apoi se umple
     * canvasul, colorand efectiv toti pixelii matricei.
     */
    @Override
    public void visit(final Canvas canvas) {

        setMaxHeight(canvas.getHeight());
        setMaxWidth(canvas.getWidth());

        bufferedImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        for (int heightIndex = 0; heightIndex < canvas.getHeight(); heightIndex++) {
            for (int widthIndex = 0; widthIndex < canvas.getWidth(); widthIndex++) {
                bufferedImage.setRGB(widthIndex, heightIndex,
                        DrawShape.toARGB(canvas.getColor()));
            }
        }

    }

    /*
     * Se deseneaza conturul cercului cu algoritmul prezentat
     * si se coloreaza apeland floodFill cu coordonatele
     * centrului cercului.
     */
    @Override
    public void visit(final Circle circle) {

        DrawShape.bresenhamCircle(bufferedImage, circle.getxCenter(),
                circle.getyCenter(), circle.getRadius(),
                circle.getColorExt(), maxHeight, maxWidth);

        DrawShape.floodFill(bufferedImage, circle.getxCenter(),
                circle.getyCenter(), circle.getColorInt(),
                circle.getColorExt(), getMaxHeight(), getMaxWidth());

    }

    /*
     * Se deseneaza cele 4 laturi ale rombului in directia
     * sus-stanga-jos-dreapta-sus, apeland de 4 ori algoritmul
     * de desenare a unei linii. Parametrii sunt calculati in
     * functie de lungimile diagonalelor si coordonatele centrului.
     * Apoi se apeleaza floodFill si se coloreaza interiorul.
     */
    @Override
    public void visit(final Diamond diamond) {

        DrawShape.bresenhamAlgorithm(bufferedImage, diamond.getxCenter(),
                diamond.getyCenter() - diamond.getvDiagonal() / 2,
                diamond.getxCenter() - diamond.getoDiagonal() / 2,
                diamond.getyCenter(), diamond.getColorExt(),
                getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, diamond.getxCenter()
                - diamond.getoDiagonal() / 2, diamond.getyCenter(),
                diamond.getxCenter(), diamond.getyCenter()
                + diamond.getvDiagonal() / 2, diamond.getColorExt(),
                getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, diamond.getxCenter(),
                diamond.getyCenter() + diamond.getvDiagonal() / 2,
                diamond.getxCenter() + diamond.getoDiagonal() / 2,
                diamond.getyCenter(), diamond.getColorExt(),
                getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, diamond.getxCenter()
                + diamond.getoDiagonal() / 2, diamond.getyCenter(),
                diamond.getxCenter(), diamond.getyCenter()
                - diamond.getvDiagonal() / 2, diamond.getColorExt(),
                getMaxHeight(), getMaxWidth());

        DrawShape.floodFill(bufferedImage, diamond.getxCenter(),
                diamond.getyCenter(), diamond.getColorInt(),
                diamond.getColorExt(), getMaxHeight(), getMaxWidth());

    }

    /*
     * Se deseneaza linia apeland algoritmul prezentat.
     */
    @Override
    public void visit(final Line line) {

        DrawShape.bresenhamAlgorithm(bufferedImage, line.getxStart(),
                line.getyStart(), line.getxEnd(), line.getyEnd(),
                line.getColor(), getMaxHeight(), getMaxWidth());

    }

    /*
     * Se deseneaza primele n-1 linii in ordine, apeland
     * algoritmul de desenare de linie, apoi si ultima linie.
     * Se coloreaza cu floodFill, centrul de greutate
     * aflandu-se cu functia getG.
     */
    @Override
    public void visit(final Polygon polygon) {

        int i, j;

        for (i = 0, j = 1; i < polygon.getNumberOfPoints() - 1
                && j < polygon.getNumberOfPoints(); i++, j++) {

            DrawShape.bresenhamAlgorithm(bufferedImage, polygon.getxPoints()[i],
                    polygon.getyPoints()[i], polygon.getxPoints()[j],
                    polygon.getyPoints()[j], polygon.getColorExt(),
                    getMaxHeight(), getMaxWidth());
        }

        DrawShape.bresenhamAlgorithm(bufferedImage,
                polygon.getxPoints()[polygon.getNumberOfPoints() - 1],
                polygon.getyPoints()[polygon.getNumberOfPoints() - 1],
                polygon.getxPoints()[0], polygon.getyPoints()[0],
                polygon.getColorExt(), getMaxHeight(), getMaxWidth());

        DrawShape.floodFill(bufferedImage, getG(polygon.getxPoints(),
                polygon.getNumberOfPoints()), getG(polygon.getyPoints(),
                polygon.getNumberOfPoints()), polygon.getColorInt(),
                polygon.getColorExt(), getMaxHeight(), getMaxWidth());

    }

    /*
     * Se deseneaza pe rand, in ordine, cele 4 laturi,
     * pornind din stanga sus. Apoi se coloreaza dreptunghiul
     * parcurgand matricea de pixeli din care este acesta format,
     * verificand mereu inainte daca pixelul e in canvas.
     */
    @Override
    public void visit(final Rectangle rectangle) {

        DrawShape.bresenhamAlgorithm(bufferedImage, rectangle.getxLeft(),
                rectangle.getyLeft(), rectangle.getxLeft(), rectangle.getyLeft()
                + rectangle.getHeight() - 1, rectangle.getColorExt(),
                getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, rectangle.getxLeft(),
                rectangle.getyLeft() + rectangle.getHeight() - 1,
                rectangle.getxLeft() + rectangle.getLength() - 1,
                rectangle.getyLeft() + rectangle.getHeight() - 1,
                rectangle.getColorExt(), getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, rectangle.getxLeft()
                + rectangle.getLength() - 1, rectangle.getyLeft()
                + rectangle.getHeight() - 1, rectangle.getxLeft()
                + rectangle.getLength() - 1, rectangle.getyLeft(),
                rectangle.getColorExt(), getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, rectangle.getxLeft()
                + rectangle.getLength() - 1, rectangle.getyLeft(),
                rectangle.getxLeft(), rectangle.getyLeft(),
                rectangle.getColorExt(), getMaxHeight(), getMaxWidth());

        for (int wIndex = rectangle.getxLeft() + 1; wIndex < rectangle.getxLeft()
                + rectangle.getLength() - 1; wIndex++) {

            for (int hIndex = rectangle.getyLeft() + 1; hIndex < rectangle.getyLeft()
                    + rectangle.getHeight() - 1; hIndex++) {

                if (DrawShape.isOnCanvas(wIndex, hIndex, getMaxHeight(),
                            getMaxWidth())) {

                    bufferedImage.setRGB(wIndex, hIndex,
                            DrawShape.toARGB(rectangle.getColorInt()));
                }
            }
        }

    }

    /*
     * Se deseneaza cele 4 linii in ordine, pornind
     * din stanga sus si se coloreaza centrul parcurgand
     * matricea de pixeli a patratului.
     */
    @Override
    public void visit(final Square square) {

        DrawShape.bresenhamAlgorithm(bufferedImage, square.getxLeft(),
                square.getyLeft(), square.getxLeft(), square.getyLeft()
                + square.getLength() - 1, square.getColorExt(),
                getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, square.getxLeft(),
                square.getyLeft() + square.getLength() - 1, square.getxLeft()
                + square.getLength() - 1, square.getyLeft()
                + square.getLength() - 1, square.getColorExt(),
                getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, square.getxLeft()
                + square.getLength() - 1, square.getyLeft() + square.getLength()
                - 1, square.getxLeft() + square.getLength() - 1,
                square.getyLeft(), square.getColorExt(), getMaxHeight(),
                getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, square.getxLeft()
                + square.getLength() - 1, square.getyLeft(), square.getxLeft(),
                square.getyLeft(), square.getColorExt(), getMaxHeight(),
                getMaxWidth());

        for (int wIndex = square.getxLeft() + 1; wIndex < square.getxLeft()
                + square.getLength() - 1; wIndex++) {

            for (int hIndex = square.getyLeft() + 1; hIndex < square.getyLeft()
                    + square.getLength() - 1; hIndex++) {

                if (DrawShape.isOnCanvas(wIndex, hIndex, getMaxHeight(),
                            getMaxWidth())) {

                    bufferedImage.setRGB(wIndex, hIndex,
                            DrawShape.toARGB(square.getColorInt()));
                }
            }
        }

    }

    /*
     * Se deseneaza cele 3 linii si se apeleaza floodFill,
     * centrul de greutate aflandu-se facand media aritmetica
     * a coordonatelor varfurilor.
     */
    @Override
    public void visit(final Triangle triangle) {

        final int trei = 3;

        DrawShape.bresenhamAlgorithm(bufferedImage, triangle.getX1(),
                triangle.getY1(), triangle.getX2(), triangle.getY2(),
                triangle.getColorExt(), getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, triangle.getX2(),
                triangle.getY2(), triangle.getX3(), triangle.getY3(),
                triangle.getColorExt(), getMaxHeight(), getMaxWidth());

        DrawShape.bresenhamAlgorithm(bufferedImage, triangle.getX3(),
                triangle.getY3(), triangle.getX1(), triangle.getY1(),
                triangle.getColorExt(), getMaxHeight(), getMaxWidth());

        DrawShape.floodFill(bufferedImage, ((triangle.getX1() + triangle.getX2()
                + triangle.getX3()) / trei), ((triangle.getY1()
                + triangle.getY2() + triangle.getY3()) / trei),
                triangle.getColorInt(), triangle.getColorExt(),
                getMaxHeight(), getMaxWidth());

    }

}
