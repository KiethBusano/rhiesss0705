package com.example.paimonstravern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.media.MediaPlayer;
public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private  MediaPlayer mediaPlayer2;
    private Switch switch1;

    EditText traveler_name;
    Button button_next;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        traveler_name = findViewById(R.id.traveler_name);
        button_next = findViewById(R.id.button_next);
        error = findViewById(R.id.error);

        mediaPlayer = MediaPlayer.create(this, R.raw.background_ost);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.button_music);
        switch1 = findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                } else {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }
                }
            }
        });
        switch1.setChecked(true);


        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = traveler_name.getText().toString().trim();
                if (name.isEmpty()) {
                    error.setText("Enter your name First");
                    Toast.makeText(MainActivity.this, "Nameless Traveler!", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            error.setText("");
                        }
                    }, 3000);
                }else{
                    if (!mediaPlayer2.isPlaying()){
                        mediaPlayer2.start();
                    }
                    Intent intent = new Intent(MainActivity.this,travern.class);
                    Toast.makeText(MainActivity.this, "Welcome Traveler " + name + "!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        if (switch1.isChecked() && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        if (mediaPlayer2.isPlaying()){
            mediaPlayer.pause();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer2.release();
    }
}