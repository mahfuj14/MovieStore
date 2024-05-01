package com.example.moviestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> implements Filterable{

    public List<MovieModel> movieModelList = new ArrayList<>();
    public List<MovieModel> getMovieModelListFilter = new ArrayList<>();
    public Context context;
    public  UserClickListener userClickListener;
    String spinType;

    public MovieAdapter(List<MovieModel> movieModelList, Context context, String spinType) {
        this.movieModelList = movieModelList;
        this.context = context;
        this.spinType = spinType;
    }



    public MovieAdapter(List<MovieModel>movieModelList, Context context, UserClickListener userClickListener){
        this.movieModelList=movieModelList;
        this.context = context;
        this.getMovieModelListFilter = movieModelList;
        this.userClickListener= userClickListener;
    }



    public interface UserClickListener{
        void selectedUser(MovieModel movieModel);
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
        holder.imageView.setImageResource(movieModel.getImageUrl());
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
                if(isChecked){
                    movieModel.setToogle(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView title, cast, category;
        ImageView imageView;
        ToggleButton favToggle;
        public MovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            cast = itemView.findViewById(R.id.castTv);
            category = itemView.findViewById(R.id.categoryTv);
            imageView = itemView.findViewById(R.id.imageView);
            favToggle = itemView.findViewById(R.id.favToggleBtn);
        }
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                char lastchar = constraint.charAt(constraint.length()-1);
                constraint = constraint.subSequence(0, constraint.length()-1);
                constraint = constraint.toString().toLowerCase();
                FilterResults filterResults = new FilterResults();
                if(constraint==null|| constraint.length()==0){
                    filterResults.values =  getMovieModelListFilter;
                    filterResults.count = getMovieModelListFilter.size();
                }
                else{
                    String searchString = constraint.toString().toLowerCase();
                    List<MovieModel> filteredList = new ArrayList<>();
                    for(MovieModel movieModel:getMovieModelListFilter){
                        if(lastchar=='1'){
                            if(movieModel.getTitle().toLowerCase().contains(constraint)){
                                filteredList.add(movieModel);
                            }
                        } else if (lastchar=='2') {
                            if(movieModel.getCast().toLowerCase().contains(constraint)){
                                filteredList.add(movieModel);
                            }
                        } else if (lastchar=='3') {
                            if(movieModel.getCategory().toLowerCase().contains(constraint)){
                                filteredList.add(movieModel);
                            }
                        }

                    }
                    filterResults.values = filteredList;
                    filterResults.count = filteredList.size();
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                movieModelList = (List<MovieModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
