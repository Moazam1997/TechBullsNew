package com.example.techbulls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.techbulls.Adapter.MovieAdp;
import com.example.techbulls.MainStructure.MoviePOJO;
import com.example.techbulls.MainStructure.MyViewModel;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyViewModel viewModel,viewModel1;
    MovieAdp adapter;
    RecyclerView recycle_list;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle_list = (RecyclerView) findViewById(R.id.recycle_list);
        searchView = (SearchView) findViewById(R.id.searchtxt);

        searchView.setQueryHint("Search your movie/show");
        // Create an instance of the ViewModel using ViewModelProvider
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel1 = new ViewModelProvider(this).get(MyViewModel.class);

        //searchbar
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel1.updateLIST(query,getResources().getString(R.string.API_KEY)).observe(MainActivity.this, new Observer<ArrayList<MoviePOJO>>() {
                    @Override
                    public void onChanged(ArrayList<MoviePOJO> result1) {
                        Log.e("MAIN2",result1.toString());
                        adapter.setData(result1);
                        for (MoviePOJO itemElement : result1) {


                            String title = itemElement.getName();
                            String year = itemElement.getYear();
                            String poster = itemElement.getImage();



                            Log.e("IF",title+" - "+year+" - "+poster);
                        }

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        viewModel.getLIST("batman",getResources().getString(R.string.API_KEY)).observe(MainActivity.this, new Observer<ArrayList<MoviePOJO>>() {
            @Override
            public void onChanged(ArrayList<MoviePOJO> result) {
                Log.e("MAIN",result.toString());
                adapter = new MovieAdp(result,MainActivity.this);

                recycle_list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recycle_list.setAdapter(adapter);
            }
        });

    }
}