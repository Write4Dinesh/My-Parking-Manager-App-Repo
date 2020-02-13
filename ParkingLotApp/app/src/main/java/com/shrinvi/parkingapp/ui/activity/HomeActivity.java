package com.shrinvi.parkingapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shrinvi.parkingapp.R;
import com.shrinvi.parkingapp.model.ParkingSystem;
import com.shrinvi.parkingapp.ui.adapter.ParkingAdapter;


public class HomeActivity extends BaseActivity {
    public static final int COLUMN_COUNT = 5;
    private ParkingSystem mParkingSystem;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeParkingSystem();
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        mRecyclerView.setAdapter(new ParkingAdapter(this, mParkingSystem));
    }

    private void initializeParkingSystem() {
        mParkingSystem = ParkingSystem.getInstance(20);
    }


}

