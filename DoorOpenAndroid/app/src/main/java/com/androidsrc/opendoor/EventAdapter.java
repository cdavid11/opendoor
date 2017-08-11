package com.androidsrc.opendoor;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Forbin on 7/25/2017.
 */

public class EventAdapter extends ArrayAdapter<Event>{

    Context context;
    int layoutResourceId;
    ArrayList<Event> events = null;

    public EventAdapter (Context context, int layoutResourceId, ArrayList<Event> events){

        super(context, layoutResourceId, events);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.events = events;

    }

    public View getView (int position, View convertView, ViewGroup parent) {

        View row = convertView;
        EventHolder holder = null;

        if (row == null){

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EventHolder();
            holder.time = (TextView)row.findViewById(R.id.time);
            row.setTag(holder);
        }else{

            holder = (EventHolder)row.getTag();
        }

        final Event event = events.get(position);
        holder.time.setText(event.time);


        return row;
    }

    static class EventHolder {

        TextView time;

    }
}
