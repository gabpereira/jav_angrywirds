package com.mygdx.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Panel extends TextualObject {
    private static final String PICNAME = "panel.png";
    public static final int WIDTH = 164;
    public static final int HEIGHT = 149;

    protected static GlyphLayout glyphLayout;
    protected BitmapFont font;

    public Panel(Vector2 position, Word word) {
        super(PICNAME, position, WIDTH, HEIGHT);
        this.word = word;
        setY(getY()-getHeight());

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        glyphLayout = new GlyphLayout(font, word.getEnglishWord());

        setSize(glyphLayout.width * 2, HEIGHT);
    }

    public void draw(SpriteBatch batch)
    {
        super.draw(batch);

        final float fontX = getX() + (getWidth() - glyphLayout.width) / 2;
        final float fontY = getY() +  glyphLayout.height*2f ;
        font.draw(batch, glyphLayout, fontX, fontY);
    }
}