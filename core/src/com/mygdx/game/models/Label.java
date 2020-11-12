package com.mygdx.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;

import java.awt.Font;


public class Label {
    private String text;
    private BitmapFont font;
    private GlyphLayout glyphLayout;
    private float x;
    private float y;

    public Label(Color color) {
        font = new BitmapFont();
        font.setColor(color);
        font.getData().setScale(4);
        glyphLayout = new GlyphLayout();
    }

    public Label(String text, Color color){
        this(color);
        setText(text);
    }

    public void setText(String text){
        glyphLayout.setText(font, text);
    }

    public float getWidth(){
        return glyphLayout.width;
    }

    public float getHeight(){
        return glyphLayout.height;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2 getPosition(){
        return new Vector2(x, y);
    }

    public void setY(float y){
        this.y = y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void draw(Batch batch, float x, float y) {
        font.draw(batch, glyphLayout, x, y);
    }

    public void draw(Batch batch) {
        draw(batch, x, y);
    }
}
