package com.kwang0.tutorialapp.network.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kwang0.tutorialapp.network.entities.Weather;

import org.json.JSONObject;

public class WeatherRepositoryVolley implements WeatherRepository {
    public static final String TAG = WeatherRepositoryVolley.class.getSimpleName();

    RequestQueue queue;

    public WeatherRepositoryVolley(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void getWeather(final RequestCallback callback) {
        final JsonObjectRequest request =
                new JsonObjectRequest(uri.toString(), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "result: " + response.toString());
                        final Weather weather = new Gson().fromJson(response.toString(), Weather.class);
                        callback.success(weather);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.error(error);
                    }
                });
        queue.add(request);

    }
}