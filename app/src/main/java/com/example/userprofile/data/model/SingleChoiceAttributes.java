package com.example.userprofile.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleChoiceAttributes {

    @SerializedName("gender")
    @Expose
    private List<Gender> gender = null;
    @SerializedName("ethnicity")
    @Expose
    private List<Ethnicity> ethnicity = null;
    @SerializedName("religion")
    @Expose
    private List<Religion> religion = null;
    @SerializedName("figure")
    @Expose
    private List<Figure> figure = null;

    public List<Gender> getGender() {
        return gender;
    }

    public void setGender(List<Gender> gender) {
        this.gender = gender;
    }

    public List<Ethnicity> getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(List<Ethnicity> ethnicity) {
        this.ethnicity = ethnicity;
    }

    public List<Religion> getReligion() {
        return religion;
    }

    public void setReligion(List<Religion> religion) {
        this.religion = religion;
    }

    public List<Figure> getFigure() {
        return figure;
    }

    public void setFigure(List<Figure> figure) {
        this.figure = figure;
    }
}
