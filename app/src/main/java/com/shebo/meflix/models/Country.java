package com.shebo.meflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("iso_3166_1")
    @Expose
    private String productionCountry;

    public String getProductionCountry() {
        return productionCountry;
    }
}
