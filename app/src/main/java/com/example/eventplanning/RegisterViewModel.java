package com.example.eventplanning;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterViewModel extends BaseObservable {
    // creating object of RegisterModel class
    private RegisterModel registerModel;

    // string variables for
    // toast messages
    private String successMessage = "Details Added";
    private String phoneNumberErrorMessage = "Please enter a valid phone number";
    private String emailErrorMessage = "Please enter a valid email";
    private String emailPhoneNumberErrorMessage = "Please enter a valid phone number and email";
    private String errorMessage = "Please fill out all the fields";

    // getter and setter methods for first name
    @Bindable
    public String getFirstName() {
        return registerModel.firstName;
    }

    public void setFirstName(String firstName) {
        registerModel.setFirstName(firstName);
        notifyPropertyChanged(BR.firstName);
    }

    // getter and setter methods for last name
    @Bindable
    public String getLastName() {
        return registerModel.lastName;
    }

    public void setLastName(String lastName) {
        registerModel.setLastName(lastName);
        notifyPropertyChanged(BR.lastName);
    }

    // getter and setter methods for phone number
    @Bindable
    public String getPhoneNumber() {
        return registerModel.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        registerModel.setPhoneNumber(phoneNumber);
        notifyPropertyChanged(BR.phoneNumber);
    }

    // getter and setter methods for email
    @Bindable
    public String getEmailAddress() {
        return registerModel.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        registerModel.setEmailAddress(emailAddress);
        notifyPropertyChanged(BR.emailAddress);
    }

    // getter and setter methods for street address
    @Bindable
    public String getStreetAddress() {
        return registerModel.streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        registerModel.setStreetAddress(streetAddress);
        notifyPropertyChanged(BR.streetAddress);
    }

    // getter and setter methods for city
    @Bindable
    public String getCity() {
        return registerModel.city;
    }

    public void setCity(String city) {
        registerModel.setCity(city);
        notifyPropertyChanged(BR.city);
    }

    // getter and setter methods for state
    @Bindable
    public String getState() {
        return registerModel.state;
    }

    public void setState(String state) {
        registerModel.setState(state);
        notifyPropertyChanged(BR.state);
    }

    // getter and setter methods for postal code
    @Bindable
    public String getPostalCode() {
        return registerModel.postalCode;
    }

    public void setPostalCode(String postalCode) {
        registerModel.setPostalCode(postalCode);
        notifyPropertyChanged(BR.postalCode);
    }

    // constructor of ViewModel class
    public RegisterViewModel() {
        // instantiating object of model class
        registerModel = new RegisterModel("","","","","","","","");
    }

    @Bindable
    // string variable for
    // toast message
    private String toastMessage = null;

    // getter and setter methods
    // for toast message
    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void onSubmitButtonClicked() {
        if (isValid()){

            if(!android.util.Patterns.PHONE.matcher(getPhoneNumber()).matches() && android.util.Patterns.EMAIL_ADDRESS.matcher(getEmailAddress()).matches()){
                setToastMessage(phoneNumberErrorMessage);
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(getEmailAddress()).matches() && android.util.Patterns.PHONE.matcher(getPhoneNumber()).matches()){
                setToastMessage(emailErrorMessage);
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(getEmailAddress()).matches() && !android.util.Patterns.PHONE.matcher(getPhoneNumber()).matches()){
                setToastMessage(emailPhoneNumberErrorMessage);
            }
            else {
                // Send data to firebase
                User user = new User();

                // Make first name (Capital + Lower case)
                String userFirstName = String.valueOf(registerModel.getFirstName().toUpperCase().charAt(0));
                for (int i = 1; i < registerModel.getFirstName().length(); i++) {
                    userFirstName += registerModel.getFirstName().toLowerCase().charAt(i);
                }

                // Make Last name (Capital + Lower case)
                String userLastName = String.valueOf(registerModel.getLastName().toUpperCase().charAt(0));
                for (int i = 1; i < registerModel.getLastName().length(); i++) {
                    userLastName += registerModel.getLastName().toLowerCase().charAt(i);
                }

                // set child
                user.setFirstName(userFirstName);
                user.setLastName(userLastName);
                user.setPhoneNumber(registerModel.getPhoneNumber());
                user.setEmail(registerModel.getEmailAddress());
                user.setStreetAddress(registerModel.getStreetAddress());
                user.setCity(registerModel.getCity());
                user.setPostalCode(registerModel.getPostalCode());
                user.setState(registerModel.getState());

                //Getting Firebase Instance
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //Getting Database Reference
                final DatabaseReference userdataDatabaseReference = database.getReference("User");

                // Add user information to Firebase
                userdataDatabaseReference.child(CredentialsActivity.usernameText.getText().toString()).child("information").setValue(user);

                setToastMessage(successMessage);
            }
        }

        else
            setToastMessage(errorMessage);
    }

    // method to keep a check
    // that variable fields must
    // not be kept empty by user
    public boolean isValid() {
        return !TextUtils.isEmpty(getFirstName()) && !TextUtils.isEmpty(getLastName()) && !TextUtils.isEmpty(getPhoneNumber())
                && !TextUtils.isEmpty(getEmailAddress()) && !TextUtils.isEmpty(getCity()) && !TextUtils.isEmpty(getPostalCode());
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
