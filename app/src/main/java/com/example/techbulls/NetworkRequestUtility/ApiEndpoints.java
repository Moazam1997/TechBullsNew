package com.example.techbulls.NetworkRequestUtility;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiEndpoints  {

    @POST
    Call<JsonObject> getList(@Url String url,
                                   @Query("s") String param1,
                                   @Query("apikey") String param2);
}
