package com.example.moviestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class Favourite extends AppCompatActivity implements FavouriteAdapter.UserClickListener{

    List<MovieModel> movieModelList;
    RecyclerView recyclerView;
    FavouriteAdapter favouriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        recyclerView = findViewById(R.id.favouriteRv);

        Intent intent =getIntent();
        if(intent!=null){
            movieModelList =(List<MovieModel>) intent.getSerializableExtra("Favourite_movie");
        }

        prepareRecyclerView();





    }
    private void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        preAdapter();
    }

    public  void preAdapter(){
        favouriteAdapter = new FavouriteAdapter(movieModelList,this, this::selectedUser);
        recyclerView.setAdapter(favouriteAdapter);
    }

    @Override
    public void selectedUser(MovieModel movieModel) {
        startActivity(new Intent(this,Selected_movie.class).putExtra("data", movieModel));
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(favouriteAdapter);
    }
}