package com.example.moviestore;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestore.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.UserClickListener{

    private ActivityMainBinding binding;
    SearchView searchView;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MovieAdapter movieAdapter2;
    List<MovieModel> movieModelList = new ArrayList<>();
    List<MovieModel> favMovieList;
    UserModel userModel1;
    Spinner spinner;
    String  searchString;
    char selectedSpint;
    int item;
    FloatingActionButton profileFloating, favouriteFloating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = findViewById(R.id.recyclerView);

        searchView = findViewById(R.id.searchView);
        spinner = findViewById(R.id.spinner);
        profileFloating = findViewById(R.id.profileFloating);
        favouriteFloating = findViewById(R.id.favfloating);
        Intent intent =getIntent();
        userModel1 = (UserModel) intent.getSerializableExtra("User_information");



        recyclerView.setHasFixedSize(true);
        spinn();
        search();



        setData();
        prepareRecyclerView();


        profileFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the DestinationActivity
                Intent intent1 = new Intent(MainActivity.this, ProfileView.class).putExtra("User_information",(Serializable) userModel1);
                startActivity(intent1);
                startActivity(new Intent(MainActivity.this, ProfileView.class));
            }
        });


        favouriteFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<MovieModel> fav = favMovieList(movieModelList);
                startActivity(new Intent(MainActivity.this, Favourite.class).putExtra("Favourite_movie",(Serializable) fav));
            }
        });


    }

    private List<MovieModel> favMovieList(List<MovieModel> movieModelList) {
        favMovieList = new ArrayList<>();
        for(MovieModel movieModel:movieModelList){
            if(movieModel.isToogle()){
                favMovieList.add(movieModel);
            }
        }
        return favMovieList;
    }



    private void spinn() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
                if(selectedCategory.toLowerCase().equals("title")){
                    selectedSpint='1';
                }
                if(selectedCategory.toLowerCase().equals("cast")){
                    selectedSpint='2';
                }
                if(selectedCategory.toLowerCase().equals("category")){
                    selectedSpint='3';
                }
                /*Toast.makeText(MainActivity.this, "Selected Category: " + selectedCategory, Toast.LENGTH_SHORT).show();*/
                // Filter itemList based on selected category
                //performSearch();
                prepareRecyclerView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void search() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                searchString = newText;
                searchString = searchString+selectedSpint;
                movieAdapter.getFilter().filter(searchString);
                /*if(selectedSpinString.equals("title")){
                    movieAdapter2 = new MovieAdapter(movieModelList,MainActivity.this, "title");
                    movieAdapter2.getFilter().filter(searchString);
                }
                else if(selectedSpinString.equals("cast")){
                    movieAdapter2 = new MovieAdapter(movieModelList,MainActivity.this, "cast");
                    movieAdapter.getFilter().filter(searchString);
                }
                else if(selectedSpinString.equals("category")){
                    movieAdapter2 = new MovieAdapter(movieModelList,MainActivity.this, "category");
                    movieAdapter.getFilter().filter(searchString);
                }*/
                //performSearch();
                return false;
            }
        });
    }



    private void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        preAdapter();
    }

    public void setData(){
        movieModelList.add(new MovieModel("The Godfather","Marlon Brando, Al Pacino", "Crime", "March 15, 1972", "$6 million", R.drawable.the_godfather, false ));
        movieModelList.add(new MovieModel("The Dark Knight","Christian Bale, Michael Caine", "Thriller", "July 14, 2008", "$185 million",R.drawable.the_dark_knight , false));
        movieModelList.add(new MovieModel("12 Angry Man","Henry Fonda, Lee J. Cobb", "Drama", "April 10, 1957","$0.337 million", R.drawable.twelve_angry_men, false));
        movieModelList.add(new MovieModel("Pulp Fiction","John Travolta, Samuel L. Jackson", "Comedy", "May 21, 1994", "8.5 million",R.drawable.pulp_fiction, false));
        movieModelList.add(new MovieModel("Forrest Gump","Tom Hanks, Robin Wright", "Comedy", "June 23, 1994", "55 million", R.drawable.forrest_gump, false));
        movieModelList.add(new MovieModel("Fight Club", "Brad Pitt, Edward Norton", "Suspence", "September 10, 1999", "65 million", R.drawable.fight_club, false));
        movieModelList.add(new MovieModel("Inception","Leonardo DiCaprio, Ken Watanabe", "Adventure", "July 8, 2010", "160 million", R.drawable.inception, false ));
    }
    public  void preAdapter(){
        movieAdapter = new MovieAdapter(movieModelList,this, this::selectedUser);
        recyclerView.setAdapter(movieAdapter);
    }



    @Override
    public void selectedUser(MovieModel movieModel) {
        startActivity(new Intent(this,Selected_movie.class).putExtra("data", movieModel));
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(movieAdapter);
    }
}