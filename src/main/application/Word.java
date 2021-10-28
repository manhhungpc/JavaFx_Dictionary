package main.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Word {
    //info about english_word
    private final StringProperty english = new SimpleStringProperty(this, "english");

    public StringProperty englishWord() {
        return english ;
    }

    public final String getEnglishWord() {
        return englishWord().get();
    }

    public final void setEnglishWord(String english) {
        englishWord().set(english);
    }

    //info about pronounce_property
    private final StringProperty pronounce = new SimpleStringProperty(this, "pronounce");

    public StringProperty pronounceProperty() {
        return pronounce ;
    }

    public final String getPronounceProperty() {
        return pronounceProperty().get();
    }

    public final void setPronounceProperty(String pronounce) {
        pronounceProperty().set(pronounce);
    }

    //info about parts_property
    private final StringProperty meanings = new SimpleStringProperty(this, "meanings");

    public StringProperty meanings() {
        return meanings ;
    }

    public final String getMeanings() {
        return meanings().get();
    }

    public final void setMeanings(String parts) {
        meanings().set(parts);
    }

    //Words constrctor
    public Word() {};

    public Word(String english, String pronounce, String meanings){
        setEnglishWord(english);
        setPronounceProperty(pronounce);
        setMeanings(meanings);
    }

    @Override
    public String toString() {
        return "Word{" +
                ", pronounce=" + pronounce +
                ", parts=" + meanings +
                '}';
    }

    public boolean isEmpty() {
        return (!(Objects.equals(getEnglishWord(), "")
                || Objects.equals(getMeanings(), "")
                || Objects.equals(getPronounceProperty(), "")));
    }
}
