package com.udacity.movieapp.movie.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marceloderezendemartins on 11/6/16.
 */

public class Movie {

    private String title;
    private String imageThumbnailPath;
    private String synopsis;
    private double rating;
    private String releaseDate;
    private String url;
    private String detailUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageThumbnailPath() {
        return imageThumbnailPath;
    }

    public void setImageThumbnailPath(String imageThumbnailPath) {
        this.imageThumbnailPath = imageThumbnailPath;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

        /*
        {
            "poster_path": "/xfWac8MTYDxujaxgPVcRD9yZaul.jpg",
            "adult": false,
            "overview": "After his career is destroyed, a brilliant but arrogant surgeon gets a new lease on life when a sorcerer takes him under his wing and trains him to defend the world against evil.",
            "release_date": "2016-10-25",
            "genre_ids": [
                28,
                12,
                14,
                878
            ],
            "id": 284052,
            "original_title": "Doctor Strange",
            "original_language": "en",
            "title": "Doctor Strange",
            "backdrop_path": "/tFI8VLMgSTTU38i8TIsklfqS9Nl.jpg",
            "popularity": 53.239914,
            "vote_count": 823,
            "video": false,
            "vote_average": 6.88
        },
         */

    public static Movie fromJson(JSONObject jsonObject, ImageUrl imageUrl) {
        Movie movie = new Movie();
        try {
            String title = jsonObject.getString("title");
            movie.setTitle(title);

            String posterPath = jsonObject.getString("poster_path");
            movie.setImageThumbnailPath(posterPath);

            movie.setUrl(imageUrl.getUrl(posterPath));

            movie.setDetailUrl(imageUrl.getDetailUrl(posterPath));

            String synopsis = jsonObject.getString("overview");
            movie.setSynopsis(synopsis);

            double rating = jsonObject.getDouble("vote_average");
            movie.setRating(rating);

            String releaseDate = jsonObject.getString("release_date");
            movie.setReleaseDate(releaseDate);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return movie;
    }
    public static List<Movie> fromJson(JSONArray jsonArray, ImageUrl imageUrl) {
        // /discover/movie?sort_by=popularity.desc

        List<Movie> movies = new ArrayList<>(jsonArray.length());

        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject movieJson = jsonArray.getJSONObject(i);
                Movie movie = Movie.fromJson(movieJson, imageUrl);
                movies.add(movie);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return movies;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();

        bundle.putString("title", this.getTitle());
        bundle.putString("synopsis", this.getSynopsis());
        bundle.putString("releaseDate", this.getReleaseDate());
        bundle.putString("url", this.getUrl());
        bundle.putString("detailUrl", this.getDetailUrl());
        bundle.putDouble("rating", this.getRating());

        return bundle;
    }

    public static Movie fromBundle(Bundle bundle) {
        Movie movie = new Movie();

        movie.setTitle(bundle.getString("title"));
        movie.setSynopsis(bundle.getString("synopsis"));
        movie.setRating(bundle.getDouble("rating"));
        movie.setUrl(bundle.getString("url"));
        movie.setDetailUrl(bundle.getString("detailUrl"));
        movie.setReleaseDate(bundle.getString("releaseDate"));

        return movie;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
