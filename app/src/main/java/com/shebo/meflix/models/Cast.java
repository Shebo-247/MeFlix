package com.shebo.meflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cast {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("profile_path")
    @Expose
    private String profilePath;

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
