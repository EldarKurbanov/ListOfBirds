package com.example.listofbirds;

public class Bird {
    private final String name, url;
    private final int sound, image;

    public Bird(String name, String url, int sound, int image) {
        this.name = name;
        this.url = url;
        this.sound = sound;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getSound() {
        return sound;
    }

    public int getImage() {
        return image;
    }
}
