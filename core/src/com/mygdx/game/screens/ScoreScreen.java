package com.mygdx.game.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.AngryBird;
import com.mygdx.game.models.Button;
import com.mygdx.game.models.Label;
import com.mygdx.game.models.VocabularyBoard;
import com.mygdx.game.models.Word;

import java.util.ArrayList;

public class ScoreScreen extends ApplicationAdapter implements InputProcessor {
    private Texture background;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Vector3 initialPos = Vector3.Zero;

    private GameScreen gameScreen;
    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 900;

    private VocabularyBoard vocabularyBoard;

    private Button exitButton;

    public ScoreScreen(GameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
    }
    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        background = new Texture(Gdx.files.internal("background.jpg"));

        //exitButton = new Button("exit.png", "exit", new Vector2(WORLD_WIDTH / 2 + 75, WORLD_HEIGHT - 125), 100, 100);
        //exitButton.setX(WORLD_WIDTH - exitButton.getWidth() - 20);
        vocabularyBoard = new VocabularyBoard(gameScreen.vocabulary, 200, gameScreen.FLOOR_HEIGHT, exitButton.getY() - 20);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        vocabularyBoard.draw(batch);
        exitButton.draw(batch);
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
        initialPos = camera.unproject(new Vector3(screenX, screenY, 0));
        if(exitButton.getBoundingRectangle().contains(initialPos.x, initialPos.y))
            AngryBird.getInstance().pop();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 scroll = camera.unproject(new Vector3(screenX, screenY, 0)).sub(initialPos);

        vocabularyBoard.touchDragged(scroll.x, scroll.y);
        return true;
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
