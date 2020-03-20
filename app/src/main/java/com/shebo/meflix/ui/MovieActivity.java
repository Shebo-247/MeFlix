package com.shebo.meflix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubePlayer;
import com.joooonho.SelectableRoundedImageView;
import com.shebo.meflix.R;
import com.shebo.meflix.YoutubePlayer;
import com.shebo.meflix.models.Cast;
import com.shebo.meflix.models.Country;
import com.shebo.meflix.models.Genre;
import com.shebo.meflix.models.Movie;
import com.shebo.meflix.models.Trailer;
import com.shebo.meflix.network.MovieRepository;
import com.shebo.meflix.network.OnGetCastCallback;
import com.shebo.meflix.network.OnGetCountryCallback;
import com.shebo.meflix.network.OnGetGenresCallback;
import com.shebo.meflix.network.OnGetMovieCallback;
import com.shebo.meflix.network.OnGetTrailersCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    private static String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%s";
    private static String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";

    public static String MOVIE_ID = "movie_id";

    MovieRepository movieRepository;
    private int movieID;

    private SelectableRoundedImageView movieDetailsPoster;
    private TextView movieDetailsTitle, movieDetailsGenre, movieDetailsYear, movieDetailsCountry,
            movieDetailsLength, movieDetailsOverview;
    private RatingBar movieDetailsRating;
    LinearLayout movieDetailsTrailers, movieDetailsCast;
    private FloatingActionButton playVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieID = getIntent().getIntExtra(MOVIE_ID, movieID);
        movieRepository = MovieRepository.getInstance();

        initUI();

        getMovieDetails();

        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, YoutubePlayer.class);
                startActivity(intent);
            }
        });

    }

    private void getMovieDetails() {
        movieRepository.getMovie(movieID, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Movie movie) {
                Picasso.get().load(IMAGE_BASE_URL + movie.getBackdropPath()).into(movieDetailsPoster);
                movieDetailsTitle.setText(movie.getTitle());
                getGenres(movie);
                getProductionCountries(movie);
                movieDetailsYear.setText(movie.getReleaseDate());
                movieDetailsOverview.setText(movie.getOverview());
                movieDetailsRating.setRating(movie.getRating() / 2);
                movieDetailsLength.setText(movie.getRunTime() + " min");
                getTrailers(movie);
                getCast(movie);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void getGenres(final Movie movie){
        movieRepository.getGenres(new OnGetGenresCallback() {
            @Override
            public void onSuccess(List<Genre> genres) {
                List<String> currentGenres = new ArrayList<>();
                for (Genre genre : movie.getGenres()){
                    currentGenres.add(genre.getName());
                }

                movieDetailsGenre.setText(TextUtils.join(" | ", currentGenres));
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getProductionCountries(Movie movie){
        movieRepository.getProductionCountries(movie.getId(), new OnGetCountryCallback() {
            @Override
            public void onSuccess(List<Country> countries) {
                List<String> currentCountries = new ArrayList<>();
                for (Country country : countries){
                    currentCountries.add(country.getProductionCountry());
                }

                movieDetailsCountry.setText(TextUtils.join(", ", currentCountries));
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getTrailers(final Movie movie){
        movieRepository.getTrailers(movie.getId(), new OnGetTrailersCallback() {
            @Override
            public void onSuccess(List<Trailer> trailers) {
                movieDetailsTrailers.removeAllViews();
                for (final Trailer trailer : trailers){
                    View parent = getLayoutInflater().inflate(R.layout.trailer_thumbnail, movieDetailsTrailers, false);
                    ImageView thumbnail = parent.findViewById(R.id.trailer_thumb);
                    thumbnail.requestLayout();
                    Picasso.get().load(String.format(YOUTUBE_THUMBNAIL_URL, trailer.getKey())).into(thumbnail);
                    thumbnail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showTrailer(String.format(YOUTUBE_VIDEO_URL, trailer.getKey()));
                        }
                    });
                    movieDetailsTrailers.addView(parent);
                }
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void getCast(Movie movie){
        movieRepository.getCast(movie.getId(), new OnGetCastCallback() {
            @Override
            public void onSuccess(List<Cast> casts) {
                movieDetailsCast.removeAllViews();
                for (Cast cast : casts){
                    View parent = getLayoutInflater().inflate(R.layout.cast_card, movieDetailsCast, false);
                    ImageView castProfile = parent.findViewById(R.id.cast_profile);
                    TextView castName = parent.findViewById(R.id.cast_name);

                    castProfile.requestLayout();
                    castName.requestLayout();

                    Picasso.get().load(IMAGE_BASE_URL + cast.getProfilePath()).into(castProfile);
                    castName.setText(cast.getName());

                    movieDetailsCast.addView(parent);
                }
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void showTrailer(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void showError() {
        Toast.makeText(this, "Please make sure you are connected to internet", Toast.LENGTH_SHORT).show();
    }

    private void initUI() {
        movieDetailsPoster = findViewById(R.id.movie_details_poster);
        movieDetailsTitle = findViewById(R.id.movie_details_title);
        movieDetailsGenre = findViewById(R.id.movie_details_genre);
        movieDetailsYear = findViewById(R.id.movie_details_year);
        movieDetailsCountry = findViewById(R.id.movie_details_country);
        movieDetailsLength = findViewById(R.id.movie_details_length);
        movieDetailsOverview = findViewById(R.id.movie_details_overview);
        movieDetailsRating = findViewById(R.id.movie_details_rating);
        movieDetailsTrailers = findViewById(R.id.movie_details_trailers);
        movieDetailsCast = findViewById(R.id.movie_details_cast);
        playVideo = findViewById(R.id.play);
    }
}
