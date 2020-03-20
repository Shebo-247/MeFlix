package com.shebo.meflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResponse {
    @SerializedName("cast")
    @Expose
    private List<Cast> cast;

    public List<Cast> getCast() {
        return cast;
    }
}
