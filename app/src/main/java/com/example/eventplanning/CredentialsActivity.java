package com.example.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eventplanning.databinding.ActivityCrentialsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CredentialsActivity extends AppCompatActivity {
    public static EditText usernameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCrentialsBinding activityCrentialsBinding = DataBindingUtil.setContentView(this, R.layout.activity_crentials);
        activityCrentialsBinding.setCredentialsViewModel(new CredentialsViewModel());
        activityCrentialsBinding.executePendingBindings();

        usernameText = (EditText) findViewById(R.id.editText_username);
        final EditText userPasswordText = (EditText) findViewById(R.id.editText_userPassword);

        final Button nextButton = (Button) findViewById(R.id.credentialsNextButton);
        final Button exitButton = (Button) findViewById(R.id.credentialsExitButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!usernameText.getText().toString().equals("") & !userPasswordText.getText().toString().equals("")){

                    UserCredentials userCredentials = new UserCredentials();

                    userCredentials.setUsername(usernameText.getText().toString());
                    userCredentials.setUserPassword(userPasswordText.getText().toString());

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference userInfoDatabaseReference = database.getReference("User");

                    // Add user credentials to Firebase
                    userInfoDatabaseReference.child(usernameText.getText().toString()).child("credentials").setValue(userCredentials);

                    Toast.makeText(getBaseContext(), "Credentials Added", Toast.LENGTH_SHORT).show();

                    // After the data is sent, send the user to RegisterActivity
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                }
                // If any of the fields aren't filled, do not record the data
                else{
                    Toast.makeText(getBaseContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
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

    public static class UserCredentials{
        String stringUsername;
        String stringUserPassword;

        public UserCredentials() {

        }
        public String getUsername() {
            return stringUsername;
        }

        public void setUsername(String username) {
            this.stringUsername = username;
        }

        public String getUserPassword() {
            return stringUserPassword;
        }

        public void setUserPassword(String password) {
            this.stringUserPassword = password;
        }
    }
}
