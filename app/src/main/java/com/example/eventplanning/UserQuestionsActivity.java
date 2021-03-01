package com.example.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.eventplanning.databinding.ActivityUserquestionsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserQuestionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserquestionsBinding activityUserQuestionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_userquestions);
        activityUserQuestionsBinding.setUserQuestionsViewModel(new UserQuestionsViewModel());
        activityUserQuestionsBinding.executePendingBindings();

        final EditText firstNameText = (EditText) findViewById(R.id.editText_questionsFirstName);
        final EditText lastNameText = (EditText) findViewById(R.id.editText_questionsLastName);
        final EditText emailText = (EditText) findViewById(R.id.editText_questionsEmail);
        final EditText question = (EditText) findViewById(R.id.editText_question);

        final TextView thankYouText = (TextView) findViewById(R.id.thankYouText);

        final Button submitButton = (Button) findViewById(R.id.questionsSubmitButton);
        final Button exitButton = (Button) findViewById(R.id.questionsExitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!firstNameText.getText().toString().equals("") && !lastNameText.getText().toString().equals("")
                        && !emailText.getText().toString().equals("") && !question.getText().toString().equals("")) {
                    // Send data to firebase
                UserQuestions userQuestions = new UserQuestions();

                    // Make first name (Capital + Lower case)
                    String firstName = String.valueOf(firstNameText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < firstNameText.getText().toString().length(); i++) {
                        firstName += firstNameText.getText().toString().toLowerCase().charAt(i);
                    }

                    // Make Last name (Capital + Lower case)
                    String lastName = String.valueOf(lastNameText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < lastNameText.getText().toString().length(); i++) {
                        lastName += lastNameText.getText().toString().toLowerCase().charAt(i);
                    }

                    String questionID = firstNameText.getText().toString().toLowerCase();
                    questionID += lastName;

                    // set child
                    userQuestions.setQuestionsFirstName(firstName);
                    userQuestions.setQuestionsLastName(lastName);
                    userQuestions.setQuestionsEmail(emailText.getText().toString());
                    userQuestions.setQuestion(question.getText().toString());

                    //Getting Firebase Instance
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //Getting Database Reference
                    final DatabaseReference userdataDatabaseReference = database.getReference("Questions");

                    // Add user information to Firebase
                    userdataDatabaseReference.child(questionID).setValue(userQuestions);

                    thankYouText.setVisibility(View.VISIBLE);
                }
                // If any of the fields aren't filled, do not record the data
                // Let the user know to fill out everything
                else {
                    Toast.makeText(getBaseContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
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
    }

    public static class UserQuestions {
        String questionsFirstName;
        String questionsLastName;
        String questionsEmail;
        String question;

        UserQuestions(){

        }

        public String getQuestionsFirstName() {
            return questionsFirstName;
        }

        public void setQuestionsFirstName(String questionsFirstName) {
            this.questionsFirstName = questionsFirstName;
        }

        public String getQuestionsLastName() {
            return questionsLastName;
        }

        public void setQuestionsLastName(String questionsLastName) {
            this.questionsLastName = questionsLastName;
        }

        public String getQuestionsEmail() {
            return questionsEmail;
        }

        public void setQuestionsEmail(String questionsEmail) {
            this.questionsEmail = questionsEmail;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }
}
