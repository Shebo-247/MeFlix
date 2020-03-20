package com.shebo.meflix.network;

import com.shebo.meflix.models.CastResponse;
import com.shebo.meflix.models.CountryResponse;
import com.shebo.meflix.models.GenreResponse;
import com.shebo.meflix.models.Movie;
import com.shebo.meflix.models.MovieResponse;
import com.shebo.meflix.models.Trailer;
import com.shebo.meflix.models.TrailerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String LANGUAGE = "en-US";
    public static final String API_KEY = "8eba28c739e0632d1ebfe7fc585aafe6";
    public static final String NOW_PLAYING = "now_playing";
    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    public static final String UP_COMING = "upcoming";

    private static MovieRepository movieRepository;
    private TMDbApi api;

    public MovieRepository(TMDbApi api) {
        this.api = api;
    }

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            movieRepository = new MovieRepository(retrofit.create(TMDbApi.class));
        }

        return movieRepository;
    }

    public void getMovies(String type, final OnGetMoviesCallback callback) {

        Callback<MovieResponse> moviesCallback = new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null && movieResponse.getMovies() != null) {
                        callback.onSuccess(movieResponse.getMovies());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onError();
            }
        };

        switch (type) {
            case NOW_PLAYING:
                api.getNowPlayingMovies(1, API_KEY, LANGUAGE)
                        .enqueue(moviesCallback);
                break;

            case POPULAR:
                api.getPopularMovies(1, API_KEY, LANGUAGE)
                        .enqueue(moviesCallback);
                break;

            case TOP_RATED:
                api.getTopRatedMovies(1, API_KEY, LANGUAGE)
                        .enqueue(moviesCallback);
                break;

            case UP_COMING:
                api.getUpComingMovies(1, API_KEY, LANGUAGE)
                        .enqueue(moviesCallback);
                break;
        }
    }

    public void getMovie(int movieID, final OnGetMovieCallback callback) {
        api.getMovie(movieID, API_KEY, LANGUAGE)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            Movie movie = response.body();
                            if (movie != null) {
                                callback.onSuccess(movie);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });
    }

    public void getGenres(final OnGetGenresCallback callback) {
        api.getGenres(API_KEY, LANGUAGE)
                .enqueue(new Callback<GenreResponse>() {
                    @Override
                    public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                        if (response.isSuccessful()) {
                            GenreResponse genreResponse = response.body();
                            if (genreResponse != null && genreResponse.getGenres() != null) {
                                callback.onSuccess(genreResponse.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenreResponse> call, Throwable t) {

                    }
                });
    }

    public void getTrailers(int movieID, final OnGetTrailersCallback callback) {
        api.getTrailers(movieID, API_KEY, LANGUAGE)
                .enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            TrailerResponse trailerResponse = response.body();
                            if (trailerResponse != null && trailerResponse.getTrailers() != null) {
                                callback.onSuccess(trailerResponse.getTrailers());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getProductionCountries(int movieID, final OnGetCountryCallback callback) {
        api.getCountries(movieID, API_KEY, LANGUAGE)
                .enqueue(new Callback<CountryResponse>() {
                    @Override
                    public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                        if (response.isSuccessful()) {
                            CountryResponse countryResponse = response.body();
                            if (countryResponse != null && countryResponse.getCountries() != null) {
                                callback.onSuccess(countryResponse.getCountries());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<CountryResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getCast(int movieID, final OnGetCastCallback callback){
        api.getCast(movieID, API_KEY)
                .enqueue(new Callback<CastResponse>() {
                    @Override
                    public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                        if (response.isSuccessful()){
                            CastResponse castResponse = response.body();
                            if (castResponse != null && castResponse.getCast() != null){
                                callback.onSuccess(castResponse.getCast());
                            } else {
                                callback.onError();
                            }
                        }else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<CastResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

}
