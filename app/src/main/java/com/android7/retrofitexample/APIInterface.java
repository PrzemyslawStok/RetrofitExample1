package com.android7.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/simpleFunction")
    Call<List<Data>> getSimpleFunction();
}
