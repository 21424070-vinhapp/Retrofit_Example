package com.example.retrofit_example.api;

import com.example.retrofit_example.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //link api: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1

    Gson gson=new GsonBuilder()
            .setDateFormat("yyy-MM-dd HH-mm-ss")
            .create();

    ApiService apiService=new Retrofit.Builder()
            //baseUrl truyen domain api
            .baseUrl("http://apilayer.net/")
            //convert gson
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/live")
    Call<Currency> convertUsdToVnd(@Query("access_key") String access_key,
                                   @Query("currencies") String currencies,
                                   @Query("source") String source,
                                   @Query("format") int format);
}
