package com.fawazalrasyid.mlhbelajar.model;

public class App {

    String name, logo, img;
    String bg, txt;
    String banner, interstisial, reward;

    public App(String name, String logo, String img, String bg, String txt, String banner, String interstisial, String reward) {
        this.name = name;
        this.logo = logo;
        this.img = img;
        this.bg = bg;
        this.txt = txt;
        this.banner = banner;
        this.interstisial = interstisial;
        this.reward = reward;
    }

    public App() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getInterstisial() {
        return interstisial;
    }

    public void setInterstisial(String interstisial) {
        this.interstisial = interstisial;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
