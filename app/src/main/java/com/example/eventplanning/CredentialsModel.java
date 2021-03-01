package com.example.eventplanning;

import androidx.annotation.Nullable;

public class CredentialsModel {
    @Nullable
    String username, password;

    // constructor to initialize the variables
    public CredentialsModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    // getter and setter methods for username
    @Nullable
    public String getCredentialsUsername() {
        return username;
    }

    public void setCredentialsUsername(@Nullable String username) {
        this.username = username;
    }

    // getter and setter methods for password
    @Nullable
    public String getCredentialsPassword() {
        return password;
    }

    public void setCredentialsPassword(@Nullable String password) {
        this.password = password;
    }
}