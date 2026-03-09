package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // grab the textview and button from xml
        TextView cityNameText = findViewById(R.id.show_city_name);
        Button backButton = findViewById(R.id.button_back);

        // get the city name that was passed from main activity
        String cityName = getIntent().getStringExtra("CITY_NAME");

        // set it on the screen
        cityNameText.setText(cityName);

        // what to do when back button is clicked
        backButton.setOnClickListener(v -> {
            // finish destroys this screen and goes back to main
            finish();
        });
    }
}