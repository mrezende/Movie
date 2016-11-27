package com.udacity.movieapp.movie;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.movieapp.movie.databinding.FragmentDetailBinding;
import com.udacity.movieapp.movie.models.Movie;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentDetailBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        View view = dataBinding.getRoot();

        Bundle arguments = getArguments();
        Movie movie = Movie.fromBundle(arguments);

        dataBinding.setMovie(movie);
        return view;
    }


}
