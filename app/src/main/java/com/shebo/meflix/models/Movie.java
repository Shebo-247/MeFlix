package com.shebo.meflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("vote_average")
    @Expose
    private float rating;

    @SerializedName("runtime")
    @Expose
    private int runTime;

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    @SerializedName("production_countries")
    @Expose
    private List<Genre> productionCountries;

    public int getRunTime() {
        return runTime;
    }


    public List<Genre> getGenres() {
        return genres;
    }


    public float getRating() {
        return rating;
    }


    public String getBackdropPath() {
        return backdropPath;
    }


    public String getOverview() {
        return overview;
    }


    public String getReleaseDate() {
        return releaseDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public String getPosterPath() {
        return posterPath;
    }

}
