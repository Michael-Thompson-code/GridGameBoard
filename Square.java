package assignment8.drawcircle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Michael Thompson
 */
public class Square extends GeometricObject {
    /**
     * stores the size of the square
     **/
    private double size;

    /**
     * creates square object when called by static method
     *
     * @param x         cord of square
     * @param y         cord of the square
     * @param fillColor of square
     * @param radius    is size of the square
     */
    private Square(double x, double y, Color fillColor, double radius) {
        super(x, y, fillColor); // calls base class constructor
        this.size = radius; // sets size to argument passed
    }

    /**
     * static method that calls object constructor
     *
     * @param x         cord of square
     * @param y         cord of the square
     * @param fillColor of square
     * @param radius    is size of the square
     * @return square constructor
     */
    public static Square create(double x, double y, Color fillColor, double radius) {
        return new Square(x, y, fillColor, radius); // creating square object
    }

    /**
     * draws the square to the screen
     *
     * @param gc reference to graphics context
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor()); // set fill color of square
        gc.fillRect(getX(), getY(), size * 2, size * 2); // draws square to screen
    }
}

