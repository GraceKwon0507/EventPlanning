package com.example.eventplanning;

import android.os.Bundle;
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

                UserCredentials userCredentials = new UserCredentials();

                userCredentials.setUsername(usernameText.getText().toString());
                userCredentials.setUserPassword(userPasswordText.getText().toString());

//
//                userInfoDatabaseReference.child()
                //Getting Database Reference
                final DatabaseReference databaseReference = database.getReference("User-Credentials");

                databaseReference.push().setValue(userCredentials);

                Toast.makeText(getBaseContext(), "Credentials Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class UserCredentials{
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

    private void dataQuery(DataSnapshot snapshot) {
        for (int y = 0; y != 2; y++) {
            for (DataSnapshot ds : snapshot.getChildren()) {
                UserCredentials findInfo = new UserCredentials();
                findInfo.setUsername(ds.child(String.valueOf(y)).getValue(UserCredentials.class).getUsername());
                findInfo.setUserPassword(ds.child(String.valueOf(y)).getValue(UserCredentials.class).getUserPassword());
            }
        }
    }

}
