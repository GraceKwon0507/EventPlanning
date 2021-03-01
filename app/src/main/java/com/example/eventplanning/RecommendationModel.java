package com.example.eventplanning;


import androidx.annotation.Nullable;

public class RecommendationModel {
    @Nullable
    String eventDescription, checklistTitle, checklistLink, linksEdit;

    // constructor to initialize the variables
    public RecommendationModel(String eventDescription, String checklistTitle, String checklistLink, String linksEdit){
        this.eventDescription = eventDescription;
        this.checklistTitle = checklistTitle;
        this.checklistLink = checklistLink;
        this.linksEdit = linksEdit;
    }

    // getter and setter methods for event type
    @Nullable
    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(@Nullable String eventDescription) {
        this.eventDescription = eventDescription;
    }

    // getter and setter methods for theme
    @Nullable
    public String getChecklistTitle() {
        return checklistTitle;
    }

    public void setChecklistTitle(@Nullable String checklistTitle) {
        this.checklistTitle = checklistTitle;
    }

    // getter and setter methods for number of people
    @Nullable
    public String getChecklistLink() {
        return checklistLink;
    }

    public void setChecklistLink(@Nullable String checklistLink) {
        this.checklistLink = checklistLink;
    }

    // getter and setter methods for location
    @Nullable
    public String getLinksEdit() {
        return linksEdit;
    }

    public void setLinksEdit(@Nullable String linksEdit) {
        this.linksEdit = linksEdit;
    }
}
