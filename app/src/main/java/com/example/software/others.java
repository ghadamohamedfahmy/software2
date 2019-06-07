package com.example.software;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class others extends AppCompatActivity implements  recycleviewfeed.OnReclyclerClickListener{
    private RecyclerView mRecyclerView;
    private feedadapter mfeedAdapter;
    private ArrayList<recycleviewfeed> recycleview;

    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luxor);

        mRecyclerView = findViewById(R.id.recycleluxor);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleview = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        List<String> categories = new ArrayList<>();

    }



    @Override
    public void onItemClick(View view, int postition) {

    }

    @Override
    public void onItemLongClick(View view, int postition) {

    }
}
