package com.example.android.quakereport.earthquake;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";
    private static final String MAG_FORMAT = "0.0";
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Earthquake earthquake = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_listview_item, parent, false);
        }
        final String URL = earthquake.getURL();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));

            }
        });
        TextView magView = (TextView) convertView.findViewById(R.id.mag_textview);
        TextView primaryView = (TextView) convertView.findViewById(R.id.primary_textview);
        TextView offsetView = (TextView) convertView.findViewById(R.id.offset_textview);
        TextView dateView = (TextView) convertView.findViewById(R.id.date_textview);
        TextView timeView = (TextView) convertView.findViewById(R.id.time_textview);

        DecimalFormat formatter = new DecimalFormat(MAG_FORMAT);
        magView.setText(formatter.format(earthquake.getMag()));
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();
        int magnitudeColor = getMagnitudeColor(earthquake.getMag());
        magnitudeCircle.setColor(magnitudeColor);


        dateView.setText(earthquake.getDate());
        timeView.setText(earthquake.getTime());

        String originalLocation = earthquake.getPlace();
        String primaryLocation;
        String locationOffset;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        offsetView.setText(locationOffset);
        primaryView.setText(primaryLocation);
        return convertView;
    }
    private int getMagnitudeColor(double magnitude) {
        int mag = (int) magnitude;
        int colorId;
        switch (mag) {
            case 0:
            case 1:
                colorId = R.color.magnitude1;
                break;
            case 2:
                colorId = R.color.magnitude2;
                break;
            case 3:
                colorId = R.color.magnitude3;
                break;
            case 4:
                colorId = R.color.magnitude4;
                break;
            case 5:
                colorId = R.color.magnitude5;
                break;
            case 6:
                colorId = R.color.magnitude6;
                break;
            case 7:
                colorId = R.color.magnitude7;
                break;
            case 8:
                colorId = R.color.magnitude8;
                break;
            case 9:
                colorId = R.color.magnitude9;
                break;
            default:
                colorId = R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getContext(), colorId);
    }
}
