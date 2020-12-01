package com.example.eventplanning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.client.Firebase;

public class RegisterActivity extends AppCompatActivity {
    static EditText firstNameText;
    static EditText lastNameText;

    static String userID;

    EditText locationText;

    AppLocationService appLocationService;

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
//        locationText = (EditText) findViewById(R.id.location);

        final Button submitButton = (Button) findViewById(R.id.registerSubmitButton);
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

//        // Get location
//        Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
//
//        if (location != null) {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            LocationAddress locationAddress = new LocationAddress();
//            locationAddress.getAddressFromLocation(latitude, longitude, getApplicationContext(), new GeocoderHandler());
//        } else {
//            showSettingsAlert();
//        }

        //When submit button is clicked on the registration form
        //record user data to firebase database
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!firstNameText.getText().toString().equals("") && !lastNameText.getText().toString().equals("")
                        && !phoneNumberText.getText().toString().equals("") && !emailText.getText().toString().equals("")
                        && !streetAddressText.getText().toString().equals("") && !cityText.getText().toString().equals("")  && !postalCodeText.getText().toString().equals("")) {

                    // Send data to firebase
                    User user = new User();

                    // Make first name (Capital + Lower case)
                    String userFirstName = String.valueOf(firstNameText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < firstNameText.getText().toString().length(); i++) {
                        userFirstName += firstNameText.getText().toString().toLowerCase().charAt(i);
                    }

                    // Make Last name (Capital + Lower case)
                    String userLastName = String.valueOf(lastNameText.getText().toString().toUpperCase().charAt(0));
                    for (int i = 1; i < lastNameText.getText().toString().length(); i++) {
                        userLastName += lastNameText.getText().toString().toLowerCase().charAt(i);
                    }

                    // set child
                    user.setFirstName(userFirstName);
                    user.setLastName(userLastName);
                    user.setPhoneNumber(phoneNumberText.getText().toString());
                    user.setEmail(emailText.getText().toString());
                    user.setStreetAddress(streetAddressText.getText().toString());
                    user.setCity(cityText.getText().toString());
                    user.setPostalCode(postalCodeText.getText().toString());
                    user.setState(stateSpinner.getSelectedItem().toString());

                    //Getting Firebase Instance
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //Getting Database Reference
                    final DatabaseReference userdataDatabaseReference = database.getReference("User");

                    // Add user information to Firebase
                    userdataDatabaseReference.child(CredentialsActivity.usernameText.getText().toString()).child("information").setValue(user);

                    Toast.makeText(getBaseContext(), "Details Added", Toast.LENGTH_SHORT).show();

                    // After the data is sent, send the user to LoginActivity
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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

//    public void showSettingsAlert() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
//        alertDialog.setTitle("SETTINGS");
//        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
//        alertDialog.setPositiveButton("Settings",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        RegisterActivity.this.startActivity(intent);
//                    }
//                });
//        alertDialog.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//        alertDialog.show();
//    }
//
//    private class GeocoderHandler extends Handler {
//        @Override
//        public void handleMessage(Message message) {
//            String locationAddress;
//            switch (message.what) {
//                case 1:
//                    Bundle bundle = message.getData();
//                    locationAddress = bundle.getString("address");
//                    break;
//                default:
//                    locationAddress = null;
//            }
//            locationText.setText(locationAddress);
//        }
//    }
}
