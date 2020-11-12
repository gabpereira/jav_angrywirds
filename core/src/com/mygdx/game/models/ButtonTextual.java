package com.mygdx.game.models;

import com.badlogic.gdx.math.Vector2;

public class ButtonTextual extends TextualObject{

    public static final int WIDTH = 350;
    public static final int HEIGHT = 298 / 3;
    private static final String SPRITE_NAME = "rubber.png";

    public ButtonTextual(Vector2 position, String text) {
        super(SPRITE_NAME, position, WIDTH, HEIGHT, text);
    }
}
