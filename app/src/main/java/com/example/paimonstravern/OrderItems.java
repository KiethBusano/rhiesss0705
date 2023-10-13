package com.example.paimonstravern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button; // Add this import
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView; // Add this import
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.media.MediaPlayer;
import java.util.ArrayList;

public class OrderItems extends AppCompatActivity{
    private LinearLayout checklistLinearLayout;
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<Double> itemPrices = new ArrayList<>();
    private TextView showTotal;
    private ScrollView linearLayout;
    private Button cancelbutton;
    private Button receiptbutton;
    private MediaPlayer mediaPlayer4;

    private MediaPlayer mediaPlayer6;
    private  MediaPlayer mediaPlayer7;

    private MediaPlayer mediaPlayer8;

    private Switch switch3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items);

        checklistLinearLayout = findViewById(R.id.checklistLinearLayout);
        showTotal = findViewById(R.id.showtotal);
        linearLayout = findViewById(R.id.linearLayout);
        cancelbutton = findViewById(R.id.cancelbutton); // Initialize the cancel button
        receiptbutton = findViewById(R.id.receiptbutton);

        mediaPlayer4 = MediaPlayer.create(this,R.raw.travern_instruction_open);
        mediaPlayer6 = MediaPlayer.create(this, R.raw.travern_instruction_close);
        mediaPlayer7 = MediaPlayer.create(this, R.raw.finish_music_2);
        mediaPlayer8 = MediaPlayer.create(this, R.raw.finish_order_tap);
        switch3 = findViewById(R.id.switch3);

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mediaPlayer7.isPlaying()) {
                            mediaPlayer7.start();
                    }
                } else {
                    if (mediaPlayer7.isPlaying()) {
                        mediaPlayer7.pause();
                    }
                }
            }
        });
        switch3.setChecked(true);


        receiptbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer8.isPlaying()) {
                    mediaPlayer8.start();
                }
                // Show a toast message
                Toast.makeText(OrderItems.this, "Thank you on Using Paimon\'s Travern! Please Comeback again!", Toast.LENGTH_SHORT).show();

                // Clear the item lists
                itemNames.clear();
                itemPrices.clear();
                checklistLinearLayout.removeAllViews();
                updateTotalPrice();

                // Return to the MainActivity (or replace it with the actual main activity class)
                Intent intent = new Intent(OrderItems.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Retrieve the selected items and their prices from the intent extras
        ArrayList<String> selectedItems = getIntent().getStringArrayListExtra("selectedItems");
        double[] selectedPrices = getIntent().getDoubleArrayExtra("selectedPrices");

        if (selectedItems != null && selectedPrices != null) {
            // Populate itemNames and itemPrices with the selected data
            for (int i = 0; i < selectedItems.size(); i++) {
                itemNames.add(selectedItems.get(i));
                itemPrices.add(selectedPrices[i]);
                addItemToLinearLayout(selectedItems.get(i), selectedPrices[i]);
            }

            // Update the total price
            updateTotalPrice();
        } else {
            // Check if the cart is empty
            if (itemNames.isEmpty()) {
                Toast.makeText(this, "The cart was empty! Order again.", Toast.LENGTH_SHORT).show();
                // Return to the previous activity (Travern.class)
                Intent intent = new Intent(OrderItems.this, travern.class);
                startActivity(intent);
                finish();
            }
        }

        // Set a click listener for the cancel button
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer6.isPlaying()) {
                    mediaPlayer6.start();
                }
                itemNames.clear();
                itemPrices.clear();
                checklistLinearLayout.removeAllViews();
                updateTotalPrice();
                Toast.makeText(OrderItems.this, "Order Canceled!", Toast.LENGTH_SHORT).show();
                // Return to the previous activity (Travern.class)
                Intent intent = new Intent(OrderItems.this, travern.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateTotalPrice() {
        double total = 0.0;
        for (Double price : itemPrices) {
            total += price;
        }
        showTotal.setText("Total: $" + total);
    }

    public void Instruction(View view) {
        if (!mediaPlayer4.isPlaying()) {
            mediaPlayer4.start();
        }
        Intent intent = new Intent(OrderItems.this, Instruction.class);
        startActivity(intent);
    }

    private void addItemToLinearLayout(String itemName, double itemPrice) {
        // Create a horizontal LinearLayout to hold the item text
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Create a TextView for the item name
        TextView itemTextView = new TextView(this);
        itemTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        itemTextView.setText(itemName + " - $" + itemPrice);
        itemTextView.setPadding(8, 8, 8, 8);

        // Add the item text to the itemLayout
        itemLayout.addView(itemTextView);

        // Add the itemLayout to the checklistLinearLayout
        checklistLinearLayout.addView(itemLayout);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if (switch3.isChecked() && !mediaPlayer7.isPlaying()){
            mediaPlayer7.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer7.isPlaying()) {
            mediaPlayer7.pause();
        }
        if (mediaPlayer4.isPlaying()){
            mediaPlayer7.pause();
        }
        if (mediaPlayer6.isPlaying()){
            mediaPlayer7.pause();
        }
        if (mediaPlayer8.isPlaying()){
            mediaPlayer7.pause();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer4.release();
        mediaPlayer6.release();
        mediaPlayer7.release();
        mediaPlayer8.release();
    }
}

