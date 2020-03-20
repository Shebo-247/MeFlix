package com.shebo.meflix.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.shebo.meflix.R;
import com.shebo.meflix.adapters.MoviesRecyclerAdapter;
import com.shebo.meflix.models.Movie;
import com.shebo.meflix.network.MovieRepository;
import com.shebo.meflix.network.OnGetMoviesCallback;
import com.shebo.meflix.network.OnMovieClickCallback;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DiscreteScrollView nowPlayingMovies;
    private RecyclerView popularMovies, topRatedMovies, upComingMovies;

    private MoviesRecyclerAdapter nowPlayingMoviesAdapter, popularMoviesAdapter, topRatedMoviesAdapter, upComingMoviesAdapter;

    private MovieRepository movieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRepository = MovieRepository.getInstance();

        initUI();

        getSliderMovies();
        getPopularMovies();
        getTopRatedMovies();
        getUpComingMovies();
    }

    private void getPopularMovies() {
        movieRepository.getMovies(MovieRepository.POPULAR, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                popularMoviesAdapter = new MoviesRecyclerAdapter(movies, "recycler", callback);
                popularMovies.setAdapter(popularMoviesAdapter);
                new LinearSnapHelper().attachToRecyclerView(popularMovies);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getTopRatedMovies() {
        movieRepository.getMovies(MovieRepository.TOP_RATED, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                topRatedMoviesAdapter = new MoviesRecyclerAdapter(movies, "recycler", callback);
                topRatedMovies.setAdapter(topRatedMoviesAdapter);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getUpComingMovies() {
        movieRepository.getMovies(MovieRepository.UP_COMING, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                upComingMoviesAdapter = new MoviesRecyclerAdapter(movies, "recycler", callback);
                upComingMovies.setAdapter(upComingMoviesAdapter);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getSliderMovies() {
        movieRepository.getMovies(MovieRepository.NOW_PLAYING, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                nowPlayingMoviesAdapter = new MoviesRecyclerAdapter(movies, "slider", callback);
                nowPlayingMovies.setAdapter(nowPlayingMoviesAdapter);

                new LinearSnapHelper().attachToRecyclerView(nowPlayingMovies);

                nowPlayingMovies.setItemTransformer(new ScaleTransformer.Builder()
                    .setMaxScale(1.0f)
                    .setMinScale(0.8f)
                    .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                    .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                    .build());
            }

            @Override
            public void onError() {

            }
        });
    }

    OnMovieClickCallback callback = new OnMovieClickCallback() {
        @Override
        public void onClick(Movie movie) {
            Intent movieIntent = new Intent(MainActivity.this, MovieActivity.class);
            movieIntent.putExtra(MovieActivity.MOVIE_ID, movie.getId());
            startActivity(movieIntent);
        }
    };

    private void initUI() {
        nowPlayingMovies = findViewById(R.id.now_playing_movies);
        popularMovies = findViewById(R.id.popular_movies);
        topRatedMovies = findViewById(R.id.top_rated_movies);
        upComingMovies = findViewById(R.id.upcoming_movies);

        //nowPlayingMovies.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        popularMovies.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        topRatedMovies.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        upComingMovies.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }
}
