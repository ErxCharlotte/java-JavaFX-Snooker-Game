package PoolGame.Items;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** The pocket of a pool table */
public class Pocket implements Drawable {

    private double[] positionPocket = {0.0, 0.0};

    /** The radius of the pocket */
    public double radius;
    /** The colour of the pocket */
    protected Color colour = Color.BLACK;
    /** The JavaFX shape of the pocket */
    protected Circle shape;

    /**
     * Initialise the pool table pocket with the provided value
     * @param posX The x coordinate position of the pocket
     * @param posY The y coordinate position of the pocket
     */
    public Pocket(double posX, double posY, double radius) {
        this.shape = new Circle(posX, posY, radius, this.colour);
        this.positionPocket[0] = posX;
        this.positionPocket[1] = posY;
        this.radius = radius;
    }

    /**
     * Check if a point is in the pocket bounds
     * @param point A point to check.
     * @return True if the point is in the pocket bounds, false otherwise
     */
    public boolean isInPocket(Point2D point) {
        return this.shape.getBoundsInLocal().contains(point);
    }

    public Node getNode() {
        return this.shape;
    }

    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.shape);
    }

    public double getRadius() {
        return radius;
    }

    public double getXPos() {
        return this.shape.getCenterX();
    }

    public double getYPos() {
        return this.shape.getCenterY();
    }
}
