package com.example.moviestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    public List<MovieModel> movieModelList = new ArrayList<>();
    public Context context;
    public  UserClickListener userClickListener;

    public interface UserClickListener{
        void selectedUser(MovieModel movieModel);
    }

    public MovieAdapter(List<MovieModel>movieModelList, Context context, UserClickListener userClickListener){
        this.movieModelList=movieModelList;
        this.context = context;
        this.userClickListener= userClickListener;
    }



    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_row, parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        MovieModel movieModel = movieModelList.get(position);
        String title = movieModel.getTitle();
        String cast = movieModel.getCast();
        String category = movieModel.getCategory();

        holder.title.setText(title);
        holder.cast.setText(cast);
        holder.category.setText(category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userClickListener.selectedUser(movieModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView title, cast, category;
        public MovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            cast = itemView.findViewById(R.id.castTv);
            category = itemView.findViewById(R.id.categoryTv);
        }
    }
}
