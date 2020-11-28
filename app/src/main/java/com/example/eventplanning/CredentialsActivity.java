package com.example.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

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
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userInfoDatabaseReference = database.getReference("User-Information");


//                userCredentials.stringUsername = usernameText.getText().toString();
//                userCredentials.stringUserPassword = userPasswordText.getText().toString();




                userInfoDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserCredentials userCredentials = dataSnapshot.getValue(UserCredentials.class);

//                        userCredentials = new UserCredentials(usernameText.getText().toString(), userPasswordText.getText().toString());
//                        userInfoDatabaseReference.push().setValue(userCredentials);
                        System.out.println("Username: " + userCredentials.getUsername());
                        System.out.println("User password: " + userCredentials.getUserPassword());

                        Toast.makeText(getBaseContext(), "Credentials Added", Toast.LENGTH_SHORT).show();
                    }

                    private static final String TAG = "LoginActivity";

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Log.e(TAG, "onCancelled: Something went wrong! Error:" + error.getMessage());
                    }
                });
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

        public UserCredentials(String stringUsername, String stringUserPassword){
            stringUsername = this.stringUsername;
            stringUserPassword = this.stringUserPassword;
        }

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

//    private void dataQuery(DataSnapshot snapshot) {
//        for (int y = 0; y != 2; y++) {
//            for (DataSnapshot ds : snapshot.getChildren()) {
//                UserCredentials findInfo = new UserCredentials();
//                findInfo.stringUserPassword = ds.child(String.valueOf(y)).getValue(UserCredentials.class).getUserPassword();
//                findInfo.stringUsername = ds.child(String.valueOf(y)).getValue(UserCredentials.class).getUsername();
//                findInfo.setUsername();
//                findInfo.setUserPassword();
//            }
//        }
//    }
}
