package com.example.skarwa.movieflicksapp.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skarwa.movieflicksapp.R;
import com.example.skarwa.movieflicksapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by skarwa on 9/14/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    // View lookup cache
    static class ViewHolder {
        @BindView(R.id.tvTitle) TextView title;
        @BindView(R.id.tvOverview) TextView overview;
        @BindView(R.id.ivMovieImage) ImageView image;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //clear out image from convertView
        viewHolder.image.setImageResource(0);

        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        Picasso.with(getContext()).load(getImageBasedOnOrientation(movie))
                .resize(600,0)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.image_error)
                .noFade()
                .into(viewHolder.image);

        return convertView;
    }

    /**
     * Get Image based on Orientation.
     * For Potrait -> we user poster_path image
     * For Landscape -> we will user backdrop_path image
     * @param movie
     * @return Image url
     */
    private String getImageBasedOnOrientation(Movie movie){
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return  movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return movie.getBackdropPath();
        } else {
            return movie.getPosterPath();
        }
    }
}
