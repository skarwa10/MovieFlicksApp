package com.example.skarwa.movieflicksapp.adapters;

import android.content.Context;
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

import static com.example.skarwa.movieflicksapp.R.id.tvOverview;
import static com.example.skarwa.movieflicksapp.R.id.tvTitle;

/**
 * Created by skarwa on 9/14/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView image;
    }


    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            //TODO: understand this (false -> dont actually attach it..make changes myself)
            convertView = inflater.inflate(R.layout.item_movie,parent,false);

            viewHolder.title = (TextView) convertView.findViewById(tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(tvOverview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivMovieImage);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //clear out image from convertView
        viewHolder.image.setImageResource(0);

        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.image);

        return convertView;
    }
}
