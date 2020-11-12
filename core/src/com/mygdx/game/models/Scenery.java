package com.mygdx.game.models;
//TODO Passer en paramètres le nécessaire pour enlever la dépendance

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.customExceptions.OutOfSceneryException;

import java.util.ArrayList;

import static com.mygdx.game.screens.GameScreen.BIRD_START;
import static com.mygdx.game.screens.GameScreen.WORLD_WIDTH;
import static com.mygdx.game.screens.GameScreen.FLOOR_HEIGHT;
import static com.mygdx.game.screens.GameScreen.WORLD_HEIGHT;
import static com.mygdx.game.screens.GameScreen.rand;

public final class Scenery {

    public static final int X_MIN = 400;
    public static final int X_MAX = WORLD_WIDTH;
    public static final int Y_MIN = FLOOR_HEIGHT;
    public static final int Y_MAX = WORLD_HEIGHT;

    public static final int BLOCK_SIZE = 80;

    public Bird bird;
    public Wasp wasp;
    public Panel panel;

    private Vocabulary vocabulary;

    public RubberBand rubberBandBack;
    public RubberBand rubberBandFront;
    private Texture slingshotBack;
    private Texture slingshotFront;
    private Word wordToFind;

    private ArrayList<PhysicalObject> scene;
    private ArrayList<Word> words = new ArrayList<Word>();

    public Scenery(Vocabulary vocabulary) {

        this.scene = new ArrayList<PhysicalObject>();
        this.vocabulary = vocabulary;

        bird = new Bird(BIRD_START);
        bird.setFrozen(true);
        wasp = new Wasp(new Vector2(WORLD_WIDTH / 2, WORLD_HEIGHT / 2), new Vector2(20, 50));
        //wordToFind = vocabulary.pickUnfoundRandomTranslateWord();// recupere un des mots du voc sur l 'un des cochons
        panel = new Panel(new Vector2(20, Y_MAX), wordToFind);

        generateFloor();
        generateTNT(7);
        generatePigs(5);

        slingshotBack = new Texture(Gdx.files.internal("slingshot1.png"));
        slingshotFront = new Texture(Gdx.files.internal("slingshot2.png"));
        rubberBandBack = new RubberBand(bird.getPosition().x + bird.getWidth() - 10, bird.getPosition().y + bird.getHeight() / 2);
        rubberBandFront = new RubberBand(bird.getPosition().x + bird.getWidth() / 2 - 10, bird.getPosition().y + bird.getHeight() / 2);

    }

    /**
     * Add a new element on the scenery
     * @param object
     */
    //throws c est lancer une exeption
    public void add(PhysicalObject object) throws OutOfSceneryException {
        // si objet hors de la scene
        if (isOutOfScenery(object) || object.getX()+object.getWidth() > WORLD_WIDTH || object.getY() < Y_MIN || object.getY() + object.getHeight() > Y_MAX) {
            throw new OutOfSceneryException("Veuillez replacer votre block");
        }
        moveObjectToTopIfColliding(object);//cela regarde s'il est dans un objet ou s'il le toucher
        scene.add(object);
    }

    public void removeObject(PhysicalObject object) {
        scene.remove(object);
    }

    private void moveObjectToTopIfColliding(PhysicalObject object) {
        for (PhysicalObject p : scene) {
            if (p.overlaps(object)) {// si il touche un objet il va le décaler de 1
                object.setY(p.getY() + p.getHeight() + 1);
                moveObjectToTopIfColliding(object); //recursivité conditionnelle
            }
        }
    }

    public PhysicalObject whichObjectTouched(Vector2 position) {
        for (PhysicalObject object : scene)
            if (object.getBoundingRectangle().contains(position.x, position.y))
                return object;
        if (bird.getBoundingRectangle().contains(position.x, position.y))
            return bird;
        if (panel.getBoundingRectangle().contains(position.x, position.y))
            return panel;
        return null;
    }

    public PhysicalObject whichObjectTouched(float x, float y) {
        return whichObjectTouched(new Vector2(x, y));
    }

    public boolean isOutOfScenery(PhysicalObject object) {
        if (object.getX() > WORLD_WIDTH || object.getX() < 0 || object.getY() < FLOOR_HEIGHT)
            return true;
        return false;
    }

    private void generateFloor() {
        try {
            for (int i = 5; i < X_MAX / BLOCK_SIZE; i++) {
                add(new WoodenBlock(new Vector2(i * BLOCK_SIZE, Y_MIN)));
            }
        } catch (Exception e) {
            Gdx.app.log("Angry", "Floor completely generate");
        }
    }

    private void generateTNT(int quantity) {
        for (int i = 0; i < quantity; i++) {
            try {
                add(new Tnt(new Vector2(rand.nextInt(X_MAX - X_MIN) + X_MIN, FLOOR_HEIGHT)));
            } catch (OutOfSceneryException e) {
                Gdx.app.log("OutOfSceneryException", "TNT: " + e.getMessage());
                i--;
            }
        }
    }

    private void generatePigs(int quantity) {
        words.add(wordToFind); // ajoute mots du panneau
        Word word = null;
        for (int i = 0; i < quantity-1; i++) { // quantité -1 car un des cochons a déjà le mot hu panneau
            do {
                //word = vocabulary.pickRandomTranslateWord(); // choisir un mot aleatoire pour continuer le jeux en cas de moins de 5 mots
            }while (words.contains(word));
            words.add(word);
            word.allocated = true;
        }
        vocabulary.unallocateTranslateWord();

        for (int i = 0; i < quantity; i++) {
            try {
                Pig pig = new Pig(new Vector2(rand.nextInt(X_MAX - X_MIN) + X_MIN, FLOOR_HEIGHT));
                pig.setWord(words.get(i)); //get index from arraylist
                add(pig);
            } catch (OutOfSceneryException e) {
                Gdx.app.log("OutOfSceneryException", "Pig: " + e.getMessage());
                i--;
            }
        }
    }

    public PhysicalObject overlaps(PhysicalObject object) {
        for (PhysicalObject p : scene)
            if (p.overlaps(object)) {
                return p;
            }
        return null;
    }

    public ArrayList<Pig> getPigs() {
        ArrayList<Pig> pigs = new ArrayList<Pig>();
        for (PhysicalObject object : scene)
            if (object.getClass() == Pig.class) {
                pigs.add((Pig) object);
            }
        return pigs;
    }

    public void resetRubbers() {
        rubberBandBack.reset();
        rubberBandFront.reset();
    }


    public void update(float dt) {
        wasp.accelerate(dt);
        wasp.move(dt);

        if (!bird.isFrozen()) {
            bird.accelerate(dt);
            bird.move(dt);
        }
    }

    /**
     * Render the whole scenary
     *
     * @param batch
     */
    public void draw(Batch batch) {
        wasp.draw(batch);

        for (PhysicalObject p : scene) p.draw(batch);

        rubberBandBack.draw(batch);
        batch.draw(slingshotBack, 200, FLOOR_HEIGHT);
        bird.draw(batch);
        batch.draw(slingshotFront, 200, FLOOR_HEIGHT);
        rubberBandFront.draw(batch);
//        panel.draw(batch); not working on this draw... why? i can't answer 😀
    }

    public ArrayList<PhysicalObject> getScene() {
        return scene;
    }
}