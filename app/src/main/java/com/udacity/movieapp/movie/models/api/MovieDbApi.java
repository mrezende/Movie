package com.udacity.movieapp.movie.models.api;

import android.net.Uri;

import com.udacity.movieapp.movie.BuildConfig;

/**
 * Created by marceloderezendemartins on 11/27/16.
 */

public class MovieDbApi {

    private static final String DISCOVER_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String API_TOKEN = BuildConfig.MOVIE_DB_API_TOKEN;
    public static final String API_KEY = "api_key";
    public static final String LANGUAGE = "language";
    public static final String SORT_BY = "sort_by";
    public static final String INCLUDE_ADULT = "include_adult";
    public static final String INCLUDE_VIDEO = "include_video";
    public static final String PAGE = "page";
    public static final String CONFIGURATION_URL = "https://api.themoviedb.org/3/configuration";


    public static String getDiscoverUrl(String sortBy) {
        String url = Uri.parse(DISCOVER_URL).buildUpon()
                .appendQueryParameter(API_KEY, API_TOKEN)
                .appendQueryParameter(LANGUAGE, "en-US")
                .appendQueryParameter(SORT_BY, sortBy)
                .appendQueryParameter(INCLUDE_ADULT, "true")
                .appendQueryParameter(INCLUDE_VIDEO, "true")
                .appendQueryParameter(PAGE, "1").build().toString();
        return url;
    }

    public static String getConfigurationUrl() {
        String url = Uri.parse(CONFIGURATION_URL).buildUpon().appendQueryParameter(API_KEY, API_TOKEN).build().toString();
        return url;
    }





}
