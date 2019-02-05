package com.example.userprofile.data.model;

import java.util.List;
public class EditUser {

    private User user = null;
    private SingleChoiceAttributes singleChoiceAttributes;
    private List<City> cities = null;

    public EditUser() {
    }
    public EditUser(User user, SingleChoiceAttributes singleChoiceAttributes, List<City> cities) {
        this.user = user;
        this.singleChoiceAttributes = singleChoiceAttributes;
        this.cities = cities;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SingleChoiceAttributes getSingleChoiceAttributes() {
        return singleChoiceAttributes;
    }

    public void setSingleChoiceAttributes(SingleChoiceAttributes singleChoiceAttributes) {
        this.singleChoiceAttributes = singleChoiceAttributes;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}