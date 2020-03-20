package com.shebo.meflix.network;

import com.shebo.meflix.models.CastResponse;
import com.shebo.meflix.models.CountryResponse;
import com.shebo.meflix.models.Genre;
import com.shebo.meflix.models.GenreResponse;
import com.shebo.meflix.models.Movie;
import com.shebo.meflix.models.MovieResponse;
import com.shebo.meflix.models.Trailer;
import com.shebo.meflix.models.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDbApi {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(
            @Query("page") int page,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(
            @Query("page") int page,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(
            @Query("page") int page,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/upcoming")
    Call<MovieResponse> getUpComingMovies(
            @Query("page") int page,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailers(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}")
    Call<CountryResponse> getCountries(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/credits")
    Call<CastResponse> getCast(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

}
