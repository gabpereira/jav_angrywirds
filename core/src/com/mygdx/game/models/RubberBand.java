package com.mygdx.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class RubberBand extends Sprite {
    public static String PICNAME = "rubber.png";
    private Vector2 origin;
    private Vector2 destination;
    private static int THICKNESS = 40;
    public static final float power = 3f; //puissance de tir a l'oiseau

    public RubberBand(float x, float y){
        super(new Texture(PICNAME));
        destination = new Vector2(0, 0);
        origin = new Vector2(x,y);
        reset();
    }

    public void setDestination(float x, float y)
    {
        this.destination = new Vector2(x, y);
        calculatePosition();
    }

    public Vector2 rubberBand() {
        Vector2 power = destination.sub(origin);
        power.y = -power.y;

        return power;
    }

    public void reset() {
        destination = new Vector2(0, 0);
        setBounds(origin.x, origin.y - THICKNESS, -10, THICKNESS);
        setOrigin(origin.x, origin.y);
    }

    private void calculatePosition() {
        Vector2 vector = destination.sub(origin);
        setBounds(origin.x, origin.y, vector.len(), THICKNESS);
        setOrigin(0,0);
        setRotation(vector.angle());
    }
}
