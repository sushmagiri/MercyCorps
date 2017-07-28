package com.example.user.mercycorpsfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;

import com.example.user.mercycorpsfinal.R;
import com.example.user.mercycorpsfinal.adapter.CallListAdapter;
import com.example.user.mercycorpsfinal.model.LatLon;

public class CallListActivity extends AppCompatActivity {
    LatLon latLon;
    String TAG = "TAG";
    private RecyclerView recyclerView;
    private CallListAdapter cAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Mercy Corps</font>"));

        latLon = new LatLon();

        Intent intent = getIntent();
        latLon = (LatLon) intent.getSerializableExtra("rumi");
        Log.e(TAG, "onCreate: " + latLon.getName());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        cAdapter = new CallListAdapter(CallListActivity.this,latLon.getContacts());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);


    }
}
