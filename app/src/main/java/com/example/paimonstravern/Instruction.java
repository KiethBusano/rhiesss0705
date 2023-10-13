package com.example.paimonstravern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.media.MediaPlayer;

public class Instruction extends AppCompatActivity {
    private MediaPlayer mediaPlayer6;
    private Switch switch4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        mediaPlayer6 = MediaPlayer.create(this,R.raw.hutao_travern);
        switch4 = findViewById(R.id.switch4);

        Button share1 = findViewById(R.id.share1);
        Button share2 = findViewById(R.id.share2);

        share1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent shareIntent1 = new Intent(Intent.ACTION_SEND);
                shareIntent1.setType("text/plain");

                String title = "More about Genshin Impact";
                String link = "https://genshin.hoyoverse.com/en/home";
                String message = title + "\n" + link + "\n";

                shareIntent1.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(shareIntent1, "References"));
            }
        });

        share2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent shareIntent2 = new Intent(Intent.ACTION_SEND);
                shareIntent2.setType("text/plain");

                String title = "Genshin Impact Foods";
                String link = "https://genshin-impact.fandom.com/wiki/Food";
                String message = title + "\n" + link + "\n";

                shareIntent2.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(shareIntent2, "References"));
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mediaPlayer6.isPlaying()) {
                        mediaPlayer6.start();
                    }
                } else {
                    if (mediaPlayer6.isPlaying()) {
                        mediaPlayer6.pause();
                    }
                }
            }
        });
        switch4.setChecked(true);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if (switch4.isChecked() && !mediaPlayer6.isPlaying()){
            mediaPlayer6.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer6.isPlaying()) {
            mediaPlayer6.pause();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer6.release();
    }
}