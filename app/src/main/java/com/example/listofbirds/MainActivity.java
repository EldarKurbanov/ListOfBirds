package com.example.listofbirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView birdPicture;
    TextView birdName;

    static MediaPlayer currentSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Birds.loadBirds(this);

        birdPicture = findViewById(R.id.pic);
        birdName = findViewById(R.id.name);

        updateBirdOnUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentSound.stop();
    }

    public void onPictureClick(View view) {
        currentSound.start();
    }

    public void onInfoClick(View view) {
        String url = Birds.getCurrent().getUrl();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onPreviousClick(View view) {
        Birds.currentIndex--;
        updateBirdOnUI();
    }

    public void onNextClick(View view) {
        Birds.currentIndex++;
        updateBirdOnUI();
    }

    void updateBirdOnUI() {
        if (currentSound != null)
            currentSound.stop();

        birdName.setText(Birds.getCurrent().getName());
        currentSound = MediaPlayer.create(this, Birds.getCurrent().getSound());
        birdPicture.setImageResource(Birds.getCurrent().getImage());
    }
}