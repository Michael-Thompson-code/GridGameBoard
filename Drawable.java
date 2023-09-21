package assignment8.drawcircle;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
    /**
     * draws object to screen
     * @param gc ref to graphics context
     */
    public abstract void draw(GraphicsContext gc);
}
