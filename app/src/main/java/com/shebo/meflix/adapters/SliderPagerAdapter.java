package com.shebo.meflix.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.shebo.meflix.R;
import com.shebo.meflix.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {

    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    Context context;
    List<Movie> movies;

    public SliderPagerAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider_item, container, false);

        ImageView sliderMoviePoster = view.findViewById(R.id.slider_movie_poster);
        TextView sliderMovieTitle = view.findViewById(R.id.slider_movie_title);

        Picasso.get().load(IMAGE_BASE_URL + movies.get(position).getPosterPath()).into(sliderMoviePoster);
        sliderMovieTitle.setText(movies.get(position).getTitle());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
