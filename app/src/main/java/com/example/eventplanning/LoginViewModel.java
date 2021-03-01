package com.example.eventplanning;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class LoginViewModel extends BaseObservable {
    // creating object of LoginModel class
    private LoginModel loginModel;

    // getter and setter methods for username
    @Bindable
    public String getUsername() {
        return loginModel.getUsername();
    }

    public void setUsername(String username) {
        loginModel.setUsername(username);
        notifyPropertyChanged(BR.username);
    }

    // getter and setter methods for password
    @Bindable
    public String getPassword() {
        return loginModel.getPassword();
    }

    public void setPassword(String password) {
        loginModel.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    public LoginViewModel() {
        // instantiating object of model class
        loginModel = new LoginModel("", "");
    }
}
