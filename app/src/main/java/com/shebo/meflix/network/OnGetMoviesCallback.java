package com.shebo.meflix.network;

import com.shebo.meflix.models.Movie;

import java.util.List;

public interface OnGetMoviesCallback {
    void onSuccess(List<Movie> movies);

    void onError();
}
