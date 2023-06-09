package com.example.techbulls.MainStructure;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.techbulls.NetworkRequestUtility.ApiEndpoints;
import com.example.techbulls.NetworkRequestUtility.MyAPIClient;
import com.example.techbulls.NetworkRequestUtility.URLS;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MoviePOJO>> data;
    private MutableLiveData<ArrayList<MoviePOJO>> data1  = new MutableLiveData<ArrayList<MoviePOJO>>();


    public LiveData<ArrayList<MoviePOJO>> getLIST(String param1,String param2) {
        if (data == null) {
            data = new MutableLiveData<>();
            loadMovieList(param1,param2);
        }
        return data;
    }

    public LiveData<ArrayList<MoviePOJO>> updateLIST(String param1,String param2) {

            updateMovieList(param1,param2);

        return data1;
    }


    private void loadMovieList(String param1,String param2) {
        ArrayList<MoviePOJO> moviePOJOS = new ArrayList<MoviePOJO>();

        // Simulate data loading from a data source
        // For example, an API call or database query
        // Here, we are just setting a static value for demonstration purposes
        ApiEndpoints apiService = MyAPIClient.getMovieList().create(ApiEndpoints.class);
        Call<JsonObject> call = apiService.getList(URLS.base_url,param1,param2);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject != null && jsonObject.has("Search")) {
                        JsonArray searchArr = jsonObject.getAsJsonArray("Search");

                        // Iterate over the jsonArray and parse individual items
                        for (JsonElement itemElement : searchArr) {

                            JsonObject itemObject = itemElement.getAsJsonObject();
                            String title = itemObject.get("Title").getAsString();
                            String year = itemObject.get("Year").getAsString();
                            String poster = itemObject.get("Poster").getAsString();

                            MoviePOJO moviePOJO = new MoviePOJO();
                            moviePOJO.setImage(poster);
                            moviePOJO.setName(title);
                            moviePOJO.setYear(year);

                            moviePOJOS.add(moviePOJO);

//                            Log.e("IF",title+" - "+year+" - "+poster);
                        }
                        Log.e("IF",moviePOJOS.toString());
                        data.setValue(moviePOJOS);
                        Log.e("IF","IF");
                    }
                } else {
                    // Handle error response
                    Log.e("ELSE","error");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Handle network failure
                Log.e("ERROR","error");
            }
        });
    }

    //update view
    private void updateMovieList(String param1,String param2) {
        ArrayList<MoviePOJO> moviePOJOS = new ArrayList<MoviePOJO>();

        // Simulate data loading from a data source
        // For example, an API call or database query
        // Here, we are just setting a static value for demonstration purposes
        ApiEndpoints apiService = MyAPIClient.getMovieList().create(ApiEndpoints.class);
        Call<JsonObject> call = apiService.getList(URLS.base_url,param1,param2);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject != null && jsonObject.has("Search")) {
                        JsonArray searchArr = jsonObject.getAsJsonArray("Search");

                        // Iterate over the jsonArray and parse individual items
                        for (JsonElement itemElement : searchArr) {

                            JsonObject itemObject = itemElement.getAsJsonObject();
                            String title = itemObject.get("Title").getAsString();
                            String year = itemObject.get("Year").getAsString();
                            String poster = itemObject.get("Poster").getAsString();

                            MoviePOJO moviePOJO = new MoviePOJO();
                            moviePOJO.setImage(poster);
                            moviePOJO.setName(title);
                            moviePOJO.setYear(year);

                            moviePOJOS.add(moviePOJO);

//                            Log.e("IF",title+" - "+year+" - "+poster);
                        }
                        Log.e("IF",moviePOJOS.toString());
                        data1.setValue(moviePOJOS);
                        Log.e("IF","IF");
                    }
                } else {
                    // Handle error response
                    Log.e("ELSE","error");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Handle network failure
                Log.e("ERROR","error");
            }
        });
    }
}
