package com.example.eventplanning;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class RegisterViewModel extends BaseObservable {
    // creating object of Model class
    private RegisterModel registerModel;

    // string variables for toast messages
    private String successMessage = "Details Added";
    private String errorMessage = "Please fill out all the fields";

    @Bindable
    // string variable for toast message
    private String toastMessage = null;

    // getter and setter methods for toast message
    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

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
}
