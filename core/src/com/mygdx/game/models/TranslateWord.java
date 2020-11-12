package com.mygdx.game.models;

import com.mygdx.game.screens.WelcomeScreen;

import java.util.HashMap;

public class TranslateWord {

    HashMap<WelcomeScreen.LANGUAGE, String> values;
    public boolean allocated; // pig as the word?
    public boolean found; // word already founded?

    public  TranslateWord()
    {
        values = new HashMap<>();
        allocated = false;
        found = false;
    }

    public void addTranslation (WelcomeScreen.LANGUAGE language, String value ){
        values.put(language, value);
    }

    public String getValues(WelcomeScreen.LANGUAGE language) {
        return values.get(language);
    }
}