package com.fawazalrasyid.mlhbelajar.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Card {
    public String txt;
    public String img;
    public String id;

    public Card() {
    }

    public Card(String txt, String img, String id) {
        this.txt = txt;
        this.img = img;
        this.id = id;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("txt", txt);
        result.put("img", img);
        result.put("id", id);

        return result;
    }
}
