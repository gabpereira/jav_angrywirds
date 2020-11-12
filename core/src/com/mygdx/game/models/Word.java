package com.mygdx.game.models;

public class Word {
    private String frenchWord;
    private String englishWord;
    public boolean allocated; // pig as the word?
    public boolean found; // word already founded?

    public Word(String frenchWord, String englishWord){
        this.frenchWord = frenchWord;
        this.englishWord = englishWord;
        allocated = false;
        found = false;
    }

    public String getFrenchWord(){
        return frenchWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }
}
