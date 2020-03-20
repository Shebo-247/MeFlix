package com.shebo.meflix.network;

import com.shebo.meflix.models.Trailer;

import java.util.List;

public interface OnGetTrailersCallback {

    void onSuccess(List<Trailer> trailers);

    void onError();
}
