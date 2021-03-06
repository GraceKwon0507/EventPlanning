package com.example.eventplanning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.eventplanning.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public Spinner eventTypeSpinner;
    public Spinner themeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new MainViewModel());
        activityMainBinding.executePendingBindings();

        final Button simpleSearch = (Button) findViewById(R.id.simpleSearch);
        final Button advancedSearch = (Button) findViewById(R.id.advancedSearch);
        final Button questions = (Button) findViewById(R.id.questions);

        final Button submitButton = (Button) findViewById(R.id.customizeSubmitButton);
        final Button exitButton = (Button) findViewById(R.id.customizeExitButton);

        // Event Type Spinner
        // Apply the adapter to the spinner
        eventTypeSpinner = (Spinner) findViewById(R.id.eventTypeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> eventTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.eventTypeSpinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        eventTypeSpinner.setAdapter(eventTypeAdapter);

        // Theme Spinner
        themeSpinner = (Spinner) findViewById(R.id.themeSpinner);
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

        simpleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.eventType).setVisibility(View.VISIBLE);
                findViewById(R.id.eventTypeSpinner).setVisibility(View.VISIBLE);
                findViewById(R.id.customizeSubmitButton).setVisibility(View.VISIBLE);
                findViewById(R.id.customizeExitButton).setVisibility(View.VISIBLE);

                findViewById(R.id.simpleSearch).setVisibility(View.INVISIBLE);
                findViewById(R.id.advancedSearch).setVisibility(View.INVISIBLE);
                findViewById(R.id.questions).setVisibility(View.INVISIBLE);
            }
        });

        advancedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.eventType).setVisibility(View.VISIBLE);
                findViewById(R.id.eventTypeSpinner).setVisibility(View.VISIBLE);
                findViewById(R.id.theme).setVisibility(View.VISIBLE);
                findViewById(R.id.themeSpinner).setVisibility(View.VISIBLE);
                findViewById(R.id.numOfPeople).setVisibility(View.VISIBLE);
                findViewById(R.id.numOfPeopleSpinner).setVisibility(View.VISIBLE);
                findViewById(R.id.location).setVisibility(View.VISIBLE);
                findViewById(R.id.editText_location).setVisibility(View.VISIBLE);
                findViewById(R.id.customizeSubmitButton).setVisibility(View.VISIBLE);
                findViewById(R.id.customizeExitButton).setVisibility(View.VISIBLE);

                findViewById(R.id.simpleSearch).setVisibility(View.INVISIBLE);
                findViewById(R.id.advancedSearch).setVisibility(View.INVISIBLE);
                findViewById(R.id.questions).setVisibility(View.INVISIBLE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomizedEvent customizedEvent = new CustomizedEvent();

            customizedEvent.setEventTypeSpinnerString(eventTypeSpinner);
            customizedEvent.setThemeSpinnerString(themeSpinner);

                // If submit button is pressed, send the user to RecommendationActivity
                Intent intent = new Intent(getApplicationContext(), RecommendationActivity.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If exit button is pressed, send the user back to the main screen
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If questions button is pressed, send the user to UserQuestionsActivity
                Intent intent = new Intent(getApplicationContext(), UserQuestionsActivity.class);
                startActivity(intent);
            }
        });
    }

    public static class CustomizedEvent{
        static String eventTypeSpinnerString;
        static String themeSpinnerString;

        public CustomizedEvent(){
        }

        public static String getEventTypeSpinnerString() {
            return eventTypeSpinnerString;
        }

        public static void setEventTypeSpinnerString(Spinner eventTypeSpinner){
            eventTypeSpinnerString = eventTypeSpinner.getSelectedItem().toString();
        }

        public static String getThemeSpinnerString() {
            return themeSpinnerString;
        }

        public static void setThemeSpinnerString(Spinner themeSpinner){
            themeSpinnerString = themeSpinner.getSelectedItem().toString();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}