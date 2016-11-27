package com.udacity.movieapp.movie.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marceloderezendemartins on 11/17/16.
 */

public class RequestHelper {

    static public void makeJsonObjectRequest(Context context, int method, String url, final VolleyResponseListener<JSONObject> volleyResponseListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                volleyResponseListener.onResponse(response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    JSONObject errorJson = new JSONObject(error.toString());
                    volleyResponseListener.onError(errorJson);
                    VolleyLog.e("Error: ", error.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    static public void makeJsonArrayRequest(Context context, int method, String url, final VolleyResponseListener<JSONArray> volleyResponseListener) {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                volleyResponseListener.onResponse(response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                try {

                    JSONArray errorJson = new JSONArray("[" + error.toString() + "]");
                    volleyResponseListener.onError(errorJson);
                    VolleyLog.e("Error: ", error.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

}
