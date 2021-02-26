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
        TextView checklistTitle = (TextView) findViewById(R.id.checklistTitle);
        TextView checklistLink = (TextView) findViewById(R.id.checklistLink);
        TextView linksEdit = (TextView) findViewById(R.id.linksEdit);

        final Button restartButton = (Button) findViewById(R.id.RecommendationRestartButton);
        final Button exitButton = (Button) findViewById(R.id.RecommendationExitButton);

        // By theme
        if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Games")) {
            eventImage.setImageResource(R.drawable.games);
            eventDescription.setText("Games are a great way to make everyone enjoy their time!");
            checklistLink.setText("https://docs.google.com/spreadsheets/u/2/d/1owcke5gLMHZriLytzK9a_GJQ4WL-PYmjuDpPRmYfhts/copy");

            if (com.example.eventplanning.MainActivity.CustomizedEvent.getEventTypeSpinnerString().equals("Kid's Birthday")) {
                linksEdit.setText("https://www.thespruce.com/free-birthday-party-games-1356524");
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getEventTypeSpinnerString().equals("Adult's Birthday")) {
                linksEdit.setText("https://easyeventplanning.com/adult-birthday-party-games");
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getEventTypeSpinnerString().equals("Family Reunion")) {
                linksEdit.setText("https://gatheredagain.com/family-reunion-games-list/");
            } else { // College party
                linksEdit.setText("https://www.scholarshippoints.com/campuslife/best-party-games-for-college-students/");
            }
        } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Movies")) {
            eventImage.setImageResource(R.drawable.movies);
            eventDescription.setText("If your guests are a fan of movies, a movie theme is the way to go!\n");
            checklistLink.setText("https://docs.google.com/spreadsheets/u/2/d/1MBIpxwev0uVa83gOUy1rCowFKeTkTujKxrehEQfZXBU/copy");
            linksEdit.setText("https://www.amazon.com/Movie-Theme-Party-Supplies/s?k=Movie+Theme+Party+Supplies");
        } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food")) {
            eventDescription.setText("Food is an important part of every event. Take a look at these ideas that will make your food theme party special!");
        } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")) {
            eventDescription.setText("Do you want some active experience for your upcoming party? Take a look at these ideas that will make your day special!");
        } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals")) {
            eventDescription.setText("If you want some time away from your city, take a look at these venues and rentals!");
        }

        // By event
        if (com.example.eventplanning.MainActivity.CustomizedEvent.getEventTypeSpinnerString().equals("Kid's Birthday")
                && (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food") || com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")
                || com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals"))) {
            eventImage.setImageResource(R.drawable.kids_birthday);
            checklistLink.setText("https://docs.google.com/spreadsheets/u/2/d/1uQ_aOHuu_w1MfTnr4MGK-cq0KzqyPxn7OVGa8CZe_PU/copy");
            linksEdit.setText("https://easyeventplanning.com/kids-birthday-party-ideas");

            if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food")) {
                eventImage.setImageResource(R.drawable.kids_birthday_food);
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")) {
                eventImage.setImageResource(R.drawable.kids_birthday_outdoors);
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals")) {
                eventImage.setImageResource(R.drawable.kids_birthday_venues_and_rentals);
            }
        } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getEventTypeSpinnerString().equals("Adult's Birthday")
                && (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food") || com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")
                || com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals"))) {
            eventImage.setImageResource(R.drawable.adults_birthday);
            checklistLink.setText("https://docs.google.com/spreadsheets/u/2/d/1dHQMCvqOSJFeuXbJyV27ZR2SzPFaOoHbZ406NF7jHV4/copy");
            linksEdit.setText("https://easyeventplanning.com/40th-birthday-party-ideas");

            if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food")) {
                eventImage.setImageResource(R.drawable.adult_birthday_food);
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")) {
                eventImage.setImageResource(R.drawable.adults_birthday_outdoors);
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals")) {
                eventImage.setImageResource(R.drawable.adults_bithday_venues_and_rentals);
            }
        } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getEventTypeSpinnerString().equals("Family Reunion")
                && (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food") || com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")
                || com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals"))) {
            eventImage.setImageResource(R.drawable.family_reunion);
            checklistLink.setText("https://docs.google.com/spreadsheets/u/2/d/1owcke5gLMHZriLytzK9a_GJQ4WL-PYmjuDpPRmYfhts/copy");
            linksEdit.setText("https://www.familyeducation.com/15-unique-family-reunion-ideas");

            if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food")) {
                eventImage.setImageResource(R.drawable.family_reunion_food);
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")) {
                eventImage.setImageResource(R.drawable.family_reunion_outdoors);
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals")) {
                eventImage.setImageResource(R.drawable.family_reunion_venues_and_rentals);
            }
        } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getEventTypeSpinnerString().equals("College Party")
                && (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food") || com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")
                || com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals"))) {
            eventImage.setImageResource(R.drawable.college_party);
            eventDescription.setText("College parties are tricky. Hope these help you!");
            checklistLink.setText("https://docs.google.com/spreadsheets/u/2/d/1Unug83SC0qq1hx5LB3e44ysmiRXz9hblEX1nOn7C6qg/copy");
            linksEdit.setText("https://www.signupgenius.com/college/fun-party-ideas.cfm");

            if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Food")) {
                eventImage.setImageResource(R.drawable.college_party_food);
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Outdoors")) {
                eventImage.setImageResource(R.drawable.college_party_outdoors);
            } else if (com.example.eventplanning.MainActivity.CustomizedEvent.getThemeSpinnerString().equals("Venues and Rentals")) {
                eventImage.setImageResource(R.drawable.college_party_venues_and_rentals);
            }
        }
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If restart button is pressed, send the user back to the com.example.eventplanning.MainViewModel
                Intent intent = new Intent(getApplicationContext(), com.example.eventplanning.MainActivity.class);
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
