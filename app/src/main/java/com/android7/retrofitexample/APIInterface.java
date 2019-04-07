package com.android7.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/simpleFunction")
    Call<List<Data>> getSimpleFunction();

    @GET("/squareFunction")
    Call<List<Data>> getSquareFunction(@Query("A") float A, @Query("B") float B, @Query("C") float C);
}
