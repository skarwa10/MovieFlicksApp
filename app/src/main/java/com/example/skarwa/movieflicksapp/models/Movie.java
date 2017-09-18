package com.example.skarwa.movieflicksapp.models;

import com.example.skarwa.movieflicksapp.utils.MovieListUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by skarwa on 9/14/17.
 */

public class Movie implements Serializable{
    private String posterPath;
    private String originalTitle;
    private String overview;
    private String backdropPath;
    private Double popularity;
    private Float voteAverage;
    private String releaseDate;


    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString(MovieListUtils.POSTER_PATH);
        this.originalTitle = jsonObject.getString(MovieListUtils.ORIGINAL_TITLE);
        this.overview = jsonObject.getString(MovieListUtils.OVERVIEW);
        this.backdropPath = jsonObject.getString(MovieListUtils.BACKDROP_PATH);
        this.popularity = jsonObject.getDouble(MovieListUtils.POPULARITY);
        this.releaseDate =jsonObject.getString(MovieListUtils.RELEASE_DATE);
        this.voteAverage = BigDecimal.valueOf(jsonObject.getDouble(MovieListUtils.VOTE_AVG)).floatValue();
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

    public Double getPopularity() {
        return popularity;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
