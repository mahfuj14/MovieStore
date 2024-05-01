package com.example.moviestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteAdapterViewHolder> {

    private List<MovieModel> movieModelList = new ArrayList<>();
    public Context context;
    public UserClickListener userClickListener;

    public FavouriteAdapter(List<MovieModel> movieModelList, Context context, UserClickListener userClickListener) {
        this.movieModelList = movieModelList;
        this.context = context;
        this.userClickListener = userClickListener;
    }
    public interface UserClickListener{
        void selectedUser(MovieModel movieModel);
    }

    @NonNull
    @Override
    public FavouriteAdapter.FavouriteAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_row, parent, false);
        return new FavouriteAdapter.FavouriteAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.FavouriteAdapterViewHolder holder, int position) {
        MovieModel movieModel = movieModelList.get(position);
        String title = movieModel.getTitle();
        String cast = movieModel.getCast();
        String category = movieModel.getCategory();

        holder.title.setText(title);
        holder.cast.setText(cast);
        holder.category.setText(category);
        holder.imageView.setImageResource(movieModel.getImageUrl());
        holder.favToggle.setChecked(movieModel.isToogle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userClickListener.selectedUser(movieModel);
            }
        });


        holder.favToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = holder.favToggle.isChecked();
                if(!isChecked){
                    movieModel.setToogle(false);
                    movieModelList.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public class FavouriteAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView title, cast, category;
        ImageView imageView;
        ToggleButton favToggle;
        public FavouriteAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            cast = itemView.findViewById(R.id.castTv);
            category = itemView.findViewById(R.id.categoryTv);
            imageView = itemView.findViewById(R.id.imageView);
            favToggle = itemView.findViewById(R.id.favToggleBtn);
        }
    }


}
