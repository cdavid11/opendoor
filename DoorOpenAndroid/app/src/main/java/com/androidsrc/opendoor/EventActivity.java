package com.androidsrc.opendoor;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventActivity extends AppCompatActivity {

    ArrayList<Event> events = new ArrayList<Event>();
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final EventAdapter adapter = new EventAdapter (this, R.layout.event_item_row, events);


        final String url = "";

        ListView listView = (ListView) findViewById(R.id.list);

        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //LinearLayout ll = (LinearLayout)layoutInflater.inflate(R.layout.event_header_row,null,false);
        //listView.addHeaderView(ll);

        listView.setAdapter(adapter);

        get_new_events(url, adapter);

        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                events.clear();
                get_new_events(url,adapter);
                swipeContainer.setRefreshing(false);
            }
            }
        );

    }

    public void get_new_events(String url, EventAdapter adapter){

        final EventAdapter event_adapter = adapter;
        final RequestQueue queue = VolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        JsonArrayRequest jsonRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response){
                try {
                    Log.d("Response", response.toString());
                    for (int x = 0; x < response.length(); x++) {

                        Log.d("Individual response: ", response.getJSONObject(x).getString("time"));
                        Event event = new Event();
                        event.time = response.getJSONObject(x).getString("time");
                        events.add(0, event);
                        event_adapter.notifyDataSetChanged();
                    }
                }
                catch(JSONException e) {e.printStackTrace();}
            }
        },
        new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });

        VolleyRequestQueue.getInstance(this).addToRequestQueue(jsonRequest);
    }
}
