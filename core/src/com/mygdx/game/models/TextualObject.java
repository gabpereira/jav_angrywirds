package com.mygdx.game.models;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public abstract class TextualObject extends PhysicalObject {
    protected Word word;
    protected BitmapFont font;
    protected GlyphLayout glyphLayout;
    private Vector2 fontOffset;

    public TextualObject(String picname, Vector2 position, int width, int height) {
        super( picname, position, width, height);
        setOrigin(position.x, position.y);
    }

    public TextualObject(String picname, Vector2 position, float width, float height, String text) {
        super(picname, position, width, height);

        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.WHITE);

        glyphLayout = new GlyphLayout(font, text);

        this.fontOffset = Vector2.Zero;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getWord() {
        return word;
    }
}
