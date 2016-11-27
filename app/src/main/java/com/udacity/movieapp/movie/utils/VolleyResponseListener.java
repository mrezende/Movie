package com.udacity.movieapp.movie.utils;

/**
 * Created by marceloderezendemartins on 11/17/16.
 */

public interface VolleyResponseListener<T> {

    public void onResponse(T t);
    public void onError(T t);

}
