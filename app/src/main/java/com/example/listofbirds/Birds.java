package com.example.listofbirds;

import android.content.Context;
import android.content.res.TypedArray;

public class Birds {
    private static Bird[] birds;

    static int currentIndex = 0;

    static Bird getCurrent() {
        return birds[Math.abs(currentIndex % birds.length)];
    }

    static void loadBirds(Context ctx) {
        if (birds != null) {
            return;
        }

        String[] birdsNames = ctx.getResources().getStringArray(R.array.birds_names);
        TypedArray birdsPictures = ctx.getResources().obtainTypedArray(R.array.birds_pictures);
        TypedArray birdsSounds = ctx.getResources().obtainTypedArray(R.array.birds_sounds);
        String[] birdsURLs = ctx.getResources().getStringArray(R.array.birds_urls);

        birds = new Bird[birdsNames.length];

        for (int i = 0; i < birds.length; i++) {
            birds[i] = new Bird(birdsNames[i], birdsURLs[i], birdsSounds.getResourceId(i, 0), birdsPictures.getResourceId(i, 0));
        }

        birdsSounds.recycle();
        birdsPictures.recycle();
    }
}
