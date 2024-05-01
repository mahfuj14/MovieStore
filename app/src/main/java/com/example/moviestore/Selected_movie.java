package com.example.moviestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Selected_movie extends AppCompatActivity {
    TextView title, cast, category, releaseDate, budget;
    ImageView selectedImageview;
    MovieModel movieModel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_movie);

        title=findViewById(R.id.title2Tv);
        cast = findViewById(R.id.cast2Tv);
        category=findViewById(R.id.category2Tv);
        releaseDate=findViewById(R.id.releaseDate2Tv);
        budget = findViewById(R.id.budget2Tv);
        selectedImageview = findViewById(R.id.selectedImageView);

        intent = getIntent();

        if(intent!=null){
            movieModel = (MovieModel) intent.getSerializableExtra("data");
            String titleS = movieModel.getTitle();
            String castS = movieModel.getCast();
            String categoryS = movieModel.getCategory();
            String releaseDateS = movieModel.getReleaseDate();
            String budgetS = movieModel.getBudget();
            int imageUrlS = movieModel.getImageUrl();
            title.setText(titleS);
            cast.setText(castS);
            category.setText(categoryS);
            releaseDate.setText(releaseDateS);
            budget.setText(budgetS);
            selectedImageview.setImageResource(movieModel.getImageUrl());

        }
    }
}