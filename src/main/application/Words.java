package main.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Words {
    //info about id_word
    private final StringProperty id_w = new SimpleStringProperty(this, "id_w");

    public StringProperty idWord() {
        return id_w ;
    }

    public final String getIdWord() {
        return idWord().get();
    }

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

    //info about vietnamese_word
    private final StringProperty vietnamese = new SimpleStringProperty(this, "vietnamese");

    public StringProperty vietnameseWord() {
        return vietnamese ;
    }

    public final String getVietnameseWord() {
        return vietnameseWord().get();
    }

    public final void setVietnameseWord(String vietnamese) {
        vietnameseWord().set(vietnamese);
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
    private final StringProperty parts = new SimpleStringProperty(this, "parts");

    public StringProperty partsProperty() {
        return parts ;
    }

    public final String getPartsProperty() {
        return partsProperty().get();
    }

    public final void setPartsProperty(String parts) {
        partsProperty().set(parts);
    }

    //Words constrctor
    public Words() {};

    public Words(String english, String vietnamese, String pronounce, String parts){
        setEnglishWord(english);
        setVietnameseWord(vietnamese);
        setPronounceProperty(pronounce);
        setPartsProperty(parts);
    }
}
