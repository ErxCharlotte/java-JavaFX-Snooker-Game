package PoolGame.Config;

import PoolGame.Items.Pocket;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class PocketConfig  implements Configurable {
    private double radius;
    private PositionConfig position;

    public PocketConfig(Object obj) {
        this.parseJSON(obj);
    }

    public PocketConfig(double radius, PositionConfig positionConfig) {
        this.init(radius, positionConfig);
    }

    private void init(double radius, PositionConfig positionConfig) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius of pocket must be greater than 0");
        }
        this.radius = radius;
        this.position = positionConfig;
    }

    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        double radius = (double) json.get("radius");
        PositionConfig positionConfig = new PositionConfig(json.get("position"));
        this.init(radius, positionConfig);
        return null;
    }

    public double getRadius() {
        return radius;
    }

    public PositionConfig getPosition() {
        return position;
    }
}
