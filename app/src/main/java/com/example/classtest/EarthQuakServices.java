package com.example.classtest;

import com.example.classtest.pojo.ResponseModel;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface EarthQuakServices {

    @GET
    Call<ResponseModel> getEarthQuakResponse(@Url String endUrl);

}
