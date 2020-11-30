package com.example.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecommendationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        ImageView eventImage = (ImageView) findViewById(R.id.eventImage);
        TextView eventDescription = (TextView) findViewById(R.id.eventDescription);

        final Button restartButton = (Button) findViewById(R.id.RecommendationRestartButton);
        final Button exitButton = (Button) findViewById(R.id.RecommendationExitButton);

        if(MainActivity.CustomizedEvent.getEventTypeSpinnerString().equals("Family Reunion")){
            if(MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food")){
                eventImage.setImageResource(R.drawable.family_reunion_food);
                eventDescription.setText("Food is an important part of every event. Take a look at these ideas that will make your food theme family reunion special!");
            }
            else if(MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")){
                eventImage.setImageResource(R.drawable.family_reunion_outdoors);
                eventDescription.setText("Do you want some active experience for your upcoming family reunion? Take a look at these ideas that will make your day special!");
            }
        }


        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If restart button is pressed, send the user back to the MainActivity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
