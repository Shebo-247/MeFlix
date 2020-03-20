package com.shebo.meflix.network;

import com.shebo.meflix.models.Cast;

import java.util.List;

public interface OnGetCastCallback {

    void onSuccess(List<Cast> casts);

    void onError();
}
