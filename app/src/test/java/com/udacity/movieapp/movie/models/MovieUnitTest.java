package com.udacity.movieapp.movie.models;

import android.net.Uri;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Injectable;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * Created by marceloderezendemartins on 11/27/16.
 */

@RunWith(JMockit.class)
public class MovieUnitTest {
    private String movieJsonString;

    @Before
    public void initialize() {
        movieJsonString = "{" +
                "'poster_path': '/xfWac8MTYDxujaxgPVcRD9yZaul.jpg'," +
                "'adult': false," +
                "'overview': 'After his career is destroyed, a brilliant but arrogant surgeon gets a new lease on life when a sorcerer takes him under his wing and trains him to defend the world against evil.'," +
                "'release_date': '2016-10-25'," +
                "'genre_ids': [" +
                "28," +
                "12,"+
                "14,"+
                "878"+
                "]," +
                "'id': 284052," +
                "'original_title': 'Doctor Strange'," +
                "'original_language': 'en'," +
                "'title': 'Doctor Strange'," +
                "'backdrop_path': '/tFI8VLMgSTTU38i8TIsklfqS9Nl.jpg',"+
                "'popularity': 53.239914," +
                "'vote_count': 823,"+
                "'video': false,"+
                "'vote_average': 6.88" +
                "}";
    }

    @Test
    public void fromJson_getTitle_isCorrect(@Mocked final Uri uri, @Injectable ImageUrl stub) {

        try {
            JSONObject movieJson = new JSONObject(movieJsonString);

            Movie movie = Movie.fromJson(movieJson, stub);

            Assert.assertEquals(movie.getTitle(), "Doctor Strange");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    
}
