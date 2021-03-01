package com.example.eventplanning;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UserQuestionsViewModel extends BaseObservable {
    // creating object of UserQuestionsModel class
    private UserQuestionsModel userQuestionsModel;

    // getter and setter methods for first name
    @Bindable
    public String getUserQuestionsFirstName() {
        return userQuestionsModel.getUserQuestionsFirstName();
    }

    public void setUserQuestionsFirstName(String firstName) {
        userQuestionsModel.setUserQuestionsFirstName(firstName);
        notifyPropertyChanged(BR.userQuestionsFirstName);
    }

    // getter and setter methods for last name
    @Bindable
    public String getUserQuestionsLastName() {
        return userQuestionsModel.getUserQuestionsLastName();
    }

    public void setUserQuestionsLastName(String lastName) {
        userQuestionsModel.setUserQuestionsLastName(lastName);
        notifyPropertyChanged(BR.userQuestionsLastName);
    }

    // getter and setter methods for email
    @Bindable
    public String getUserQuestionsEmailAddress() {
        return userQuestionsModel.getUserQuestionsEmailAddress();
    }

    public void setUserQuestionsEmailAddress(String emailAddress) {
        userQuestionsModel.setUserQuestionsEmailAddress(emailAddress);
        notifyPropertyChanged(BR.userQuestionsEmailAddress);
    }

    // getter and setter methods for question
    @Bindable
    public String getQuestion() {
        return userQuestionsModel.getQuestion();
    }

    public void setQuestion(String question) {
        userQuestionsModel.setQuestion(question);
        notifyPropertyChanged(BR.question);
    }

    // constructor of ViewModel class
    public UserQuestionsViewModel() {

        // instantiating object of model class
        userQuestionsModel = new UserQuestionsModel("","","","");
    }
}
