package com.shebo.meflix.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shebo.meflix.R;
import com.shebo.meflix.models.Movie;
import com.shebo.meflix.network.OnMovieClickCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.MovieViewHolder> {

    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    private List<Movie> allMovies;
    private String type;
    private OnMovieClickCallback callback;

    private int currentItemID = 0;

    public MoviesRecyclerAdapter(List<Movie> allMovies, String type, OnMovieClickCallback callback){
        this.allMovies = allMovies;
        this.type = type;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MoviesRecyclerAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (type.equals("slider")){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
            return new MovieViewHolder(view);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new MovieViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MoviesRecyclerAdapter.MovieViewHolder holder, int position) {
        holder.bind(allMovies.get(position));
        currentItemID = position;
    }

    @Override
    public int getItemCount() {
        return allMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView recyclerItemPoster;
        ImageView sliderMoviePoster;
        TextView sliderMovieTitle;

        Movie movie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerItemPoster = itemView.findViewById(R.id.recycler_movie_poster);
            sliderMoviePoster = itemView.findViewById(R.id.slider_movie_poster);
            sliderMovieTitle = itemView.findViewById(R.id.slider_movie_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(movie);
                }
            });
        }

        public void bind(Movie movie){
            this.movie = movie;

            if (type.equals("slider")){
                Picasso.get().load(IMAGE_BASE_URL + movie.getPosterPath()).into(sliderMoviePoster);
                sliderMovieTitle.setText(movie.getTitle());
            }
            else{
                Picasso.get().load(IMAGE_BASE_URL + movie.getPosterPath()).into(recyclerItemPoster);
            }
        }
    }
}
