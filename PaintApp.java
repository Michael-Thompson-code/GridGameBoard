package assignment8.drawcircle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;


/**
 * Application that allows users to manipulate pieces on a grid based game board
 *
 * @author Michael Thompson
 */
public class PaintApp extends Application {
    /**
     * Stores window size
     **/
    private final int WINDOW_SIZE = 450;
    /**
     * stores grid height
     **/
    private final int GRID_HEIGHT = 40;
    /**
     * stores grid width
     **/
    private final int GRID_WIDTH = 40;
    /**
     * Stores board size
     **/
    private final int BOARD_SIZE = 8;
    /**
     * stores mouse clicked cords
     **/
    double addX, addY;
    /**
     * holds selected piece cords
     **/
    double tempX = 0, tempY = 0;
    /**
     * stores the index of selected object
     **/
    int tempIndex;
    /**
     * stores color of selected object
     **/
    Color team;
    /**
     * stores team one circle object
     **/
    Circle cir;
    /**
     * store team two circle object
     **/
    Circle cir2;
    Button add = new Button("Add"); // creates add button
    Button remove = new Button("Remove"); // creates remove button
    Button clear = new Button("Reset"); // creates reset button
    TextField tf = new TextField(); // creates x cord text-field
    TextField tf2 = new TextField(); // creates y cord text-field
    Label x = new Label("X"); // creates x cord label
    Label y = new Label("Y"); // creates y cord label
    Label instuct = new Label(); // creates instructions label
    Canvas canvas = new Canvas(600, 600); // creates canvas
    GraphicsContext gc = canvas.getGraphicsContext2D(); // creates graphics object
    /**
     * stores game board pieces
     **/
    private ArrayList<GeometricObject> shapes = new ArrayList<>();

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * creates game board and starting pieces
     */
    public void board() {
        int x = GRID_WIDTH; // set x to constant
        for (int i = 0; i < BOARD_SIZE; i++) { // creates x axis grid lines
            gc.setStroke(Color.BLACK); // set lines to black
            gc.setLineWidth(3); // set width of grid lines
            gc.strokeLine(x, 0, x, GRID_WIDTH * 8); // draws lines
            x += GRID_WIDTH; // increments x axis cords
        }

        //rows
        int y = GRID_HEIGHT; // set y to constant
        for (int j = 0; j < BOARD_SIZE; j++) { // creates y axis grid lines
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(3);
            gc.strokeLine(0, y, GRID_HEIGHT * 8, y);
            y += GRID_HEIGHT;
        }

        //Column letters
        char letter = 'a'; // sets letter as a
        int colPos = 20; // stores position of column letter

        for (int i = 0; i < 8; i++) { // creates column letter labels
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Cosmic Sans MS", 12)); // changes letter font
            gc.fillText(String.valueOf(letter), colPos, 340); // draws letters to screen
            letter++; // increments to next letter in the alphabet
            colPos += 40; // increments the column position
        }

        // row numbers
        int rowNum = 1; // sets the row number
        int rowPos = 20; // stores the row position

        for (int i = 0; i < 8; i++) { // creates the row number labels
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Cosmic Sans MS", 12));
            gc.fillText(String.valueOf(rowNum), 340, rowPos);
            rowNum++;
            rowPos += 40;
        }
        // RED TEAM
        int cirPos = 10; // stores piece position
        for (int i = 0; i < 8; i++) { // creates red pieces
            cir = new Circle(cirPos, 290, 14, Color.RED); // created circle object
            shapes.add(cir); // add circle object to arraylist
            cir.draw(gc); // draws circle object
            cirPos += 40; // increments circle position
        }


        // GREEN TEAM
        int cirPos2 = 10;
        for (int i = 0; i < 8; i++) {
            cir2 = new Circle(cirPos2, 10, 14, Color.GREEN);
            shapes.add(cir2);
            cir2.draw(gc);
            cirPos2 += 40;
        }
    }

