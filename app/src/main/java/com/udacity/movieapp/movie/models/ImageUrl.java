package com.udacity.movieapp.movie.models;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marceloderezendemartins on 11/15/16.
 */

public class ImageUrl {

    private String baseUrl;
    private String size;
    private String detailSize;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }



    public static ImageUrl fromJson(JSONObject jsonObject) throws JSONException {
        JSONObject imageJson = jsonObject.getJSONObject("images");

        String secureBaseUrl = imageJson.getString("secure_base_url");
        JSONArray posterSizes = imageJson.getJSONArray("poster_sizes");
        String posterSize = null;
        String detailSize = null;
        if(posterSizes.length() > 3) {
            posterSize = posterSizes.getString(posterSizes.length() - 3); //w500
            detailSize = posterSizes.getString(posterSizes.length() - 2); //w780
        }


        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setBaseUrl(secureBaseUrl);
        imageUrl.setSize(posterSize);
        imageUrl.setDetailSize(detailSize);

        return imageUrl;
    }

    public String getUrl(String path) {
        Uri uri = Uri.parse(this.getBaseUrl()).buildUpon()
                .appendPath(this.getSize())
                .appendPath(path.replace("/", "")).build();
        return uri.toString();
    }

    public String getDetailUrl(String path) {
        Uri uri = Uri.parse(this.getBaseUrl()).buildUpon()
                .appendPath(this.getDetailSize())
                .appendPath(path.replace("/", "")).build();
        return uri.toString();
    }

    public String getDetailSize() {
        return detailSize;
    }

    public void setDetailSize(String detailSize) {
        this.detailSize = detailSize;
    }
}
