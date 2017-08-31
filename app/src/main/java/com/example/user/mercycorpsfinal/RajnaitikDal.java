package com.example.user.mercycorpsfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.user.mercycorpsfinal.activity.DetailActivity;
import com.example.user.mercycorpsfinal.activity.KanchanpurList;
import com.example.user.mercycorpsfinal.adapter.CustomAdapterList;
import com.example.user.mercycorpsfinal.database.DatabaseHelper;
import com.example.user.mercycorpsfinal.model.ListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RajnaitikDal extends AppCompatActivity {
    private DatabaseHelper db =new DatabaseHelper(getApplicationContext());
    private List<ListItem> entityArrayList=new ArrayList<>();
    CustomAdapterList adapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanchanpur_list);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        adapter = new CustomAdapterList(entityArrayList, new CustomAdapterList.OnItemClickListener() {
//            @Override
//            public void onItemClick(ListItem item) {
//                Intent i=new Intent(getApplicationContext(),DetailActivity.class);
//                i.putExtra("data",(Serializable)item);
//                startActivity(i);
//            }
//        },);
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(verticalLayoutmanager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        prepareData();

    }

    private void prepareData() {
        List<String>stringList=db.getAllData();
        Log.d("hello",stringList.toString());

//     List<ListItem> list=db.getAllData();
//        entityArrayList.add(new ListItem(db.getAllData());
    }
}
