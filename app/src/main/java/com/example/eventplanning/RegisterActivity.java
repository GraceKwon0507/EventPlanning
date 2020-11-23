package com.example.eventplanning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.firebase.client.Firebase;

public class RegisterActivity extends AppCompatActivity {
    public Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText firstNameText = (EditText) findViewById(R.id.editText_firstName);
        final EditText lastNameText = (EditText) findViewById(R.id.editText_lastName);
        final EditText phoneNumberText = (EditText) findViewById(R.id.editText_phoneNumber);
        final EditText emailText = (EditText) findViewById(R.id.editText_email);
        final EditText streetAddressText = (EditText) findViewById(R.id.editText_streetAddress);
        final Button sendData = (Button) findViewById(R.id.registerSubmitButton);

        final Spinner citySpinner = (Spinner) findViewById(R.id.city_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        citySpinner.setAdapter(cityAdapter);

        final Spinner stateSpinner = (Spinner) findViewById(R.id.state_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,
                R.array.state_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        stateSpinner.setAdapter(stateAdapter);

//        Firebase.setAndroidContext(this);
//        ref = new Firebase("https://event-planning-5b640.firebaseio.com/");
//
//        ref.child("Users");

        //When submit button is clicked on the registration form
        //record user data to firebase database
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Firebase firstNameChild = ref.child("First Name");
//                firstNameChild.setValue(firstNameText.getText() + "");
//                Firebase lastNameChild = ref.child("Last Name");
//                lastNameChild.setValue(lastNameText.getText() + "");
//                Firebase phoneNumberChild = ref.child("Phone Number");
//                lastNameChild.setValue(phoneNumberText.getText() + "");
//                Firebase emailChild = ref.child("Email");
//                emailChild.setValue(emailText.getText() + "");
//                Firebase streetAddressChild = ref.child("Street Address");
//                streetAddressChild.setValue(streetAddressText.getText() + "");
//                Firebase cityChild = ref.child("City");
//                cityChild.setValue(citySpinner.getSelectedItem().toString() + "");
//                Firebase stateChild = ref.child("State");
//                stateChild.setValue(stateSpinner.getSelectedItem().toString() + "");


                String firstNameDB = firstNameText.getText().toString();
                String lastNameDB = lastNameText.getText().toString();
                String phoneNumberDB = phoneNumberText.getText().toString();
                String emailDB = emailText.getText().toString();
                String streetAddressDB = streetAddressText.getText().toString();
                String cityDB = citySpinner.getSelectedItem().toString();
                String stateDB = stateSpinner.getSelectedItem().toString();

                for (int i = 0; i < 5; i++) {
                    User user = new User(firstNameDB, lastNameDB, phoneNumberDB, emailDB, streetAddressDB, cityDB, stateDB);

                    //Getting Firebase Instance
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //Getting Database Reference
                    final DatabaseReference databaseReference = database.getReference("User");

                    databaseReference.setValue(user);

                    Toast.makeText(getBaseContext(), "Details Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class User {
        String stringFirstName;
        String stringLastName;
        String stringPhoneNumber;
        String stringEmail;
        String stringStreetAddress;
        String stringCity;
        String stringState;

        public User(String firstName, String lastName, String phoneNumber, String email, String streetAddress, String city, String state) {
            this.stringFirstName = firstName;
            this.stringLastName = lastName;
            this.stringPhoneNumber = phoneNumber;
            this.stringEmail = email;
            this.stringStreetAddress = streetAddress;
            this.stringCity = city;
            this.stringState = state;
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
    }

}
