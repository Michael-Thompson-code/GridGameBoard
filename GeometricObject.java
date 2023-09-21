package assignment8.drawcircle;

import javafx.scene.paint.Color;

/**
 * @author Michael Thompson
 */
public abstract class GeometricObject {
    /**
     * stores the cords of the object
     **/
    private double x, y;
    /**
     * Stores the fill color of the object
     **/
    private Color fillColor;

    /**
     * Creates the object
     *
     * @param x         cord of the object
     * @param y         cords of the object
     * @param fillColor of the object
     */
    public GeometricObject(double x, double y, Color fillColor) {
        this.x = x; // setting the instance vars equal to arguments passed
        this.y = y;
        this.fillColor = fillColor;
    }

    /**
     * gets the x-cord of the object
     *
     * @return x cord
     */
    public double getX() {
        return x;
    }

    /**
     * gets the y cord of the object
     *
     * @return y cord
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the fill color of the object
     *
     * @return fill color
     */
    public Color getFillColor() {
        return fillColor;
    }
}

