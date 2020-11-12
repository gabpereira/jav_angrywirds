package com.mygdx.game.models;

import com.badlogic.gdx.math.Vector2;

/*public class Button extends PhysicalObject {
    private String name;

    public Button(String picname, String name, Vector2 position, float width, float height) {
        super(picname, position, width, height);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}*/

public class Button extends TextualObject{

    public static final int WIDTH = 350;
    public static final int HEIGHT = 298 / 3;
    private static final String SPRITE_NAME = "rubber.png";

    public Button(Vector2 position, String text) {
        super(SPRITE_NAME, position, WIDTH, HEIGHT, text);
    }
}