package com.shebo.meflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("key")
    @Expose
    private String key;

    public String getKey() {
        return key;
    }
}
