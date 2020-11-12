package com.mygdx.game.models;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AngryBird;
import com.mygdx.game.interfaces.Scoreable;
import com.mygdx.game.screens.GameScreen;

public class Pig extends TextualObject implements Scoreable {
    private static final int points = 10;
    private static final String PICNAME = "pig.png";
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    public Pig(Vector2 position) {
        super(PICNAME, position, WIDTH, HEIGHT);
    }

    public String sayWord() {
        return this.getWord().getFrenchWord();
    }

    @Override
    public int incrementScore() {
        return points;
    }

    @Override
    public int decrementScore() {
        return  -points;
    }
}
