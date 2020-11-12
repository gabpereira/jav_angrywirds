package com.mygdx.game.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AngryBird;
import com.mygdx.game.models.Button;

import java.util.HashMap;

public class WelcomeScreen extends ApplicationAdapter implements InputProcessor {

    private Texture background;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Button languageButton;
    private Button secondLanguageButton;
    private Button thirdLanguageButton;
    private Button languageButtonRight;
    private Button secondLanguageButtonRight;
    private Button thirdLanguageButtonRight;

    private BitmapFont title;
    private BitmapFont subTitle;
    private BitmapFont text;

    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 900;

    private static AngryBird instance;

    public enum LANGUAGE {
        fr,
        en,
        es
    }

    public static AngryBird getInstance() {
        return instance == null ? instance = new AngryBird() : instance;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        background = new Texture(Gdx.files.internal("background.jpg"));

        title = new BitmapFont();
        title.setColor(Color.ROYAL);
        title.getData().setScale(6);

        subTitle = new BitmapFont();
        subTitle.setColor(Color.BLUE);
        subTitle.getData().setScale(4);

        text = new BitmapFont();
        text.setColor(Color.WHITE);
        text.getData().setScale(3);

        //buttons
        // 2 étapes dabord la taille ensuite la position
        languageButton = new Button( new Vector2(WORLD_WIDTH / 2 + 75, WORLD_HEIGHT - 450),  "Français");
        languageButton.setX(WORLD_WIDTH / 5);

        secondLanguageButton = new Button( new Vector2(WORLD_WIDTH / 2 + 75, languageButton.getY() - 150),  "Français");
        secondLanguageButton.setX(languageButton.getX());

        thirdLanguageButton = new Button( new Vector2(WORLD_WIDTH / 2 + 75, secondLanguageButton.getY() - 150),  "Français");
        thirdLanguageButton.setX(secondLanguageButton.getX());

        languageButtonRight = new Button( new Vector2(WORLD_WIDTH / 2 + 75, WORLD_HEIGHT - 450),  "Français");
        languageButtonRight.setX(WORLD_WIDTH / 5);

        secondLanguageButtonRight = new Button( new Vector2(WORLD_WIDTH / 2 + 75, languageButtonRight.getY() - 150),  "Français");
        secondLanguageButtonRight.setX(languageButtonRight.getX());

        thirdLanguageButtonRight = new Button( new Vector2(WORLD_WIDTH / 2 + 75, secondLanguageButtonRight.getY() - 150),  "Français");
        thirdLanguageButtonRight.setX(secondLanguageButtonRight.getX());


        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        title.draw(batch, "Angry Wirds", WORLD_WIDTH / 3, WORLD_HEIGHT - 50);
        languageButton.draw(batch);
        secondLanguageButton.draw(batch);
        thirdLanguageButton.draw(batch);
        languageButtonRight.draw(batch);
        secondLanguageButtonRight.draw(batch);
        thirdLanguageButtonRight.draw(batch);
        text.draw(batch, "Français", WORLD_WIDTH / 4 + 10, WORLD_HEIGHT - 380);
        subTitle.draw(batch, "Exercice de (choisir) en (choisir)", WORLD_WIDTH / 4, WORLD_HEIGHT - 200);
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        AngryBird.getInstance().push(AngryBird.SCREENS_NAME.Game); // passer à la page game
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
