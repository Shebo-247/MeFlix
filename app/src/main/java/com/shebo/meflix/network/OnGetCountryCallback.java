package com.shebo.meflix.network;

import com.shebo.meflix.models.Country;

import java.util.List;

public interface OnGetCountryCallback {

    void onSuccess(List<Country> countries);

    void onError();
}
