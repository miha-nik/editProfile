package com.example.userprofile.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

public class City extends BaseModel {

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("city")
    @Expose
    private String city;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getName() {
        return getCity();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        super.equals(obj);
        if(obj instanceof City){
            return this.city.equals(((City)obj).getCity());
        }
        return false;
    }


}
