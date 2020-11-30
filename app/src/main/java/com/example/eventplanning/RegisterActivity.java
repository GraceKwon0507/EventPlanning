package com.example.eventplanning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.client.Firebase;

public class RegisterActivity extends AppCompatActivity {
    public Firebase ref;

    static EditText firstNameText;
    static EditText lastNameText;

    static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameText = (EditText) findViewById(R.id.editText_firstName);
        lastNameText = (EditText) findViewById(R.id.editText_lastName);
        final EditText phoneNumberText = (EditText) findViewById(R.id.editText_phoneNumber);
        final EditText emailText = (EditText) findViewById(R.id.editText_email);
        final EditText streetAddressText = (EditText) findViewById(R.id.editText_streetAddress);
        final EditText cityText = (EditText) findViewById(R.id.city_spinner);
        final EditText postalCodeText = (EditText) findViewById(R.id.editText_postalCode);

        final Button nextButton = (Button) findViewById(R.id.registerNextButton);
        final Button backButton = (Button) findViewById(R.id.registerBackButton);

        // Apply the adapter to the spinner
        final Spinner stateSpinner = (Spinner) findViewById(R.id.state_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,
                R.array.state_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        stateSpinner.setAdapter(stateAdapter);

        //When submit button is clicked on the registration form
        //record user data to firebase database
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!firstNameText.getText().toString().equals("") && !lastNameText.getText().toString().equals("")
                        && !phoneNumberText.getText().toString().equals("") && !emailText.getText().toString().equals("")
                        && !streetAddressText.getText().toString().equals("") && !cityText.getText().toString().equals("")  && !postalCodeText.getText().toString().equals("")) {

                    // Send data to firebase
                    User user = new User();

                    // Unique user ID
                    userID = firstNameText.getText().toString().toLowerCase();
                    userID += String.valueOf(lastNameText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < lastNameText.getText().toString().length(); i++) {
                        userID += lastNameText.getText().toString().toLowerCase().charAt(i);
                    }

                    // Make first name (Capital + Lower case)
                    String userFirstNameID = String.valueOf(firstNameText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < firstNameText.getText().toString().length(); i++) {
                        userFirstNameID += firstNameText.getText().toString().toLowerCase().charAt(i);
                    }

                    // Make Last name (Capital + Lower case)
                    String userLastNameID = String.valueOf(lastNameText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < lastNameText.getText().toString().length(); i++) {
                        userLastNameID += lastNameText.getText().toString().toLowerCase().charAt(i);
                    }

                    // set child
                    user.setFirstName(userFirstNameID);
                    user.setLastName(userLastNameID);
                    user.setPhoneNumber(phoneNumberText.getText().toString());
                    user.setEmail(emailText.getText().toString());
                    user.setStreetAddress(streetAddressText.getText().toString());
                    user.setCity(cityText.getText().toString());
                    user.setPostalCode(postalCodeText.getText().toString());
                    user.setState(stateSpinner.getSelectedItem().toString());

                    //Getting Firebase Instance
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //Getting Database Reference
                    final DatabaseReference userdataDatabaseReference = database.getReference("User-Information");

                    // Add user information to Firebase
                    userdataDatabaseReference.child(userID).setValue(user);

                    Toast.makeText(getBaseContext(), "Details Added", Toast.LENGTH_SHORT).show();

                    // After the data is sent, send the user to CredentialsActivity
                    Intent intent = new Intent(getApplicationContext(), CredentialsActivity.class);
                    startActivity(intent);
                }
                // If any of the fields aren't filled, do not record the data
                // Let the user know to fill out everything
                else {
                    Toast.makeText(getBaseContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If back button is pressed, send the user back to the login screen
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public static class User {
        String stringFirstName;
        String stringLastName;
        String stringPhoneNumber;
        String stringEmail;
        String stringStreetAddress;
        String stringCity;
        String stringState;
        String stringPostalCode;

        public User() {
        }

        public String getFirstName() {
            return stringFirstName;
        }

        public void setFirstName(String firstName) {
            this.stringFirstName = firstName;
        }

        public String getLastName() {
            return stringLastName;
        }

        public void setLastName(String lastName) {
            this.stringLastName = lastName;
        }

        public String getPhoneNumber() {
            return stringPhoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.stringPhoneNumber = phoneNumber;
        }

        public String getEmail() {
            return stringEmail;
        }

        public void setEmail(String email) {
            this.stringEmail = email;
        }

        public String getStreetAddress() {
            return stringStreetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.stringStreetAddress = streetAddress;
        }

        public String getCity() {
            return stringCity;
        }

        public void setCity(String city) {
            this.stringCity = city;
        }

        public String getState() {
            return stringState;
        }

        public void setState(String state) {
            this.stringState = state;
        }

        public String getPostalCode() {
            return stringPostalCode;
        }

        public void setPostalCode(String postalCode) {
            this.stringPostalCode = postalCode;
        }

        //Credentials
        String stringUsername;
        String stringUserPassword;

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
