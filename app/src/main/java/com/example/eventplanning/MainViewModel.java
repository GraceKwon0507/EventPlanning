package com.example.eventplanning;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class MainViewModel extends BaseObservable {
    // creating object of Model class
    private Model model;

    // getter and setter methods for event type
    @Bindable
    public String getEventType() {
        return model.getEventType();
    }

    public void setEventType(String eventType) {
        model.setEventType(eventType);
        notifyPropertyChanged(BR.eventType);
    }

    @Bindable
    public String getTheme() {
        return model.getTheme();
    }

    public void setTheme(String theme) {
        model.setTheme(theme);
        notifyPropertyChanged(BR.theme);
    }

    @Bindable
    public String getNumOfPeople() {
        return model.getNumOfPeople();
    }

    public void setNumOfPeople(String numOfPeople) {
        model.setNumOfPeople(numOfPeople);
        notifyPropertyChanged(BR.numOfPeople);
    }

    @Bindable
    public String getLocation() {
        return model.getLocation();
    }

    public void setLocation(String location) {
        model.setLocation(location);
        notifyPropertyChanged(BR.location);
    }

    public MainViewModel() {

        // instantiating object of model class
        model = new Model("", "", "", "");
    }
}
