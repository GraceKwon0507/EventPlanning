package com.example.eventplanning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText locationText = (EditText) findViewById(R.id.editText_location);

        final Button submitButton = (Button) findViewById(R.id.customizeSubmitButton);
        final Button exitButton = (Button) findViewById(R.id.customizeExitButton);

        // Event Type Spinner
        // Apply the adapter to the spinner
        final Spinner eventTypeSpinner = (Spinner) findViewById(R.id.eventTypeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> eventTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.eventTypeSpinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        eventTypeSpinner.setAdapter(eventTypeAdapter);

        // Theme Spinner
        final Spinner themeSpinner = (Spinner) findViewById(R.id.themeSpinner);
        ArrayAdapter<CharSequence> themeAdapter = ArrayAdapter.createFromResource(this,
                R.array.themeSpinner, android.R.layout.simple_spinner_item);
        themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(themeAdapter);

        // Number of People Spinner
        final Spinner numOfPeopleSpinner = (Spinner) findViewById(R.id.numOfPeopleSpinner);
        ArrayAdapter<CharSequence> numOfPeopleAdapter = ArrayAdapter.createFromResource(this,
                R.array.numOfPeopleSpinner, android.R.layout.simple_spinner_item);
        numOfPeopleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numOfPeopleSpinner.setAdapter(numOfPeopleAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If exit button is pressed, send the user back to the login screen
                Intent intent = new Intent(getApplicationContext(), RecommendationActivity.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If exit button is pressed, send the user back to the login screen
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}