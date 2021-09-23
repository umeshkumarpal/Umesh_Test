package com.example.umesh_test;

import com.example.umesh_test.ModelClass.Model;
import com.example.umesh_test.WeatherModel.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("pincode/{PINCODE}")
    Call<List<Model>>getData(@Path("PINCODE") String id);

    @GET("v1/current.json?")
    Call<Weather> getWeatherData(
            @Query("key") String key,
            @Query("q") String cityname


    );


}
