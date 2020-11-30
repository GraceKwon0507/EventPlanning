package com.example.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CredentialsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crentials);

        final EditText firstNameCredentialsText = (EditText) findViewById(R.id.editText_firstNameCredentials);
        final EditText lastNameCredentialsText = (EditText) findViewById(R.id.editText_lastNameCredentials);
        final EditText usernameText = (EditText) findViewById(R.id.editText_username);
        final EditText userPasswordText = (EditText) findViewById(R.id.editText_userPassword);

        final Button submitButton = (Button) findViewById(R.id.credentialsSubmitButton);
        final Button exitButton = (Button) findViewById(R.id.credentialsExitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!firstNameCredentialsText.getText().toString().equals("") && !lastNameCredentialsText.getText().toString().equals("")
                        && !usernameText.getText().toString().equals("") & !userPasswordText.getText().toString().equals("")){
                    // Make first name (Capital + Lower case)
                    String userFirstName = String.valueOf(firstNameCredentialsText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < firstNameCredentialsText.getText().toString().length(); i++) {
                        userFirstName += firstNameCredentialsText.getText().toString().toLowerCase().charAt(i);
                    }

                    // Make Last name (Capital + Lower case)
                    String userLastName = String.valueOf(lastNameCredentialsText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < lastNameCredentialsText.getText().toString().length(); i++) {
                        userLastName += lastNameCredentialsText.getText().toString().toLowerCase().charAt(i);
                    }

                    if(RegisterActivity.firstNameText.getText().toString().equals(userFirstName)
                            && RegisterActivity.lastNameText.getText().toString().equals(userLastName)){
                        UserCredentials userCredentials = new UserCredentials();

                        userCredentials.setUsername(usernameText.getText().toString());
                        userCredentials.setUserPassword(userPasswordText.getText().toString());

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference userInfoDatabaseReference = database.getReference("User-Information");

                        // Add user credentials to Firebase
                        userInfoDatabaseReference.child(RegisterActivity.userID).child("credentials").setValue(userCredentials);

                        Toast.makeText(getBaseContext(), "Credentials Added", Toast.LENGTH_SHORT).show();
                    }
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
