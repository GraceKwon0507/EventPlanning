package com.example.eventplanning;

import androidx.annotation.Nullable;

public class Model {
    @Nullable
    String eventType, theme, numOfPeople, location;

    public Model(String eventType, String theme, String numOfPeople, String location){
        this.eventType = eventType;
        this.theme = theme;
        this.numOfPeople = numOfPeople;
        this.location = location;
    }

    @Nullable
    public String getEventType() {
        return eventType;
    }

    public void setEventType(@Nullable String eventType) {
        this.eventType = eventType;
    }

    @Nullable
    public String getTheme() {
        return theme;
    }

    public void setTheme(@Nullable String theme) {
        this.theme = theme;
    }

    @Nullable
    public String getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(@Nullable String numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    @Nullable
    public String getLocation() {
        return location;
    }

    public void setLocation(@Nullable String location) {
        this.location = location;
    }
}
