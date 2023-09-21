package assignment8.drawcircle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Michael Thompson
 */
public class Circle extends GeometricObject implements Drawable {
    /**
     * stores the circle radius
     **/
    private double radius;


    /**
     * creates Circle object
     *
     * @param x         cord of circle
     * @param y         cord of circle
     * @param radius    of circle
     * @param fillColor of circle
     */
    public Circle(double x, double y, double radius, Color fillColor) {
        super(x, y, fillColor); // call base class constructor
        this.radius = radius; // set radius to argument passed
    }

    /**
     * draws circle to screen
     *
     * @param gc reference to Graphics context
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor()); // set circle fill color
        gc.setStroke(getFillColor()); // set stroke color
        gc.fillOval(getX(), getY(), radius, radius); // draws the Circle
    }
}
