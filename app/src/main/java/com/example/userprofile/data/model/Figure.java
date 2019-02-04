package com.example.userprofile.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

public class Figure extends BaseModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        super.equals(obj);
        if(obj instanceof Figure){
            return this.id.equals(((Figure)obj).getId());
        }
        return false;
    }
}