package com.udacity.movieapp.movie;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.udacity.movieapp.movie.models.ImageUrl;
import com.udacity.movieapp.movie.models.Movie;
import com.udacity.movieapp.movie.models.api.MovieDbApi;
import com.udacity.movieapp.movie.utils.RequestHelper;
import com.udacity.movieapp.movie.utils.VolleyResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GridFragment extends Fragment {

    MovieAdapter movieAdapter;

    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                Movie movie = (Movie)parent.getItemAtPosition(position);
                intent.putExtra("movie", movie.getBundle());

                startActivity(intent);
            }
        });

        movieAdapter = new MovieAdapter(getContext(), new ArrayList<Movie>());


        List<Movie> movies = new ArrayList<Movie>();


        movieAdapter.addAll(movies);


        gridView.setAdapter(movieAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    private void load() {

        String url = MovieDbApi.getConfigurationUrl();
        VolleyResponseListener<JSONObject> listener = new VolleyResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                // load images
                try {
                    ImageUrl imageUrl = ImageUrl.fromJson(jsonObject);
                    loadImages(imageUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(JSONObject jsonObject) {
                Log.e("Deu erro: ", jsonObject.toString());
            }
        };
        RequestHelper.makeJsonObjectRequest(getContext(), Request.Method.GET, url, listener);


    }

    private void loadImages(final ImageUrl imageUrl) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String sortBy = preferences.getString(getString(R.string.order_key), getString(R.string.order_default));
        String url = MovieDbApi.getDiscoverUrl(sortBy);


        VolleyResponseListener<JSONObject> listener = new VolleyResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    List<Movie> movies = Movie.fromJson(jsonObject.getJSONArray("results"), imageUrl);
                    movieAdapter.clear();
                    movieAdapter.addAll(movies);
                    movieAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(JSONObject jsonObject) {

            }
        };

        RequestHelper.makeJsonObjectRequest(getContext(), Request.Method.GET, url, listener);
    }


}
