package com.example.paimonstravern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.media.MediaPlayer;
import java.util.ArrayList;
import java.util.List;

public class travern extends AppCompatActivity {
    private ArrayList<String> selectedItems = new ArrayList<>();
    private ArrayList<Double> selectedPrices = new ArrayList<>();
    private  MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    private MediaPlayer mediaPlayer4;
    private MediaPlayer mediaPlayer5;
    private Switch switch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travern);

        final ImageView item1 = findViewById(R.id.item1);

        mediaPlayer2 = MediaPlayer.create(this, R.raw.button_music);
        mediaPlayer3 = MediaPlayer.create(this, R.raw.travern_button_items);
        mediaPlayer4 = MediaPlayer.create(this,R.raw.travern_instruction_open);
        mediaPlayer5 = MediaPlayer.create(this,R.raw.hutao_travern);
        switch2 = findViewById(R.id.switch2);

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mediaPlayer5.isPlaying()) {
                        mediaPlayer5.start();
                    }
                } else {
                    if (mediaPlayer5.isPlaying()) {
                        mediaPlayer5.pause();
                    }
                }
            }
        });
        switch2.setChecked(true);

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer3.isPlaying()){
                    mediaPlayer3.start();
                }
                addItem("Sticky Honey Roast", 1250.0);
                Toast.makeText(travern.this, "Sticky Honey Roast Added!", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView item2 = findViewById(R.id.item2);

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer3.isPlaying()){
                    mediaPlayer3.start();
                }
                addItem("Mushroom Pizza", 780.0);
                Toast.makeText(travern.this, "Mushroom Pizza Added!", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView item3 = findViewById(R.id.item3);

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer3.isPlaying()){
                    mediaPlayer3.start();
                }
                addItem("Venti\'s Bouyant Breeze", 2350.0);
                Toast.makeText(travern.this, "Venti\'s Bouyant Breeze Added!", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView item4 = findViewById(R.id.item4);

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer3.isPlaying()){
                    mediaPlayer3.start();
                }
                addItem("Apple Cider", 425.0);
                Toast.makeText(travern.this, "Apple Cider Added!", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView item5 = findViewById(R.id.item5);

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer3.isPlaying()){
                    mediaPlayer3.start();
                }
                addItem("Fruit of the Festival", 680.0);
                Toast.makeText(travern.this, "Fruit of the Festival Added!", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView item6 = findViewById(R.id.item6);

        item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer3.isPlaying()){
                    mediaPlayer3.start();
                }
                addItem("Flaming Red Bolognese", 1300.0);
                Toast.makeText(travern.this, "Flaming Red Bolognese Added!", Toast.LENGTH_SHORT).show();
            }
        });


        Button Order = findViewById(R.id.Order);
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItems.isEmpty()) {
                    // No items were added
                    displayToast("You didn't order anything");

                } else {
                    if (!mediaPlayer2.isPlaying()){
                        mediaPlayer2.start();
                    }
                    // Items were added
                    Intent intent = new Intent(travern.this, OrderItems.class);
                    intent.putStringArrayListExtra("selectedItems", selectedItems);

                    double[] pricesArray = new double[selectedPrices.size()];
                    for (int i = 0; i < selectedPrices.size(); i++) {
                        pricesArray[i] = selectedPrices.get(i);
                    }

                    intent.putExtra("selectedPrices", pricesArray);
                    startActivity(intent);

                    // Display "Thank you for your order" toast
                    displayToast("Thank you! Please Proceed!");
                }
            }
        });
    }

    private void addItem(String itemName, double itemPrice) {
        selectedItems.add(itemName);
        selectedPrices.add(itemPrice);
    }

    public void showInstruction(View view) {
        if (!mediaPlayer4.isPlaying()) {
            mediaPlayer4.start();
        }
        Intent intent = new Intent(travern.this, Instruction.class);
        startActivity(intent);
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (switch2.isChecked() && !mediaPlayer5.isPlaying()){
            mediaPlayer5.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer5.isPlaying()) {
            mediaPlayer5.pause();
        }
        if (mediaPlayer2.isPlaying()){
            mediaPlayer5.pause();
        }
        if (mediaPlayer3.isPlaying()){
            mediaPlayer5.pause();
        }
        if (mediaPlayer4.isPlaying()) {
            mediaPlayer5.pause();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer2.release();
        mediaPlayer3.release();
        mediaPlayer4.release();
        mediaPlayer5.release();
    }
}