
package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import static com.mygdx.game.screens.GameScreen.WORLD_WIDTH;
import static com.mygdx.game.screens.GameScreen.WORLD_HEIGHT;
import static com.mygdx.game.screens.GameScreen.startTime;

public class Wasp extends MovingObject {

    private static final String PICNAME = "wasp.png";
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    float globalCounter;
    Vector2 cycleLength = new Vector2(1300,900); // time (1000 = 1sec)
    Vector2 cycleScale = new Vector2(WORLD_WIDTH/3-this.getWidth(), WORLD_HEIGHT/4); // zone where wasp move from centerPos
    Vector2 centerPos;

    public Wasp(Vector2 position, Vector2 speed){
        super(PICNAME, position, WIDTH, HEIGHT, speed);
        centerPos = position;
        globalCounter = 0;
    }

    @Override
    public void accelerate(float dt) {
        //Vector2 folly = new Vector2(AngryBirdOLD.alea.nextFloat()-(getX()/AngryBirdOLD.WORLD_WIDTH), AngryBirdOLD.alea.nextFloat()-(getX()/AngryBirdOLD.WORLD_HEIGHT));
        //peed = speed.add(folly.scl(AGITATION));
    }

    @Override
    public void move(float deltaTime) {
        globalCounter+= deltaTime * 1000;

        float x  = (float) Math.sin(globalCounter/cycleLength.x)*cycleScale.x + centerPos.x + getWidth();
        float y = (float) Math.cos(globalCounter/cycleLength.y)*cycleScale.y + centerPos.y + getWidth();

        this.setPosition(x,y);
    }
}
