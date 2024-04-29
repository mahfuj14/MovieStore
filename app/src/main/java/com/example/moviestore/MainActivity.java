package com.example.moviestore;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestore.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.UserClickListener{

    private ActivityMainBinding binding;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    List<MovieModel> movieModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = findViewById(R.id.recyclerView);
        setData();
        prepareRecyclerView();


        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/
    }

    private void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        preAdapter();
    }

    public void setData(){
        movieModelList.add(new MovieModel("The Godfather","Marlon Brando, Al Pacino", "Crime", "March 15, 1972", "$6 million", "" ));
        movieModelList.add(new MovieModel("The Dark Knight","Christian Bale, Michael Caine", "Thriller", "July 14, 2008", "$185 million", "" ));
        movieModelList.add(new MovieModel("12 Angry Man","Henry Fonda, Lee J. Cobb", "Drama", "April 10, 1957","$0.337 million", ""));
        movieModelList.add(new MovieModel("Pulp Fiction","John Travolta, Samuel L. Jackson", "Comedy", "May 21, 1994", "8.5 million",""));
        movieModelList.add(new MovieModel("Forrest Gump","Tom Hanks, Robin Wright", "Comedy", "June 23, 1994", "55 million", "" ));
        movieModelList.add(new MovieModel("Fight Club", "Brad Pitt, Edward Norton", "Suspence", "September 10, 1999", "65 million", ""));
        movieModelList.add(new MovieModel("Inception","Leonardo DiCaprio, Ken Watanabe", "Adventure", "July 8, 2010", "160 million", "" ));
    }
    public  void preAdapter(){
        movieAdapter = new MovieAdapter(movieModelList,this, this::selectedUser);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void selectedUser(MovieModel movieModel) {
        startActivity(new Intent(this,Selected_movie.class).putExtra("data", movieModel));
    }
}