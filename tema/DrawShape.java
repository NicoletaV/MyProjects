package tema;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public final class DrawShape {

    private DrawShape() {

    }

    /*
     * Metoda returneaza culoarea sub forma de int,
     * primind-o ca string.
     */
    public static int toARGB(final String color) {

        int result = 0, red, green, blue, alpha;
        final int trei = 3, cinci = 5, sapte = 7;
        final int unu = 1, doi = 2, opt = 8;
        final int aux1 = 16, aux2 = 24;

        red = Integer.parseInt(color.substring(unu, trei), aux1);
        green = Integer.parseInt(color.substring(trei, cinci), aux1);
        blue = Integer.parseInt(color.substring(cinci, sapte), aux1);
        alpha = Integer.parseInt(color.substring(sapte));
        result += alpha * (int) Math.pow(doi, aux2) + red * (int) Math.pow(doi, aux1)
                + green * (int) Math.pow(doi, opt) + blue;

        return result;
    }

    public static int sign(final int value) {

        if (value < 0) {
            return -1;
        } else if (value > 0) {
            return 1;
        } else {
            return 0;
        }

    }

    /*
     * Metoda ce verifica daca un pixel e in canvas, comparandu-l
     * cu dimensiunile acestuia.
     */
    public static boolean isOnCanvas(final int x, final int y, final int maxHeight,
                                    final int maxWidth) {

        if (x < maxWidth && y < maxHeight && x >= 0 && y >= 0) {
            return true;
        }

        return false;
    }

    /*
     * Algoritmul de desenare a unei linii, ce contine in plus
     * si cazul in care liniile sunt verticale sau orizontale.
     * Inainte de setarea unui pixel, se verifica mereu daca
     * acesta e in canvas.
     */
    public static void bresenhamAlgorithm(final BufferedImage bufferedImage, final int x1,
            final int y1, final int x2, final int y2, final String color, final int maxHeight,
            final int maxWidth) {

        if (x1 == x2) {
            if (y2 < y1) {
                for (int i = y1; i >= y2; i--) {
                    if (isOnCanvas(x1, i, maxHeight, maxWidth)) {
                        bufferedImage.setRGB(x1, i, toARGB(color));
                    }
                }
                return;
            } else {
                for (int i = y1; i <= y2; i++) {
                    if (isOnCanvas(x1, i, maxHeight, maxWidth)) {
                        bufferedImage.setRGB(x1, i, toARGB(color));
                    }
                }
                return;
            }
        } else if (y1 == y2) {
            if (x1 < x2) {
                for (int i = x1; i <= x2; i++) {
                    if (isOnCanvas(i, y1, maxHeight, maxWidth)) {
                        bufferedImage.setRGB(i, y1, toARGB(color));
                    }
                }
                return;
            } else {
                for (int i = x1; i >= x2; i--) {
                    if (isOnCanvas(i, y1, maxHeight, maxWidth)) {
                        bufferedImage.setRGB(i, y1, toARGB(color));
                    }
                }
                return;
            }
        }

        int x = x1;
        int y = y1;
        int deltax = Math.abs(x2 - x1);
        int deltaY = Math.abs(y2 - y1);
        int s1 = sign(x2 - x1);
        int s2 = sign(y2 - y1);

        boolean interchanged;
        if (deltaY > deltax) {
            int aux = deltax;
            deltax = deltaY;
            deltaY = aux;
            interchanged = true;
        } else {
            interchanged = false;
        }

        int error = 2 * deltaY - deltax;

        for (int i = 0; i <= deltax; i++) {
            if (isOnCanvas(x, y, maxHeight, maxWidth)) {
                bufferedImage.setRGB(x, y, toARGB(color));
            }

            while (error > 0) {
                if (interchanged) {
                    x = x + s1;
                } else {
                    y = y + s2;
                }

                error = error - 2 * deltax;
            }

            if (interchanged) {
                y = y + s2;
            } else {
                x = x + s1;
            }

            error = error + 2 * deltaY;
        }

    }

    /*
     * Metoda ce seteaza 8 pixeli, verificand anterior daca
     * se afla in canvas.
     */
    public static void drawCircle(final BufferedImage bufferedImage, final int x,
            final int y, final int p, final int q, final String color,
            final int maxHeight, final int maxWidth) {

        if (isOnCanvas(x + p, y + q, maxHeight, maxWidth)) {
            bufferedImage.setRGB(x + p, y + q, toARGB(color));
        }

        if (isOnCanvas(x - p, y + q, maxHeight, maxWidth)) {
            bufferedImage.setRGB(x - p, y + q, toARGB(color));
        }

        if (isOnCanvas(x + p, y - q, maxHeight, maxWidth)) {
            bufferedImage.setRGB(x + p, y - q, toARGB(color));
        }

        if (isOnCanvas(x - p, y - q, maxHeight, maxWidth)) {
            bufferedImage.setRGB(x - p, y - q, toARGB(color));
        }

        if (isOnCanvas(x + q, y + p, maxHeight, maxWidth)) {
            bufferedImage.setRGB(x + q, y + p, toARGB(color));
        }

        if (isOnCanvas(x - q, y + p, maxHeight, maxWidth)) {
            bufferedImage.setRGB(x - q, y + p, toARGB(color));
        }

        if (isOnCanvas(x + q, y - p, maxHeight, maxWidth)) {
            bufferedImage.setRGB(x + q, y - p, toARGB(color));
        }

        if (isOnCanvas(x - q, y - p, maxHeight, maxWidth)) {
            bufferedImage.setRGB(x - q, y - p, toARGB(color));
        }

    }

    /*
     * Metoda ce deseneaza marginea unui cerc cu algoritmul
     * prezentat. Apeleaza drawCircle pentru a seta cei 8 pixeli.
     */
    public static void bresenhamCircle(final BufferedImage bufferedImage,
            final int xCenter, final int yCenter, final int radius,
            final String color, final int maxHeight, final int maxWidth) {

        final int doi = 2, trei = 3, patru = 4;
        final int sase = 6, zece = 10;
        int x = xCenter;
        int y = yCenter;
        int r = radius;
        int p = 0;
        int q = r;
        int d = trei - doi * r;

        while (p <= q) {

            drawCircle(bufferedImage, x, y, p, q, color, maxHeight, maxWidth);
            p++;
            if (d > 0) {
                q--;
                d = d + patru * (p - q) + zece;
            } else {
                d = d + patru * p + sase;
            }
            drawCircle(bufferedImage, x, y, p, q, color, maxHeight, maxWidth);
        }

    }

    /*
     * Metoda primeste coordonatele centrului si pornind de
     * la acesta, coloreaza toti pixelii care nu sunt de aceeasi
     * culoare ca marginea figurii si nici de culoarea interioara,
     * cu care ar trebui colorati.
     */
    public static void floodFill(final BufferedImage bufferedImage,
            final int xG, final int yG, final String colorInt,
            final String colorExt, final int maxHeight, final int maxWidth) {

        /*
         * Daca centrul e de culoarea marginii figurii, se opreste.
         */
        if (bufferedImage.getRGB(xG, yG) == toARGB(colorExt)) {
            return;
        }

        /*
         * O coada retine abscisele, una ordonatele.
         */
        Queue<Integer> xQueue = new LinkedList<Integer>();
        Queue<Integer> yQueue = new LinkedList<Integer>();

        /*
         * Se seteaza pixelul din centru, daca e in canvas.
         */
        if (isOnCanvas(xG, yG, maxHeight, maxWidth)) {
            bufferedImage.setRGB(xG, yG, toARGB(colorInt));
        }

        /*
         * Se adauga centrul in coada.
         */
        xQueue.add(xG);
        yQueue.add(yG);

        while (!(xQueue.isEmpty()) && !(yQueue.isEmpty())) {

            /*
             * Se retin coordonatele aflate la inceputul cozilor,
             * apoi se sterg din cozi.
             */
            int xN = xQueue.peek();
            int yN = yQueue.peek();

            xQueue.poll();
            yQueue.poll();

            /*
             * Se iau cele 4 directii (vecinii pixelului curent) si
             * daca (sunt in canvas) nu sunt de culoarea in care ar
             * trebui sa fie(colorInt), dar nu sunt nici de culoarea
             * marginii(colorExt), se coloreaza. Apoi se adauga
             * coordonatele in cozi(simultan pentru x si y).
             */
            if (isOnCanvas(xN + 1, yN, maxHeight, maxWidth)) {
                if (bufferedImage.getRGB(xN + 1, yN) != toARGB(colorInt)
                        && bufferedImage.getRGB(xN + 1, yN) != toARGB(colorExt)) {

                    bufferedImage.setRGB(xN + 1, yN, toARGB(colorInt));
                    xQueue.add(xN + 1);
                    yQueue.add(yN);
                }
            }

            if (isOnCanvas(xN, yN - 1, maxHeight, maxWidth)) {
                if (bufferedImage.getRGB(xN, yN - 1) != toARGB(colorInt)
                        && bufferedImage.getRGB(xN, yN - 1) != toARGB(colorExt)) {

                    bufferedImage.setRGB(xN, yN - 1, toARGB(colorInt));
                    xQueue.add(xN);
                    yQueue.add(yN - 1);
                }
            }

            if (isOnCanvas(xN - 1, yN, maxHeight, maxWidth)) {
                if (bufferedImage.getRGB(xN - 1, yN) != toARGB(colorInt)
                        && bufferedImage.getRGB(xN - 1, yN) != toARGB(colorExt)) {

                    bufferedImage.setRGB(xN - 1, yN, toARGB(colorInt));
                    xQueue.add(xN - 1);
                    yQueue.add(yN);
                }
            }

            if (isOnCanvas(xN, yN + 1, maxHeight, maxWidth)) {
                if (bufferedImage.getRGB(xN, yN + 1) != toARGB(colorInt)
                        && bufferedImage.getRGB(xN, yN + 1) != toARGB(colorExt)) {

                    bufferedImage.setRGB(xN, yN + 1, toARGB(colorInt));
                    xQueue.add(xN);
                    yQueue.add(yN + 1);
                }
            }

        }

        return;

    }

}
