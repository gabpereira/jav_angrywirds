package com.mygdx.game.providers.tests;

import com.badlogic.gdx.Gdx;

import com.mygdx.game.models.TranslateWord;
import com.mygdx.game.screens.WelcomeScreen;

public class SemanticWordTest {

    private static TranslateWord sw = new TranslateWord();

    public static boolean addTranslationTest() {
        boolean res = true;
        try {
            sw.addTranslation(WelcomeScreen.LANGUAGE.en, "a chair");
            sw.addTranslation(WelcomeScreen.LANGUAGE.es, "una silla");
            sw.addTranslation(WelcomeScreen.LANGUAGE.fr, "une chaise");
            Gdx.app.log("TESTVP", "Add translation test success");
        } catch (Exception e) {
            Gdx.app.log("TESTVP", "Add translation test failed:" + e.getMessage());
            res= false;
        }

        try {
            sw.addTranslation(WelcomeScreen.LANGUAGE.en, "a chair");
            Gdx.app.log("TESTVP", "Add translation duplicate test failed: duplicate was accepted");
            res = false;
        } catch (Exception e) {
            Gdx.app.log("TESTVP", "Add translation duplicate test success");
        }
        return res;
    }

    public static boolean getValueTest() {
        boolean res = true;
        try {
            if (sw.getValues(WelcomeScreen.LANGUAGE.en).equals("a chair")) {
                Gdx.app.log("TESTVP", "Get translation test success");
            } else {
                Gdx.app.log("TESTVP", "Get translation test failed: bad value");
                res = false;
            }
        } catch (Exception e) {
            Gdx.app.log("TESTVP", "Get translation test failed: exception " + e.getMessage());
            return false;
        }

        try {
            sw.getValues(WelcomeScreen.LANGUAGE.fr);
            Gdx.app.log("TESTVP", "Get nonexistent translation test failed: no exception raised");
            res = false;
        } catch (Exception e) {
            Gdx.app.log("TESTVP", "Get nonexistent translation test success: " + e.getMessage());
        }
        return res;
    }


}