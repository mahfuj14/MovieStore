package com.example.moviestore;

import java.io.Serializable;

public class MovieModel implements Serializable {

    private  String title;
    private  String cast;
    private  String category;
    private  String releaseDate;
    private  String budget;
    private int imageUrl;
    private boolean toogle;

    public MovieModel(String title, String cast, String category, String releaseDate, String budget, int imageUrl, boolean toogle) {
        this.title = title;
        this.cast = cast;
        this.category = category;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.imageUrl = imageUrl;
        this.toogle = toogle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isToogle() {
        return toogle;
    }

    public void setToogle(boolean toogle) {
        this.toogle = toogle;
    }
}
