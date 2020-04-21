package com.fawazalrasyid.mlhbelajar.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class CardDetail {
    public String txt;
    public String img;
    public String url;

    public CardDetail() {
    }

    public CardDetail(String txt, String img, String url) {
        this.txt = txt;
        this.img = img;
        this.url = url;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("txt", txt);
        result.put("img", img);
        result.put("url", url);

        return result;
    }
}
