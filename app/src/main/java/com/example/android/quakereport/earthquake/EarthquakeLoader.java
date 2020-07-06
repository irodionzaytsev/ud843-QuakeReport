package com.example.android.quakereport.earthquake;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.quakereport.QueryUtils;

import java.io.IOException;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private final String LOG_TAG = "EarthquakeLoader";
    String url;

    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        try {
            return QueryUtils.extractEarthquakes(QueryUtils.makeHttpRequest(url));
        } catch (IOException e) {
            Log.e(LOG_TAG, "Something went wrong while retrieving earthquake data");
            return null;
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
