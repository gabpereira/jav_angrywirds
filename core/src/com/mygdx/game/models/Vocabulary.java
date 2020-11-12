package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.models.Word;

import java.util.ArrayList;

public class Vocabulary {
    public TranslateWord translateWord;
    String vocName;
    ArrayList<TranslateWord> words;

    public Vocabulary(String vocName){
        this.vocName = vocName;
        words = new ArrayList<>();
    }

    public ArrayList<TranslateWord> getTranslateWord() {
        Gdx.app.log("PIGGY", String.valueOf(words.size()));
        return words;
    }

    public TranslateWord findTranslateWord(TranslateWord wordToFind)
    {
        for (TranslateWord word : words)
            if (word == wordToFind)
                return word;
        return null;
    }

    public void  addTranslateWord(TranslateWord word){
        words.add(word);
    }
    public  TranslateWord pickRandomTranslateWord(){
        return  words.get(MathUtils.random(0,words.size()-1));
    }
    public TranslateWord pickUnfoundRandomTranslateWord(){
        TranslateWord word;
        do {
            word = words.get(MathUtils.random(0,words.size()-1));
        } while (word.found);
        return word;
    }
    public int countUnusedTranslateWord(){
        int i = 0;
        for (TranslateWord word : words) {
            if(word.found == false)
                i++;
        }
        return i;
    }

    public void unallocateTranslateWord() {
        for(TranslateWord w: words){
            w.allocated = false;
        }
    }
}
