package com.udacity.movieapp.movie.utils;

import android.databinding.BindingAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by marceloderezendemartins on 11/20/16.
 */

public class BindingAdapterUtils {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(NetworkImageView view, String url) {
        ImageLoader imageLoader = MySingleton.getInstance(view.getContext()).getImageLoader();
        view.setImageUrl(url, imageLoader);

    }
}
