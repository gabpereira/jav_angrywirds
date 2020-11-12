package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.screens.EndScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.PauseScreen;
import com.mygdx.game.screens.ScoreScreen;
import com.mygdx.game.screens.WelcomeScreen;

import java.util.HashMap;
import java.util.Stack;

public class AngryBird extends ApplicationAdapter {

    private static AngryBird instance;
    private Stack<ApplicationAdapter> stackScreens;
    private HashMap<SCREENS_NAME, ApplicationAdapter> screens;

    public enum SCREENS_NAME {
        Welcome,
        Game,
        Pause,
        Score,
        End
    }

    //Méthode statique du Singleton qui vérifie l'instance et la crée
    public static AngryBird getInstance() {
        return instance == null ? instance = new AngryBird() : instance;
    }

    //constructeur en private
    public AngryBird() {
        screens = new HashMap<>(); // clé=enum valeur=instanciation
        stackScreens = new Stack<>(); // Stocker les screens

        screens.put(SCREENS_NAME.Welcome, new WelcomeScreen());
        screens.put(SCREENS_NAME.Game, new GameScreen());
        screens.put(SCREENS_NAME.Pause, new PauseScreen());
        screens.put(SCREENS_NAME.Score, new ScoreScreen((GameScreen) screens.get(SCREENS_NAME.Game)));
        screens.put(SCREENS_NAME.End, new EndScreen());
    }

    //méthode pour la stack
    public void push(SCREENS_NAME screen_name) {
        ApplicationAdapter screen = screens.get(screen_name);
        screen.create();
        stackScreens.push(screen);
        Gdx.input.setInputProcessor((InputProcessor) stackScreens.peek());
    }

    //sort le premier element du stack
    public void pop() {
        stackScreens.pop();
        Gdx.input.setInputProcessor((InputProcessor) stackScreens.peek());// donner les droits interagir avec l ecran
    }

    public void goToStart(){
        for (int i = stackScreens.size()-1; i > 0 ; i--) { // récupere le début de la stack
            pop();
        }
    }
    @Override
    public void create() {
        push(SCREENS_NAME.Welcome);
    }

    @Override
    public void render() {
        stackScreens.peek().render(); //il prend le premier element et fait le rendu
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
