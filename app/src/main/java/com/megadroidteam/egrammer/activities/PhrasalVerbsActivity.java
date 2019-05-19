package com.megadroidteam.egrammer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.megadroidteam.egrammer.R;
import com.megadroidteam.egrammer.adapter.EgrammarAdapter;
import com.megadroidteam.egrammer.model.Pharal;

import java.util.ArrayList;
import java.util.List;

public class PhrasalVerbsActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recycle;
    EgrammarAdapter mAdapter;
    private List<Pharal> pharalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrasal_verbs);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycle = findViewById(R.id.recycle);


        mAdapter = new EgrammarAdapter(getApplicationContext(),pharalList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL
        ,false);
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setAdapter(mAdapter);

    }
}
