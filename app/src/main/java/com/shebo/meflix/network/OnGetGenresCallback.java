package com.shebo.meflix.network;

import com.shebo.meflix.models.Genre;

import java.util.List;

public interface OnGetGenresCallback {

    void onSuccess(List<Genre> genres);

    void onError();
}
