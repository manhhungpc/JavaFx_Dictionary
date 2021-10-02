package main.Command_line;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    public static List<Word> words = new ArrayList<>();

    public static List<Word> getWords() {
        return words;
    }

    public static void addNewWord(Word new_word) {
        words.add(new_word);
    }

}
