package com.shebo.meflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryResponse {
    @SerializedName("production_countries")
    @Expose
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }
}
