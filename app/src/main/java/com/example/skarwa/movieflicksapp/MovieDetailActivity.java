package com.example.skarwa.movieflicksapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.skarwa.movieflicksapp.R;
import com.example.skarwa.movieflicksapp.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.skarwa.movieflicksapp.R.id.etReleaseDate;
import static com.example.skarwa.movieflicksapp.R.id.tvOverview;
import static com.example.skarwa.movieflicksapp.R.id.tvTitle;
import static java.security.AccessController.getContext;

/**
 * Activity to show additional movie details like Release Date , Popularity , Rating etc
 */
public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.ivMovieImage)
    ImageView image;
    @BindView(R.id.rbDetailRating)
    RatingBar ratingBar;
    @BindView(R.id.tvDetailTitle)
    TextView title;
    @BindView(R.id.tvDetailOverview)
    TextView overview;
    @BindView(etReleaseDate)
    EditText releaseDate;
    @BindView(R.id.etPopularity)
    TextView popularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        title.setText(movie.getOriginalTitle());
        overview.setText(movie.getOverview());
        popularity.setText(" Popularity : "+movie.getPopularity());
        releaseDate.setText(" Release Date : " + movie.getReleaseDate());
        ratingBar.setMax(5);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize((float)0.5);
        ratingBar.setRating((float) movie.getVoteAverage()/2);

       Picasso.with(this).load(getImageBasedOnOrientation(movie))
                 .resize(500,0)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.image_error)
                .noFade()
                .into(image);
    }

    /**
     * Get Image based on Orientation.
     * For Potrait -> we user poster_path image
     * For Landscape -> we will user backdrop_path image
     * @param movie
     * @return Image url
     */
    private String getImageBasedOnOrientation(Movie movie){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return  movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return movie.getBackdropPath();
        } else {
            return movie.getPosterPath();
        }
    }
}
