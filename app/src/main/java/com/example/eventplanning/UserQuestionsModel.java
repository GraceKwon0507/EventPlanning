package com.example.eventplanning;

import androidx.annotation.Nullable;

public class UserQuestionsModel {
    @Nullable
    String firstName, lastName, emailAddress, question;

    // constructor to initialize the variables
    public UserQuestionsModel(String firstName, String lastName, String emailAddress, String question){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.question = question;
    }

    // getter and setter methods for first name
    @Nullable
    public String getUserQuestionsFirstName() {
        return firstName;
    }

    public void setUserQuestionsFirstName(@Nullable String firstName) {
        this.firstName = firstName;
    }

    // getter and setter methods for last name
    @Nullable
    public String getUserQuestionsLastName() {
        return lastName;
    }

    public void setUserQuestionsLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    // getter and setter methods for email
    @Nullable
    public String getUserQuestionsEmailAddress() {
        return emailAddress;
    }

    public void setUserQuestionsEmailAddress(@Nullable String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // getter and setter methods for question
    @Nullable
        public String getQuestion() {
        return question;
    }

    public void setQuestion(@Nullable String question) {
        this.question = question;
    }
}
