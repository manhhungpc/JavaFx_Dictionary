package main.application;

import java.util.Map;
import java.util.TreeMap;

public class Search {
    private String keySearch;
    private Map<String, String> mapSearchWords = new TreeMap<>();
    private Map<String, String> mapWords;

    Search(String keySearch, Map<String, String> mapWords) {
        this.keySearch = keySearch;
        this.mapWords = mapWords;
        setMapSearchWords();
    }

    public String getKey() {
        return keySearch;
    }

    public void setKey(String key) {
        this.keySearch = key;
        setMapSearchWords();
    }

    public void setMapSearchWords() {
        for (String i : mapWords.keySet()) {
            if (i.startsWith(keySearch)) {
                mapSearchWords.put(i, mapWords.get(i));
            }
        }
    }

    public Map<String, String> getMapSearchWords() {
        return mapSearchWords;
    }

    @Override
    public String toString() {
        return "Search{" +
                "mapSearchWords=" + mapSearchWords +
                '}';
    }
}
