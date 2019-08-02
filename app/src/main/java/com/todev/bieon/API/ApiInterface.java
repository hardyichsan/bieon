package com.todev.bieon.API;

import com.todev.bieon.Response.InformasiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("content/")
    Call<InformasiResponse> getinformasi(@Query("key") String key);
}
