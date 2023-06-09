package com.example.techbulls.NetworkRequestUtility;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAPIClient {

    private static Retrofit retrofit;

    public static Retrofit getMovieList() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLS.base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
