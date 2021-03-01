package com.example.eventplanning;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class RecommendationViewModel extends BaseObservable {
    // creating object of Model class
    private RecommendationModel recommendationModel;

    // getter and setter methods for event type
    @Bindable
    public String getEventDescription() {
        return recommendationModel.getEventDescription();
    }

    public void setEventDescription(String eventDescription) {
        recommendationModel.setEventDescription(eventDescription);
        notifyPropertyChanged(BR.eventDescription);
    }

    // getter and setter methods for theme
    @Bindable
    public String getChecklistTitle() {
        return recommendationModel.getChecklistTitle();
    }

    public void setChecklistTitle(String checklistTitle) {
        recommendationModel.setChecklistTitle(checklistTitle);
        notifyPropertyChanged(BR.checklistTitle);
    }

    // getter and setter methods for number of people
    @Bindable
    public String getChecklistLink() {
        return recommendationModel.getChecklistLink();
    }

    public void setChecklistLink(String checklistLink) {
        recommendationModel.setChecklistLink(checklistLink);
        notifyPropertyChanged(BR.checklistLink);
    }

    // getter and setter methods for location
    @Bindable
    public String getLinksEdit() {
        return recommendationModel.linksEdit;
    }

    public void setLinksEdit(String linksEdit) {
        recommendationModel.setLinksEdit(linksEdit);
        notifyPropertyChanged(BR.linksEdit);
    }

    public RecommendationViewModel() {

        // instantiating object of model class
        recommendationModel = new RecommendationModel("", "", "", "");
    }
}
