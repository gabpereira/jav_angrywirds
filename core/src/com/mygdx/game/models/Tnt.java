package com.mygdx.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.Scoreable;

public class Tnt extends PhysicalObject implements Scoreable {
    private static final int points = 10;
    private static final String PICNAME = "tnt.png";
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    public Tnt(Vector2 position) {
        super(PICNAME, position, WIDTH, HEIGHT);
    }

    @Override
    public int incrementScore() {
        return points;
    }

    @Override
    public int decrementScore() {
        return -points;
    }
}
