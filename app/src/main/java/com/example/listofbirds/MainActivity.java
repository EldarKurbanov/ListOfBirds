package com.example.listofbirds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView birdPicture;
    TextView birdName;

    static int currentBird = 0;
    static MediaPlayer currentSound;
    Bird[] birds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birdPicture = findViewById(R.id.bird_image);
        birdName = findViewById(R.id.bird_name);

        birds = loadBirds();

        updateBird();
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentSound.stop();
    }

    Bird[] loadBirds() {
        if (birds != null) {
            return birds;
        }

        String[] birdsNames = getResources().getStringArray(R.array.birds_names);
        TypedArray birdsPictures = getResources().obtainTypedArray(R.array.birds_images);
        TypedArray birdsSounds = getResources().obtainTypedArray(R.array.birds_sounds);
        String[] birdsURLs = getResources().getStringArray(R.array.birds_urls);

        birds = new Bird[birdsNames.length];

        for (int i = 0; i < birds.length; i++) {
            birds[i] = new Bird(birdsNames[i], birdsURLs[i], birdsSounds.getResourceId(i, 0), birdsPictures.getResourceId(i, 0));
        }

        birdsSounds.recycle();
        birdsPictures.recycle();

        return birds;
    }

    public void onPictureClick(View view) {
        currentSound.start();
    }

    public void onPreviousClick(View view) {
        currentBird--;
        updateBird();
    }

    public void onNextClick(View view) {
        currentBird++;
        updateBird();
    }

    void updateBird() {
        if (currentSound != null)
            currentSound.stop();

        birdName.setText(birds[Math.abs(currentBird % birds.length)].name);
        currentSound = MediaPlayer.create(this, birds[Math.abs(currentBird % birds.length)].sound);
        birdPicture.setImageResource(birds[Math.abs(currentBird % birds.length)].image);
    }

    public void onHelpClick(View view) {
        String url = birds[Math.abs(currentBird % birds.length)].url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}