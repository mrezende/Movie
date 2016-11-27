package com.udacity.movieapp.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.udacity.movieapp.movie.models.Movie;
import com.udacity.movieapp.movie.utils.MySingleton;

import java.util.ArrayList;

/**
 * Created by marceloderezendemartins on 11/6/16.
 */



public class MovieAdapter extends ArrayAdapter<Movie> {

    static class ViewHolder {
        NetworkImageView imageView;
    }
    public MovieAdapter(Context context, ArrayList<Movie> users) {
        super(context, R.layout.list_item_movie, users);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        Movie item = (Movie) getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie, parent, false);
            viewHolder.imageView = (NetworkImageView) convertView.findViewById(R.id.movie_thumb);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageLoader imageLoader = MySingleton.getInstance(getContext()).getImageLoader();
        viewHolder.imageView.setImageUrl(item.getUrl(), imageLoader);


        return convertView;
    }
}
