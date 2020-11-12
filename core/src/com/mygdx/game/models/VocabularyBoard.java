package com.mygdx.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

public class VocabularyBoard {
    private float x;
    private float y;
    private float minY;
    private float maxY;
    private float actualScrollY;
    private float maxWidthEnglishWord;
    private float maxWidthFrenchWord;
    private Vocabulary vocabulary;
    private ArrayList<Label> englishWords;
    private ArrayList<Label> frenchWords;

    public VocabularyBoard(Vocabulary vocabulary, float x, float y, float maxY) {
        maxWidthFrenchWord = maxWidthEnglishWord = 0;
        this.vocabulary = vocabulary;
        englishWords = new ArrayList<>();
        frenchWords = new ArrayList<>();
        this.x = x;
        this.minY = this.y = y;
        this.maxY = actualScrollY = maxY;
        createLabels();
        placeLabels(actualScrollY);
    }

    private void createLabels() {
        float width;
        int i = 0;

        for (TranslateWord w : vocabulary.getTranslateWord()) {
            //englishWords.add(new Label(w.getEnglishWord(), Color.BLACK));
            //frenchWords.add(new Label(w.found ? w.getFrenchWord() : "", Color.BLACK));
//            frenchWords.add(new Label(w.getFrenchWord() , Color.BLACK));

            Label englishWord = englishWords.get(englishWords.size() - 1);
            width = englishWord.getWidth() + 30;
            maxWidthEnglishWord = maxWidthEnglishWord < width ? width : maxWidthEnglishWord;
        }
    }

    private void placeLabels(float scrollPosYFirstElem) {
        int i = englishWords.size();
        for (Label label : englishWords) {
            label.setPosition(x, scrollPosYFirstElem - i * label.getHeight() - i * 20);
            i--;
        }

        i = englishWords.size();;
        for (Label label : frenchWords) {
            label.setPosition(x + maxWidthEnglishWord, scrollPosYFirstElem - i * label.getHeight() - i * 20);
            i--;
        }
    }

    public void touchDragged(float x, float y) {
        //limit scroll top
        if ( englishWords.get(0).getPosition().y + y > minY )
            return;
        //limit scroll bottom
        if ( englishWords.get(englishWords.size() - 1).getPosition().y + y < maxY )
            return;

        actualScrollY+=y;
        placeLabels(actualScrollY);
    }

    public void draw(Batch batch) {
        for (Label label : englishWords) {
            label.draw(batch);
        }
        for (Label label : frenchWords) {
            label.draw(batch);
        }
    }
}
