package com.example.android.quakereport.earthquake;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    private double mag;
    private String place;
    private long time;
    private Date date;
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final String TIME_FORMAT = "HH:mm:ss";
    private String URL;
    public Earthquake(double _mag, String _place, long _time, String _URL) {
        mag = _mag;
        time = _time;
        place = _place;
        date = new Date(time);
        URL = _URL;
    }
    double getMag() { return mag; }
    String getPlace() { return place; }
    String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
        return formatter.format(date);
    }
    String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(date);
    }
    String getURL() { return URL; }
}
