package com.example.eventplanning;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class CredentialsViewModel extends BaseObservable {
    // creating object of LoginModel class
    private CredentialsModel credentialsModel;

    // getter and setter methods for username
    @Bindable
    public String getCredentialsUsername() {
        return credentialsModel.getCredentialsUsername();
    }

    public void setCredentialsUsername(String username) {
        credentialsModel.setCredentialsUsername(username);
        notifyPropertyChanged(BR.credentialsUsername);
    }

    // getter and setter methods for password
    @Bindable
    public String getCredentialsPassword() {
        return credentialsModel.getCredentialsPassword();
    }

    public void setCredentialsPassword(String password) {
        credentialsModel.setCredentialsPassword(password);
        notifyPropertyChanged(BR.credentialsPassword);
    }

    public CredentialsViewModel() {
        // instantiating object of model class
        credentialsModel = new CredentialsModel("", "");
    }
}
