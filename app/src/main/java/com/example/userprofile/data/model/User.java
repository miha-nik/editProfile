package com.example.userprofile.data.model;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("about_me")
    @Expose
    private String aboutMe;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("ethnicity")
    @Expose
    private Ethnicity ethnicity;
    @SerializedName("figure")
    @Expose
    private Figure figure;
    @SerializedName("gender")
    @Expose
    private Gender gender;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("location")
    @Expose
    private City location;
    @SerializedName("profile_picture")
    @Expose
    private Picture profilePicture;
    @SerializedName("religion")
    @Expose
    private Religion religion;

    public User() {
    }

    public User(String aboutMe, String birthday, String displayName, Ethnicity ethnicity, Figure figure, Gender gender, String height, City location, Picture profilePicture, Religion religion) {
        this.aboutMe = aboutMe;
        this.birthday = birthday;
        this.displayName = displayName;
        this.ethnicity = ethnicity;
        this.figure = figure;
        this.gender = gender;
        this.height = height;
        this.location = location;
        this.profilePicture = profilePicture;
        this.religion = religion;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public City getLocation() {
        return location;
    }

    public void setLocation(City location) {
        this.location = location;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public String getModel(){
        return new GsonBuilder().create().toJson(this);
    }
}