    /**
     * adds pieces to board
     *
     * @param e action event reference
     */
    private void addHandle(ActionEvent e) {
        try {
            addX = Double.parseDouble(tf.getText()); // gets the x cord from text-field
            addY = Double.parseDouble(tf2.getText()); // gets the y cord from text-field
            Circle add = new Circle(addX, addY, 14, team); // creates circle object
            add.draw(gc); // draws the circle to screen
            shapes.add(add); // adds the circle to arraylist
        } catch (NumberFormatException ex) { // catches exception if users enters character that isn't a number
            throw new RuntimeException(String.valueOf(new Alert(Alert.AlertType.WARNING,
                    "Invalid Input, numbers only").showAndWait())); // throws runtime exception and
        }                                                                     // shows alert window
    }

    /**
     * removes pieces from board
     *
     * @param e action event reference
     */
    private void removeHandle(ActionEvent e) {
        Circle move = new Circle(tempX, tempY, 20, Color.WHITE); // creates circle to draw over existing circle
        move.draw(gc); // draws circle object
        shapes.remove(tempIndex); // removes object from arraylist
    }

    /**
     * clears the screen and resets pieces
     *
     * @param e action event ref
     */
    private void clearHandle(ActionEvent e) {
        Square.create(0, 0, Color.WHITE, 1000).draw(gc); // creates square object
        shapes.removeAll(shapes); // clears arraylist
        board(); // calls board method to draw board to screen
    }

    /**
     * selects pieces and gets mouse clicked position
     *
     * @param me mouse event ref
     */
    private void pressedHandle(MouseEvent me) {
        addX = me.getX(); // holds mouse clicked x cord
        addY = me.getY(); // holds mouse clicked y cord
        for (GeometricObject obj : shapes) { // checks to see if object was selected
            if ((obj.getX() >= me.getX() - 10) && (obj.getX() <= me.getX() + 10)) { // checks object cords with mouse cords
                if ((obj.getY() >= me.getY() - 10) && (obj.getY() <= me.getY() + 10)) { // adds a 20 pixel buffer so
                    // pieces can be selected easier
                    if (obj instanceof Circle) {
                        Circle circle1 = new Circle(obj.getX(), obj.getY(), 14, Color.BLUE); // creates circle object
                        circle1.draw(gc); // draws circle object
                        tempX = obj.getX() - 5; // sets tempX var to object x cord
                        tempY = obj.getY() - 5; // sets tempY var to object y cord (minus 5 pixels to fully cover previous object)
                        tempIndex = shapes.indexOf(obj); // sets tempIndex to index of object in arraylist
                        team = obj.getFillColor(); // sets team to object color
                    }
                }
            }
        }
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane(); // creates pane for scene
        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE); // creates the scene
        scene.setFill(Color.WHITE); // sets background to white
        stage.setTitle("Table Top Board"); // set window title
        stage.setScene(scene); // sets stage scene

        board(); // calls method to draw the game board
        root.getChildren().addAll(canvas, remove, add, clear, tf, tf2, x, y, instuct); // adds nodes to root

        remove.relocate(370, 50); // sets POS of remove button
        add.relocate(370, 80); // sets POS of add button
        clear.relocate(370, 110); // sets POS of clear buttton
        tf.setPrefSize(50, 30); // sets size of text-field for x cord
        tf.relocate(370, 140); // sets POS of tf
        tf2.setPrefSize(50, 30); // set size of y cord text-field
        tf2.relocate(370, 170); // sets POS of tf2
        x.relocate(358, 140); // sets POS of x label
        y.relocate(358, 170); // sets POS of y label
        instuct.relocate(0, 350); // sets POS of instructions label
        instuct.setText("Hello! To play use the tools on the right-hand side of the window.\n To remove a piece from " +
                " the board simply select the piece using left click \n and press remove. To add a piece to the board" +
                " select the color you wish to add then \n enter the x and y cords in the text-fields, then hit the " +
                "Add button. \n Finally to reset the game board simply hit reset. Enjoy :) "); // sets instructions text

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressedHandle); // adds an event handle to canvas
        remove.setOnAction(this::removeHandle); // adds event handle to remove button
        add.setOnAction(this::addHandle); // adds event handel to add button
        clear.setOnAction(this::clearHandle); // adds event handle to clear button


        stage.show(); // display the stage
    }
}

