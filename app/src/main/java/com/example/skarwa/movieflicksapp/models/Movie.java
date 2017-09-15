package com.example.skarwa.movieflicksapp.models;

import com.example.skarwa.movieflicksapp.utils.MovieListUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by skarwa on 9/14/17.
 */

public class Movie {
    private String posterPath;
    private String originalTitle;
    private String overview;
    private String backdropPath;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString(MovieListUtils.POSTER_PATH);
        this.originalTitle = jsonObject.getString(MovieListUtils.ORIGINAL_TITLE);
        this.overview = jsonObject.getString(MovieListUtils.OVERVIEW);
        this.backdropPath = jsonObject.getString(MovieListUtils.BACKDROP_PATH);
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();

        for(int i = 0;i < array.length();i++){
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getPosterPath() {
        return String.format(MovieListUtils.IMAGE_PATH_URL,posterPath);
    }

    public String getBackdropPath() {
        return String.format(MovieListUtils.IMAGE_PATH_URL,backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }
}
