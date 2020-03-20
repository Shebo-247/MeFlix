package com.shebo.meflix.network;

import com.shebo.meflix.models.Movie;

public interface OnGetMovieCallback {
    void onSuccess(Movie movie);

    void onError();
}
